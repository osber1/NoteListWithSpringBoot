package com.notes.notelist.controller;

import com.notes.notelist.DTO.UserDTO;
import com.notes.notelist.mapper.UserMapper;
import com.notes.notelist.model.User;
import com.notes.notelist.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/api/users")
public class UserController {
    private final UserService service;

    @PostMapping
    @ApiOperation(value = "Adds user",
            notes = "Add new user to the list.",
            response = User.class)
    public UserDTO create(@Valid @RequestBody UserDTO userDTO) {
        User user = service.create(UserMapper.INSTANCE.userToEntity(userDTO));
        return UserMapper.INSTANCE.userToDTO(user);
    }

    @GetMapping
    @ApiOperation(value = "Shows all users",
            notes = "Shows all users the list.",
            response = User.class)
    public Collection<UserDTO> getAll() {
        Collection<User> all = service.getAll();
        return UserMapper.INSTANCE.userToDTOs(all);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Finds user by id",
            notes = "Provide an ID to look up specific user from whole list.",
            response = User.class)
    public UserDTO getOne(@PathVariable Integer id) {
        User user = service.getOne(id);
        return UserMapper.INSTANCE.userToDTO(user);
    }

    @PutMapping
    @ApiOperation(value = "Updates user",
            notes = "Provide new details if you want to change it.",
            response = User.class)
    public UserDTO update(@Valid @RequestBody UserDTO userDTO) {
        User user = service.update(UserMapper.INSTANCE.userToEntity(userDTO));
        return UserMapper.INSTANCE.userToDTO(user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes user by id",
            notes = "Provide an ID and delete user from the list.",
            response = User.class)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
