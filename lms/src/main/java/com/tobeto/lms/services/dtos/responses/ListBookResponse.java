package com.tobeto.lms.services.dtos.responses;

import com.tobeto.lms.entities.BookStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListBookResponse {
    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private BookStatus bookStatus;

}
