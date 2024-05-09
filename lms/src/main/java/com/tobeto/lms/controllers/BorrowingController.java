package com.tobeto.lms.controllers;

import com.tobeto.lms.services.abstracts.BorrowingService;
import com.tobeto.lms.services.dtos.requests.AddBorrowingRequest;
import com.tobeto.lms.services.dtos.responses.AddBorrowingResponse;
import com.tobeto.lms.services.dtos.responses.ListBorrowingResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/borrowings")
public class BorrowingController {
    private BorrowingService borrowingService;

    @PostMapping
    public AddBorrowingResponse add(@RequestBody @Valid AddBorrowingRequest request){
        return borrowingService.add(request);
    }
    @GetMapping
    public List<ListBorrowingResponse> getAll(){
        return borrowingService.getAll();
    }

}
