package com.example.TestAPI.service;
import com.example.TestAPI.dto.NewUserDto;
import com.example.TestAPI.dto.UserDto;
import com.example.TestAPI.exceptions.NotFoundValidationException;
import com.example.TestAPI.mapper.UserMapper;
import com.example.TestAPI.model.User;
import com.example.TestAPI.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserDto addUser(NewUserDto newUserDto) {
        UserDto userDto = userMapper.toUserDto(newUserDto);
        return userMapper.toUserDto(userRepo.save(userMapper.toUser(userDto)));
    }

    @Override
    public UserDto findUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() ->
                new NotFoundValidationException("User with id: " + userId + " not found"));
        return userMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> findAllUsers(int from, int size) {
        List<User> users = userRepo.findAll(PageRequest.of(from, size, Sort.by("id").ascending())).toList();
        return userMapper.toUserDtoList(users);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User oldUser = userRepo.findById(userId).orElseThrow(() ->
                new NotFoundValidationException("User with id: " + userId + " not found"));
        User newUser = userParametersUpdate(oldUser, userDto);
        return userMapper.toUserDto(userRepo.save(newUser));
    }

    @Override
    public void removeUser(Long userId) {
        if (!userRepo.existsById(userId)) {
            throw new NotFoundValidationException("User with id: " + userId + " not found");
        }
        userRepo.deleteById(userId);
    }

    private User userParametersUpdate(User oldUser, UserDto userDto) {
        if (userDto.getName() != null) {
            if (!userDto.getName().isBlank()) {
                oldUser.setName(userDto.getName());
            }
        }
        if (userDto.getLastName() != null) {
            if (!userDto.getLastName().isBlank()) {
                oldUser.setLastName(userDto.getLastName());
            }
        }
        if (userDto.getSurName() != null) {
            if (!userDto.getSurName().isBlank()) {
                oldUser.setSurName(userDto.getSurName());
            }
        }
        return oldUser;
    }
}
