package com.tobeto.lms.services.concretes;

import com.tobeto.lms.core.utils.exceptions.types.BusinessException;
import com.tobeto.lms.entities.*;
import com.tobeto.lms.repositories.BookRepository;
import com.tobeto.lms.repositories.BorrowingRepository;
import com.tobeto.lms.repositories.ReturnRepository;
import com.tobeto.lms.repositories.UserRepository;
import com.tobeto.lms.services.abstracts.*;
import com.tobeto.lms.services.dtos.requests.AddReturnRequest;
import com.tobeto.lms.services.dtos.responses.AddReturnResponse;
import com.tobeto.lms.services.dtos.responses.ListReturnResponse;
import com.tobeto.lms.services.dtos.responses.ListUserResponse;
import com.tobeto.lms.services.mappers.ReturnMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReturnServiceImpl implements ReturnService {
    private ReturnRepository returnRepository;
    private BookService bookService;
    private AuthService authService;
    private BorrowingService borrowingService;

    @Override
    public AddReturnResponse add(AddReturnRequest addReturnRequest) {
        //borrowCheck(addReturnRequest.getBorrowingId());

        // Ödünç alma işlemi gerçekleştikten sonra kitabı geri teslim et

        Optional<Borrowing> optionalBorrowing = borrowingService.findById(addReturnRequest.getBorrowingId());
        Borrowing borrowing = optionalBorrowing.orElseThrow(() -> new BusinessException("Ödünç alma işlemi bulunamadı."));
        returnBookStatus(borrowing);

        Return addReturn=ReturnMapper.INSTANCE.returnFromAddRequest(addReturnRequest);
        addReturn.setBorrowing(borrowing); //Manuel Mapping
        calculatePenalty(borrowing, addReturn); // gecikme ücretini hesapla
        addReturn=returnRepository.save(addReturn);
        AddReturnResponse response=ReturnMapper.INSTANCE.addResponseFromReturn(addReturn);

        return response;
    }

    @Override
    public List<ListReturnResponse> getAll() {
        List<Return> returns=returnRepository.findAll();
        List<ListReturnResponse> result = returns
                .stream()
                .map((return1) ->ReturnMapper.INSTANCE.listResponseFromReturn(return1) )
                .toList();
        return result;
    }

    /*private void borrowCheck(int id){
        Optional<Borrowing> borrowing=borrowingRepository.findById(id);
        if(borrowing.isEmpty()){
            throw new RuntimeException("Böyle Bir Ödünç Alma Yok!");
        }
    }*/

    private void returnBookStatus(Borrowing borrowing) {
        Book book = borrowing.getBook();
        if (book.getBookStatus() == BookStatus.NOTAVAILABLE) {
            book.setBookStatus(BookStatus.AVAILABLE);
            bookService.save(book);
        } else {
            throw new BusinessException("Bu kitap zaten Teslim edildi");
        }
    }

    private void calculatePenalty(Borrowing borrowing, Return return1) {
        LocalDate dueDate = borrowing.getDueDate();
        LocalDate returnDate = return1.getReturnDate();
        double dailyFee= borrowing.getDailyLateFee();
        //double delayDay= return1.getLateFee();
        double totalDelayFee=return1.getLateFee();

        int comparisonResult = returnDate.compareTo(dueDate);

        if (comparisonResult > 0) {
            // returnDate, dueDate'den sonra
            long difference = ChronoUnit.DAYS.between(dueDate, returnDate);
            // Burada gecikme için yapılacak işlemleri yapabilirsiniz
            totalDelayFee=difference*dailyFee;
        } else {
            totalDelayFee=0;
            // returnDate, dueDate'den önce
            // Bu durumda gecikme yoktur veya erken iade vardır
        }
        return1.setLateFee(totalDelayFee);

        // User sınıfındaki penaltyAmount alanını güncelle
        User user = borrowing.getUser();
        double currentPenaltyAmount = user.getPenaltyAmount();
        user.setPenaltyAmount(currentPenaltyAmount + totalDelayFee);
        authService.save(user); // User nesnesini veritabanına kaydet

    }
}
