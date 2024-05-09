package com.tobeto.lms.services.concretes;

import com.tobeto.lms.core.utils.exceptions.types.BusinessException;
import com.tobeto.lms.entities.Book;
import com.tobeto.lms.entities.BookStatus;
import com.tobeto.lms.entities.Borrowing;
import com.tobeto.lms.entities.User;
import com.tobeto.lms.repositories.BorrowingRepository;
import com.tobeto.lms.services.abstracts.AuthService;
import com.tobeto.lms.services.abstracts.BookService;
import com.tobeto.lms.services.abstracts.BorrowingService;
import com.tobeto.lms.services.dtos.requests.AddBorrowingRequest;
import com.tobeto.lms.services.dtos.responses.AddBorrowingResponse;
import com.tobeto.lms.services.dtos.responses.ListBorrowingResponse;
import com.tobeto.lms.services.mappers.BorrowingMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {
    private BorrowingRepository borrowingRepository;
    private AuthService authService;
    private BookService bookService;

    @Override
    public AddBorrowingResponse add(AddBorrowingRequest addBorrowingRequest) {
        // Kullanıcı id Kontrolü
        userIdCheck(addBorrowingRequest.getUserId());

        // Kitap durumu kontrolü ve ödünç alma işlemi
        Optional<Book> book = bookService.findById(addBorrowingRequest.getBookId());
        borrowBook(book);

        // Kullanıcının ceza miktarı kontrolü
        Optional<User> user = authService.findById(addBorrowingRequest.getUserId());
        if (user.isPresent()) {
            double penaltyAmount = user.get().getPenaltyAmount();
            penaltyAmountCheck(penaltyAmount);
        }

        // Ödünç alma işlemi başarılı ise, veritabanına kaydet
        Borrowing borrowing = BorrowingMapper.INSTANCE.borrowingFromAddRequest(addBorrowingRequest);
        borrowing = borrowingRepository.save(borrowing);
        AddBorrowingResponse response = BorrowingMapper.INSTANCE.addResponseFromBorrowing(borrowing);
        return response;
    }

    @Override
    public List<ListBorrowingResponse> getAll() {
        List<Borrowing> borrowings=borrowingRepository.findAll();
        List<ListBorrowingResponse> result = borrowings
                .stream()
                .map((borrowing) -> BorrowingMapper.INSTANCE.listResponseFromBorrowing(borrowing) )
                .toList();
        return result;
    }

    @Override
    public Optional<Borrowing> findById(int id) {
        return borrowingRepository.findById(id);
    }

    private void userIdCheck(int id) {
        Optional<User> user = authService.findById(id);
        if (user.isEmpty()) {
            throw new BusinessException("UserId Mevcut Değil!");
        }
    }

    private void borrowBook(Optional<Book> book) {
        if (book.get().getBookStatus() == BookStatus.NOTAVAILABLE) {
            throw new BusinessException("Bu Kitap Şu An Kullanımda");
        }
        // Kitabın durumu uygunsa, ödünç alınabilir
        book.get().setBookStatus(BookStatus.NOTAVAILABLE);
    }

    private void penaltyAmountCheck(double penaltyAmount) {
        if (penaltyAmount > 1000) {
            throw new BusinessException("1000 ve üzeri borcunuz bulunmaktadır. Ödeme yaptıktan sonra kitap ödünç alabilirsiniz.");
        }
    }

}




