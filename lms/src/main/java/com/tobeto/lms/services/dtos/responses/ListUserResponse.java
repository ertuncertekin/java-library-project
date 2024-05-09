package com.tobeto.lms.services.dtos.responses;

import com.tobeto.lms.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListUserResponse {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private double penaltyAmount;
    private String password;
    private Role role;
}
