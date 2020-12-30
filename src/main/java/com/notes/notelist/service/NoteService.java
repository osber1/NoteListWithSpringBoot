package com.notes.notelist.service;

import com.notes.notelist.exception.NotFoundException;
import com.notes.notelist.model.Note;
import com.notes.notelist.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteService {
    private final NoteRepository repository;

    @Transactional(readOnly = true)
    public List<Note> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Note getOne(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no id: " + id));
    }

    public Note create(Note note) {
        return repository.save(note);
    }

    public Note update(Note note) {
        if (!repository.existsById(note.getId())) {
            throw new NotFoundException("There is no id: " + note.getId());
        }
        return repository.save(note);
    }

    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("There is no id: " + id);
        }
        repository.deleteById(id);
    }

    public Note makeDone(int id) {
        Note note = getOne(id);
        note.setDone(true);
        return repository.save(note);
    }
}
