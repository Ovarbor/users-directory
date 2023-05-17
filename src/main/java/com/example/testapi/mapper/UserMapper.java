package com.example.testapi.mapper;
import com.example.testapi.dto.NewUserDto;
import com.example.testapi.dto.UserDto;
import com.example.testapi.model.User;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(NewUserDto newUserDto);

    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> users);
}
