package com.notes.notelist.controller;

import com.notes.notelist.model.Note;
import com.notes.notelist.service.NoteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService service;

    @GetMapping(value = "notes")
    @ApiOperation(value = "Shows all notes",
            notes = "Shows all notes in the list.",
            response = Note.class)
    public List<Note> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "Finds note by id",
            notes = "Provide an ID to look up specific note from whole list.",
            response = Note.class)
    @GetMapping(value = "notes/{id}")
    public Note findNote(@PathVariable Integer id) {
        return service.getOne(id);
    }

    @PostMapping(value = "notes")
    @ApiOperation(value = "Adds note",
            notes = "Add new note to the list.",
            response = Note.class)
    public Note save(@Valid @RequestBody Note note) {
        return service.create(note);
    }

    @PutMapping(value = "notes")
    @ApiOperation(value = "Updates note",
            notes = "Provide a new title and done status if you want to change it.",
            response = Note.class)
    public Note update(@Valid @RequestBody Note note) {
        return service.update(note);
    }

    @DeleteMapping(value = "notes/{id}")
    @ApiOperation(value = "Deletes note by id",
            notes = "Provide an ID and delete note from the list.",
            response = Note.class)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping(value = "notes/done/{id}")
    @ApiOperation(value = "Changes note status",
            notes = "Provide an ID to change note status to done.",
            response = Note.class)
    public Note makeDone(@PathVariable int id) {
        return service.makeDone(id);
    }
}
