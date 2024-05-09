package com.tobeto.lms.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBookResponse {
    private int id;
    private String title;
    private String author;
    private String publicationYear;
}
