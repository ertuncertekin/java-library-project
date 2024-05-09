package com.tobeto.lms.services.dtos.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBorrowingResponse {

    private int id;
    private int userId;
    private int bookId;
    private LocalDate borrowingDate;
    private LocalDate dueDate;
    private double dailyLateFee;
}
