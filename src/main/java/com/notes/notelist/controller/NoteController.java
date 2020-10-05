package com.notes.notelist.controller;

import com.notes.notelist.model.Note;
import com.notes.notelist.repo.NoteRepo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteRepo noteRepo;

    @GetMapping(value = "/notes")
    @ApiOperation(value = "Shows all notes",
            notes = "Shows all notes in the list.",
            response = Note.class)
    public List<Note> findAll() {
        return noteRepo.findAll();
    }

    @ApiOperation(value = "Finds note by id",
            notes = "Provide an ID to look up specific note from whole list.",
            response = Note.class)
    @GetMapping(value = "/note/{id}")
    public Optional<Note> findNote(@PathVariable Integer id) {
        return noteRepo.findById(id);
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "Adds note",
            notes = "Add new note to the list.",
            response = Note.class)
    public Note save(@Valid @NotNull @RequestBody Note note) {
        return noteRepo.save(note);
    }

    @PutMapping(value = "/update/{id}")
    @ApiOperation(value = "Updates note by id",
            notes = "Provide a note ID, which you want to update. Then provide a new title and done status if you want to change it.",
            response = Note.class)
    public Note update(@Valid @NotNull @RequestBody Note note, @PathVariable Integer id) {
        Note oldNote = noteRepo.getOne(id);
        if (note.getTitle() != null) {
            oldNote.setTitle(note.getTitle());
        }
        if (note.isDone() == false && noteRepo.getOne(id).isDone() == false) {
            oldNote.setDone(false);
        } else if (note.isDone() == false) {
            oldNote.setDone(false);
        } else {
            oldNote.setDone(true);
        }
        return noteRepo.save(oldNote);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "Deletes note by id",
            notes = "Provide an ID and delete note from the list.",
            response = Note.class)
    public void delete(@PathVariable Integer id) {
        noteRepo.deleteById(id);
    }

    @PutMapping(value = "/makedone/{id}")
    @ApiOperation(value = "Changes note status",
            notes = "Provide an ID to change note status to done.",
            response = Note.class)
    public Note makeDone(@PathVariable Integer id) {
        Note note = noteRepo.getOne(id);
        note.setTitle(noteRepo.getOne(id).getTitle());
        note.setDone(true);
        return noteRepo.save(note);
    }
}
