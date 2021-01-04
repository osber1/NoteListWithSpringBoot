package com.notes.notelist.DTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "Details about the note.")
public class NoteDTO {
    private Integer id;
    private String title;
    private boolean done;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
}
