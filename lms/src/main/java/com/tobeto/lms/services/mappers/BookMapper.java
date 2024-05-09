package com.tobeto.lms.services.mappers;

import com.tobeto.lms.entities.Book;
import com.tobeto.lms.services.dtos.requests.AddBookRequest;
import com.tobeto.lms.services.dtos.requests.UpdateBookRequest;
import com.tobeto.lms.services.dtos.responses.AddBookResponse;
import com.tobeto.lms.services.dtos.responses.ListBookResponse;
import com.tobeto.lms.services.dtos.responses.UpdateBookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    //Ekleme işlemi
    @Mapping(target = "bookStatus", ignore = true)
    Book bookFromAddRequest(AddBookRequest addBookRequest);
    AddBookResponse addResponseFromBook(Book book);

    //Güncelleme İşlemi
    Book bookFromUpdateRequest(UpdateBookRequest updateBookRequest);
    UpdateBookResponse updateResponseFromBook(Book book);

    ListBookResponse listResponseFromBook(Book book);
}
