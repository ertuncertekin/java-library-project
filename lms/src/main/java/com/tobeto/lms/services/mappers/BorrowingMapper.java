package com.tobeto.lms.services.mappers;

import com.tobeto.lms.entities.Borrowing;
import com.tobeto.lms.services.dtos.requests.AddBorrowingRequest;
import com.tobeto.lms.services.dtos.responses.AddBorrowingResponse;
import com.tobeto.lms.services.dtos.responses.ListBorrowingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BorrowingMapper {
    BorrowingMapper INSTANCE= Mappers.getMapper(BorrowingMapper.class);

    //Request FK'lar İçin Mapping
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "bookId", target = "book.id")
    Borrowing borrowingFromAddRequest(AddBorrowingRequest addBorrowingRequest);

    //Response Fk'lar İçin Mapping
    @Mapping(source = "user.id",target = "userId")
    @Mapping(source="book.id",target="bookId")
    AddBorrowingResponse addResponseFromBorrowing(Borrowing borrowing);

    @Mapping(source = "user.id",target = "userId")
    @Mapping(source="book.id",target="bookId")
    ListBorrowingResponse listResponseFromBorrowing(Borrowing borrowing);

}
