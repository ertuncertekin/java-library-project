package com.tobeto.lms.services.dtos.requests;

import com.tobeto.lms.entities.Role;
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
public class RegisterRequest {
    @NotBlank(message = "İsim Alanı Boş Olamaz!")
    private String name;
    @NotBlank(message = "Soyad Alanı Boş Olamaz!")
    private String surname;
    @NotBlank(message = "Telefon Numarası Boş Olamaz!")
    private String phoneNumber;
    @NotBlank(message = "Email Alanı Boş Olamaz!")
    @Email(message = "Email Yanlış Biçimde")
    private String email;
    @NotBlank(message = "Şifre Alanı Boş Olamaz")
    private String password;
    private Role role;
}
