package com.notes.notelist.mapper;

import com.notes.notelist.DTO.NoteDTO;
import com.notes.notelist.model.Note;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    NoteDTO noteToDTO(Note note);

    Note noteToEntity(NoteDTO dto);

    Collection<NoteDTO> noteToDTOs(Collection<Note> all);
}
