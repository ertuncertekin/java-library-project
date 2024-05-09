package com.tobeto.lms.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserResponse {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
}
