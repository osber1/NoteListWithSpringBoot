package com.notes.notelist.controller;

import com.notes.notelist.DTO.UserDTO;
import com.notes.notelist.mapper.UserMapper;
import com.notes.notelist.model.User;
import com.notes.notelist.service.UserService;
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
    public UserDTO create(@Valid @RequestBody UserDTO userDTO) {
        User user = service.create(UserMapper.INSTANCE.userToEntity(userDTO));
        return UserMapper.INSTANCE.userToDTO(user);
    }

    @GetMapping
    public Collection<UserDTO> getAll() {
        Collection<User> all = service.getAll();
        return UserMapper.INSTANCE.userToDTOs(all);
    }

    @GetMapping("/{id}")
    public UserDTO getOne(@PathVariable Integer id) {
        User user = service.getOne(id);
        return UserMapper.INSTANCE.userToDTO(user);
    }

    @PutMapping
    public UserDTO update(@Valid @RequestBody UserDTO userDTO) {
        User user = service.update(UserMapper.INSTANCE.userToEntity(userDTO));
        return UserMapper.INSTANCE.userToDTO(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
