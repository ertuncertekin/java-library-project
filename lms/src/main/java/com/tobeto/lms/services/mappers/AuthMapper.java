package com.tobeto.lms.services.mappers;

import com.tobeto.lms.entities.User;
import com.tobeto.lms.services.dtos.responses.ListUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {
    AuthMapper INSTANCE= Mappers.getMapper(AuthMapper.class);

    @Mapping(source = "role",target = "role")
    ListUserResponse listResponseFromUser(User user);
}
