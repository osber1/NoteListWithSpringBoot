package com.notes.notelist.DTO;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

@Data
@JsonFilter("User filter")
public class UserDTO {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String password;
}
