package com.tobeto.lms.services.concretes;

import com.tobeto.lms.core.utils.exceptions.types.BusinessException;
import com.tobeto.lms.entities.Book;
import com.tobeto.lms.entities.BookStatus;
import com.tobeto.lms.entities.User;
import com.tobeto.lms.repositories.BookRepository;
import com.tobeto.lms.services.abstracts.BookService;
import com.tobeto.lms.services.dtos.requests.AddBookRequest;
import com.tobeto.lms.services.dtos.requests.UpdateBookRequest;
import com.tobeto.lms.services.dtos.responses.AddBookResponse;
import com.tobeto.lms.services.dtos.responses.ListBookResponse;
import com.tobeto.lms.services.dtos.responses.UpdateBookResponse;
import com.tobeto.lms.services.mappers.BookMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public AddBookResponse add(AddBookRequest addBookRequest) {

        Book book = BookMapper.INSTANCE.bookFromAddRequest(addBookRequest);
        book.setBookStatus(BookStatus.AVAILABLE);
        book = bookRepository.save(book);
        AddBookResponse response = BookMapper.INSTANCE.addResponseFromBook(book);

        return response;
    }

    @Override
    public UpdateBookResponse update(UpdateBookRequest updateBookRequest) {
        bookIdCheck(updateBookRequest.getId());
        Book book = BookMapper.INSTANCE.bookFromUpdateRequest(updateBookRequest);

        Book existingBook = bookRepository.findById(updateBookRequest.getId()).orElse(null);
        if (existingBook != null) {
            // Güncellenecek kitabın durumunu mevcut kitabın durumuyla güncelle
            book.setBookStatus(existingBook.getBookStatus());
        }

        book = bookRepository.save(book);

        UpdateBookResponse response = BookMapper.INSTANCE.updateResponseFromBook(book);
        response.setBookStatus(book.getBookStatus());
        return response;
    }

    @Override
    public void delete(int id) {
        bookIdCheck(id);
        bookRepository.deleteById(id);
    }

    @Override
    public List<ListBookResponse> getAll() {
        List<Book> books = bookRepository.findAll();
        // Book -> ListBookResponse

        List<ListBookResponse> list =
                books
                        .stream()
                        .map((book) -> BookMapper.INSTANCE.listResponseFromBook(book))
                        .toList();

        return list;
    }

    @Override
    public List<ListBookResponse> search(String query,String query2) {

        return bookRepository.search(query,query2);
    }


    private void bookIdCheck(int id){
        Optional<Book> book=bookRepository.findById(id);
        if(book.isEmpty()){
            throw new BusinessException("BookId Mevcut Değil!");
        }
    }

    @Override
    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
