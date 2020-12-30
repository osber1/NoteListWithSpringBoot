package com.notes.notelist.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Details about the note.")
public class NoteDTO {
    @ApiModelProperty(notes = "The unique id of the note.")
    private Integer id;
    @ApiModelProperty(notes = "The note's title.")
    private String title;
    @ApiModelProperty(notes = "The note's status (done or not).")
    private boolean done;
}
