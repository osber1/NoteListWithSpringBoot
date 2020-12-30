package com.notes.notelist.repository;

import com.notes.notelist.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository <Note, Integer> {

}
