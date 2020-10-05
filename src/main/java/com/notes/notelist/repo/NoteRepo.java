package com.notes.notelist.repo;

import com.notes.notelist.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository <Note, Integer> {

}
