package com.example.mainservice.service;

import com.example.mainservice.dto.NewUserDto;
import com.example.mainservice.dto.UserDto;
import com.example.mainservice.dto.UserDtoGet;
import com.example.mainservice.dto.UserDtoUpdate;
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
