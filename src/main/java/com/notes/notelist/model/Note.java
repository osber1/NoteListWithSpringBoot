package com.notes.notelist.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@ApiModel(description = "Details about the note.")
public class Note {
    @ApiModelProperty(notes = "The unique id of the note.")
    private Integer id;
    @NotBlank
    @ApiModelProperty(notes = "The note's title.")
    private String title;
    @ApiModelProperty(notes = "The note's status (done or not).")
    private boolean done;

    public Note() {
    }

    public Note(Integer id, String title, boolean done) {
        this.id = id;
        this.title = title;
        this.done = done;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
