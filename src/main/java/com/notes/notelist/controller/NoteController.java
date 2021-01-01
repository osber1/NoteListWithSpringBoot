package com.notes.notelist.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
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
@RequestMapping("api")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService service;

    @GetMapping(value = "notesWithoutId")
    @ApiOperation(value = "Shows all notes without id",
            notes = "Shows all notes without id in the list.",
            response = Note.class)
    public MappingJacksonValue findAllWithoutId() {
        Collection<Note> notes = service.findAll();
        MappingJacksonValue mapping = new MappingJacksonValue(notes);
        mapping.setFilters(createFilter());
        return mapping;
    }

    @GetMapping(value = "notes")
    @ApiOperation(value = "Shows all notes",
            notes = "Shows all notes in the list.",
            response = Note.class)
    public Collection<NoteDTO> findAll() {
        Collection<Note> notes = service.findAll();
        return NoteMapper.INSTANCE.noteToDTOs(notes);
    }

    @ApiOperation(value = "Finds note by id",
            notes = "Provide an ID to look up specific note from whole list.",
            response = Note.class)
    @GetMapping(value = "notes/{id}")
    public NoteDTO findNote(@PathVariable Integer id) {
        Note note = service.getOne(id);
        return NoteMapper.INSTANCE.noteToDTO(note);
    }

    @PostMapping(value = "notes")
    @ApiOperation(value = "Adds note",
            notes = "Add new note to the list.",
            response = Note.class)
    public NoteDTO save(@Valid @RequestBody NoteDTO noteDTO) {
        Note note = service.create(NoteMapper.INSTANCE.noteToEntity(noteDTO));
        return NoteMapper.INSTANCE.noteToDTO(note);
    }

    @PutMapping(value = "notes")
    @ApiOperation(value = "Updates note",
            notes = "Provide a new title and done status if you want to change it.",
            response = Note.class)
    public NoteDTO update(@Valid @RequestBody NoteDTO noteDTO) {
        Note note = service.update(NoteMapper.INSTANCE.noteToEntity(noteDTO));
        return NoteMapper.INSTANCE.noteToDTO(note);
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
    public MappingJacksonValue makeDone(@PathVariable int id) {
        Note note = service.makeDone(id);
        MappingJacksonValue mapping = new MappingJacksonValue(note);
        mapping.setFilters(createFilter());
        return mapping;
    }

    private FilterProvider createFilter() {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("title", "done"); // fields you want to show
        return new SimpleFilterProvider().addFilter("Note filter", filter);
    }
}
