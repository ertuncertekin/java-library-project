package com.tobeto.lms.controllers;

import com.tobeto.lms.services.abstracts.BookService;
import com.tobeto.lms.services.dtos.requests.AddBookRequest;
import com.tobeto.lms.services.dtos.requests.UpdateBookRequest;
import com.tobeto.lms.services.dtos.responses.AddBookResponse;
import com.tobeto.lms.services.dtos.responses.ListBookResponse;
import com.tobeto.lms.services.dtos.responses.UpdateBookResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping
    public List<ListBookResponse> getAll()
    {
        return bookService.getAll();
    }

    @GetMapping("/search")
    public List<ListBookResponse> search(@RequestParam(required = false, defaultValue = "") String bookTitle,
                                         @RequestParam(required = false, defaultValue = "") String author){
        return bookService.search(bookTitle,author);
    }

    @PostMapping
    public AddBookResponse add(@RequestBody @Valid AddBookRequest request){
        return bookService.add(request);
    }
    @PutMapping
    public UpdateBookResponse update(@RequestBody @Valid UpdateBookRequest request) {
        return bookService.update(request);
    }
    @DeleteMapping
    public  void delete(int id){
        bookService.delete(id);
    }
}
