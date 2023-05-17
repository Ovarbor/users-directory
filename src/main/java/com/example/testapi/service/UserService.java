package com.example.testapi.service;
import com.example.testapi.dto.NewUserDto;
import com.example.testapi.dto.UserDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {

    UserDto addUser(NewUserDto newUserDto);

    UserDto findUser(Long userId);

    List<UserDto> findAllUsers(int from, int size);

    UserDto updateUser(Long userId, UserDto userDto);

    void removeUser(Long userId);
}
