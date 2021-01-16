package com.notes.notelist.repository;

import com.notes.notelist.model.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NoteListRepositoryIntegrationTest {

    @Autowired
    private NoteRepository repository;

    @Test
    public void ifNewNoteSaved_thenSuccess() {
        Note newNote = new Note();
        newNote.setTitle("Darbas");
        newNote.setDone(false);
        repository.save(newNote);
        assertEquals(1, repository.findAll().size());
    }
}
