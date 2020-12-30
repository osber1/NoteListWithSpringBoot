package com.notes.notelist.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@ApiModel(description = "Details about the note.")
public class Note {
    @ApiModelProperty(notes = "The unique id of the note.")
    @Id
    @GeneratedValue
    private Integer id;
    @ApiModelProperty(notes = "The note's title.")
    @NotBlank
    private String title;
    @ApiModelProperty(notes = "The note's status (done or not).")
    private boolean done;
}
