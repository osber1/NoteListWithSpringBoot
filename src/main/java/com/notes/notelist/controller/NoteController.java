package com.notes.notelist.controller;


import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.notes.notelist.DTO.NoteDTO;
import com.notes.notelist.mapper.NoteMapper;
import com.notes.notelist.model.Note;
import com.notes.notelist.service.NoteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService service;

    @GetMapping(value = "withoutId")
    @ApiOperation(value = "Shows all notes without id",
            notes = "Shows all notes without id in the list.",
            response = Note.class)
    public MappingJacksonValue findAllWithoutId() {
        Collection<Note> notes = service.findAll();
        MappingJacksonValue mapping = new MappingJacksonValue(notes);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
                filterOutAllExcept("title", "done"); // fields you want to show in response body
        mapping.setFilters(new SimpleFilterProvider().addFilter("Note filter", filter));
        return mapping;
    }

    @GetMapping
    @ApiOperation(value = "Shows all notes",
            notes = "Shows all notes in the list.",
            response = Note.class)
    public Collection<NoteDTO> findAll() {
        Collection<Note> notes = service.findAll();
        return NoteMapper.INSTANCE.noteToDTOs(notes);
    }

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Finds note by id",
            notes = "Provide an ID to look up specific note from whole list.",
            response = Note.class)
    public NoteDTO findNote(@PathVariable Integer id) {
        Note note = service.getOne(id);
        return NoteMapper.INSTANCE.noteToDTO(note);
    }

    @PostMapping
    @ApiOperation(value = "Adds note",
            notes = "Add new note to the list.",
            response = Note.class)
    public NoteDTO save(@Valid @RequestBody NoteDTO noteDTO) {
        Note note = service.create(NoteMapper.INSTANCE.noteToEntity(noteDTO));
        return NoteMapper.INSTANCE.noteToDTO(note);
    }

    @PutMapping
    @ApiOperation(value = "Updates note",
            notes = "Provide a new title and done status if you want to change it.",
            response = Note.class)
    public NoteDTO update(@Valid @RequestBody NoteDTO noteDTO) {
        Note note = service.update(NoteMapper.INSTANCE.noteToEntity(noteDTO));
        return NoteMapper.INSTANCE.noteToDTO(note);
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Deletes note by id",
            notes = "Provide an ID and delete note from the list.",
            response = Note.class)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping(value = "done/{id}")
    @ApiOperation(value = "Changes note status",
            notes = "Provide an ID to change note status to done.",
            response = Note.class)
    public NoteDTO makeDone(@PathVariable int id) {
        Note note = service.makeDone(id);
        return NoteMapper.INSTANCE.noteToDTO(note);
    }
}
