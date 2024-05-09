package com.tobeto.lms.services.dtos.requests;

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
public class AddReturnRequest {

    @NotNull(message = "Id Alanı Boş Olamaz")
    private int borrowingId;

    @NotNull(message = "Teslim Tarihi Boş Olamaz")
    private LocalDate returnDate;

}
