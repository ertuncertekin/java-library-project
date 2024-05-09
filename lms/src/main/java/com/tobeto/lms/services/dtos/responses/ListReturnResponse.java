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
public class ListReturnResponse {

    private int id;
    private int userId;
    private int bookId;
    private LocalDate borrowingDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double dailyLateFee;
    private double lateFee;

}
