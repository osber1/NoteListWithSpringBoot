package com.notes.notelist.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetTokenBody {
    private String username;
    private String password;
}
