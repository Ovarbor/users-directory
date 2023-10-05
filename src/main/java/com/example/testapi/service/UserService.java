package com.example.TestAPI.service;
import com.example.TestAPI.dto.NewUserDto;
import com.example.TestAPI.dto.UserDto;
import com.example.TestAPI.dto.UserDtoGet;
import com.example.TestAPI.dto.UserDtoUpdate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {

    UserDto addUser(NewUserDto newUserDto);

    UserDtoGet getUser(Long userId);

    List<UserDtoGet> getAllUsers(int from, int size);

    UserDto updateUser(Long userId, UserDtoUpdate userDtoUpdate);

    void removeUser(Long userId);
}
