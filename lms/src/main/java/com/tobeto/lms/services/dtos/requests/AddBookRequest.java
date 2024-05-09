package com.tobeto.lms.services.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequest {
    @NotBlank(message = "Kitap ismi Boş geçilemez! ")
    @Size(min = 2)
    private String title;
    @NotBlank(message = "Yazar ismi Boş geçilemez! ")
    private String author;
    @NotBlank(message = "Basım Yılı Boş geçilemez! ")
    private String publicationYear;

}
