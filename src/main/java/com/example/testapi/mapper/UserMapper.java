package com.example.TestAPI.mapper;
import com.example.TestAPI.dto.NewUserDto;
import com.example.TestAPI.dto.UserDto;
import com.example.TestAPI.model.User;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(NewUserDto newUserDto);

    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> users);
}
