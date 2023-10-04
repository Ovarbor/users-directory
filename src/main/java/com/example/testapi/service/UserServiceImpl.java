package com.example.TestAPI.service;
import com.example.TestAPI.Utils.CreateImageLink;
import com.example.TestAPI.dto.NewUserDto;
import com.example.TestAPI.dto.UserDto;
import com.example.TestAPI.dto.UserDtoGet;
import com.example.TestAPI.dto.UserDtoUpdate;
import com.example.TestAPI.exceptions.ConflictException;
import com.example.TestAPI.exceptions.ImageNotFoundException;
import com.example.TestAPI.exceptions.NotFoundValidationException;
import com.example.TestAPI.mapper.UserMapper;
import com.example.TestAPI.model.User;
import com.example.TestAPI.repo.ContactInfoRepo;
import com.example.TestAPI.repo.ImageRepo;
import com.example.TestAPI.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final ImageRepo imageRepo;
    private final ContactInfoRepo contactInfoRepo;

    @Override
    public UserDto addUser(NewUserDto newUserDto) {
        UserDto userDto = userMapper.toUserDto(newUserDto);
        return userMapper.toUserDto(userRepo.save(userMapper.toUser(userDto)));
    }

    @Override
    public UserDtoGet getUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() ->
                new NotFoundValidationException("User with id " + userId + " not found"));
        UserDto userDto = userMapper.toUserDto(user);
        UserDtoGet userDtoGet = userMapper.toUserDtoGet(userDto);
        if (userDtoGet.getImage() != null) {
            userDtoGet.getImage().setLink(CreateImageLink.createImageLink(userDtoGet.getImage().getId()));
        }
        return userDtoGet;
    }

    @Override
    public List<UserDtoGet> getAllUsers(int from, int size) {
        List<User> users = userRepo.findAll(PageRequest.of(from, size, Sort.by("id").ascending())).toList();
        List<UserDtoGet> userDtoGets = userMapper.toUserDtoGetList(users);
        userDtoGets.stream().filter(userDtoGet -> userDtoGet.getImage() != null).forEach(userDtoGet -> userDtoGet.getImage().setLink(CreateImageLink.createImageLink(userDtoGet.getImage().getId())));
        return userDtoGets;
    }

    @Override
    public UserDto updateUser(Long userId, UserDtoUpdate userDtoUpdate) {
        User oldUser = userRepo.findById(userId).orElseThrow(() ->
                new NotFoundValidationException("User with id " + userId + " not found"));
        User newUser = userParametersUpdate(oldUser, userDtoUpdate);
        return userMapper.toUserDto(userRepo.save(newUser));
    }

    @Override
    public void removeUser(Long userId) {
        if (!userRepo.existsById(userId)) {
            throw new NotFoundValidationException("User with id " + userId + " not found");
        }
        if (contactInfoRepo.countContactInfoByUserId(userId) != 0) {
            throw new ConflictException("User with id " + userId + " still in use");
        }
        userRepo.deleteById(userId);
    }

    private User userParametersUpdate(User oldUser, UserDtoUpdate userDtoUpdate) {
        if (userDtoUpdate.getName() != null) {
            if (!userDtoUpdate.getName().isBlank()) {
                oldUser.setName(userDtoUpdate.getName());
            }
        }
        if (userDtoUpdate.getLastName() != null) {
            if (!userDtoUpdate.getLastName().isBlank()) {
                oldUser.setLastName(userDtoUpdate.getLastName());
            }
        }
        if (userDtoUpdate.getSurName() != null) {
            if (!userDtoUpdate.getSurName().isBlank()) {
                oldUser.setSurName(userDtoUpdate.getSurName());
            }
        }
        if (userDtoUpdate.getBirthday() != null) {
            if (!userDtoUpdate.getBirthday().isAfter(ChronoLocalDate.from(LocalDateTime.now()))) {
                oldUser.setBirthday(userDtoUpdate.getBirthday());
            }
        }
        if (userDtoUpdate.getImage() != null) {
            oldUser.setImage(imageRepo.findById(userDtoUpdate.getImage()).orElseThrow(() ->
                    new ImageNotFoundException("Image with id " + userDtoUpdate.getImage() + " not found")));
        }
        return oldUser;
    }
}
