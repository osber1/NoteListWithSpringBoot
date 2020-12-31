package com.notes.notelist.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(3)
    public void getsAllNotes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/notes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(2)
    public void getsSingleNote() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/notes/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(5)
    public void returnsNotFoundForInvalidSingleNote() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/notes/6")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @Order(1)
    public void addsNewNote() throws Exception {
        String newNote = "{\"title\":\"Task\",\"done\":\"false\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newNote)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(4)
    public void deletesNote() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/notes/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
