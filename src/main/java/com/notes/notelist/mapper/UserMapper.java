package com.notes.notelist.mapper;

import com.notes.notelist.DTO.UserDTO;
import com.notes.notelist.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToDTO(User user);

    User userToEntity(UserDTO dto);

    Collection<UserDTO> userToDTOs(Collection<User> all);
}
