package com.tobeto.lms.services.abstracts;

import com.tobeto.lms.entities.Book;
import com.tobeto.lms.services.dtos.requests.AddBookRequest;
import com.tobeto.lms.services.dtos.requests.UpdateBookRequest;
import com.tobeto.lms.services.dtos.responses.AddBookResponse;
import com.tobeto.lms.services.dtos.responses.ListBookResponse;
import com.tobeto.lms.services.dtos.responses.UpdateBookResponse;

import java.util.List;
import java.util.Optional;

public interface BookService {
    AddBookResponse add(AddBookRequest addBookRequest);
    UpdateBookResponse update(UpdateBookRequest updateBookRequest);
    void delete(int id);
    List<ListBookResponse> getAll();

    List<ListBookResponse> search(String query,String query2);

    Optional<Book> findById(int id);
    Book save(Book book);
}
