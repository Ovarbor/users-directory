package com.example.TestAPI.controller;
import com.example.TestAPI.dto.NewUserDto;
import com.example.TestAPI.dto.UserDto;
import com.example.TestAPI.dto.UserDtoGet;
import com.example.TestAPI.dto.UserDtoUpdate;
import com.example.TestAPI.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> postUser(@Valid @RequestBody NewUserDto newUserDto) {
        log.info("POST: /users");
        return ResponseEntity.status(201).body(userService.addUser(newUserDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDtoGet> getUser(@PathVariable Long userId) {
        log.info("GET /users/{}", userId);
        return ResponseEntity.ok().body(userService.getUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDtoGet>> getUsers(@RequestParam(value = "from", defaultValue = "0")
                                                  @PositiveOrZero Integer from,
                                                  @RequestParam(value = "size", defaultValue = "10")
                                                  @Positive Integer size) {
        log.info("GET /users?from=" + from + "&size=" + size);
        return ResponseEntity.ok().body(userService.getAllUsers(from, size));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId,
                                              @Valid @RequestBody UserDtoUpdate userDtoUpdate) {
        log.info("PATCH: /users/{}", userId);
        return ResponseEntity.ok().body(userService.updateUser(userId, userDtoUpdate));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.removeUser(userId);
        log.info("DELETE: /users/{}", userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
