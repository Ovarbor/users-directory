package com.example.testapi.service;
import com.example.testapi.dto.NewUserDto;
import com.example.testapi.dto.UserDto;
import com.example.testapi.dto.UserDtoGet;
import com.example.testapi.dto.UserDtoUpdate;
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
