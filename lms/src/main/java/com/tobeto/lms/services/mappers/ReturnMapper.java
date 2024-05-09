package com.tobeto.lms.services.mappers;

import com.tobeto.lms.entities.Book;
import com.tobeto.lms.entities.Return;
import com.tobeto.lms.services.dtos.requests.AddBookRequest;
import com.tobeto.lms.services.dtos.requests.AddReturnRequest;
import com.tobeto.lms.services.dtos.responses.AddBookResponse;
import com.tobeto.lms.services.dtos.responses.AddReturnResponse;
import com.tobeto.lms.services.dtos.responses.ListReturnResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReturnMapper {
    ReturnMapper INSTANCE= Mappers.getMapper(ReturnMapper.class);

    //Ekleme İşlemi İçin Map'leme
    //@Mapping(source = "borrowingId",target = "borrowing.id")
    Return returnFromAddRequest(AddReturnRequest addReturnRequest);

    @Mapping(source = "borrowing.borrowingDate", target = "borrowingDate")
    @Mapping(source = "borrowing.dueDate", target = "dueDate")
    @Mapping(source = "borrowing.dailyLateFee", target = "dailyLateFee")
    @Mapping(source = "borrowing.user.id", target = "userId")
    @Mapping(source = "borrowing.book.id", target = "bookId")
    AddReturnResponse addResponseFromReturn(Return addReturn);

    @Mapping(source = "borrowing.borrowingDate", target = "borrowingDate")
    @Mapping(source = "borrowing.dueDate", target = "dueDate")
    @Mapping(source = "borrowing.dailyLateFee", target = "dailyLateFee")
    @Mapping(source = "borrowing.user.id", target = "userId")
    @Mapping(source = "borrowing.book.id", target = "bookId")
    ListReturnResponse listResponseFromReturn(Return return1);
}
