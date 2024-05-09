package com.tobeto.lms.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListBorrowingResponse {
    private int id;
    private int userId;
    private int bookId;
    private LocalDate borrowingDate;
    private LocalDate dueDate;
    private double dailyLateFee;
}