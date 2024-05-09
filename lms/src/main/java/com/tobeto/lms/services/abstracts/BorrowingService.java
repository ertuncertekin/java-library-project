package com.tobeto.lms.services.abstracts;

import com.tobeto.lms.entities.Borrowing;
import com.tobeto.lms.services.dtos.requests.AddBorrowingRequest;
import com.tobeto.lms.services.dtos.responses.AddBorrowingResponse;
import com.tobeto.lms.services.dtos.responses.ListBorrowingResponse;

import java.util.List;
import java.util.Optional;

public interface BorrowingService {
    AddBorrowingResponse add(AddBorrowingRequest addBorrowingRequest);

    List<ListBorrowingResponse> getAll();

    Optional<Borrowing> findById(int id);
}
