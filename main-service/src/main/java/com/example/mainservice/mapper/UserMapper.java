package com.example.mainservice.mapper;

import com.example.mainservice.dto.NewUserDto;
import com.example.mainservice.dto.UserDto;
import com.example.mainservice.dto.UserDtoGet;
import com.example.mainservice.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User toUser(UserDto userDto);

    UserDto toUserDto(NewUserDto newUserDto);

    UserDto toUserDto(User user);

    UserDtoGet toUserDtoGet(UserDto userDto);

    List<UserDtoGet> toUserDtoGetList(List<User> users);
}
