package com.notes.notelist.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@ApiModel(description = "Details about the note.")
@JsonFilter("Note filter")
public class Note {
    @ApiModelProperty(notes = "The unique id of the note.")
    @Id
    @GeneratedValue
    private Integer id;
    @ApiModelProperty(notes = "The note's title.")
    @Column(nullable = false)
    private String title;
    @ApiModelProperty(notes = "The note's status (done or not).")
    private boolean done;
    @ApiModelProperty(notes = "The note's creation date.")
//    @NotNull
    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
    @ApiModelProperty(notes = "The note's last update date.")
    @Column(name = "last_updated_date")
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;
}
