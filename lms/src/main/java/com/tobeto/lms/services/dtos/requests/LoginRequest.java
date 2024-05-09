package com.tobeto.lms.services.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email Alanı Boş Olamaz!")
    @Email(message = "Lütfen Geçerli Bir Email Giriniz.")
    private String email;

    @NotBlank(message = "Şifre Alanı Boş Olamaz")
    private String password;
}
