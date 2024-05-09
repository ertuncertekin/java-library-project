package com.tobeto.lms.services.dtos.requests;

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
public class AddBorrowingRequest {
    @NotNull(message = "Kullanıcı Id Alanı Boş Olamaz!")
    private int userId;
    @NotNull(message = "Kitap Id Alanı Boş Olamaz!")
    private int bookId;
    @NotNull(message = "Ödünç Alma Tarihi Boş Olamaz")
    private LocalDate borrowingDate;
    @NotNull(message = "Son Teslim Tarihi Boş Olamaz")
    private LocalDate dueDate;
    @NotNull(message = "Günlük Ceza Bedeli Boş Olamaz")
    private double dailyLateFee;
}
