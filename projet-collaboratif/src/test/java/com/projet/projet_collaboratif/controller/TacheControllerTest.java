package com.projet.projet_collaboratif.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.projet_collaboratif.model.Tache;
import com.projet.projet_collaboratif.model.Statut;
import com.projet.projet_collaboratif.service.TacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TacheController.class)
public class TacheControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TacheService tacheService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllTaches() throws Exception {
        Tache t = new Tache();
        t.setTitre("Task");

        when(tacheService.getAllTaches()).thenReturn(List.of(t));

        mockMvc.perform(get("/api/taches"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateTache() throws Exception {
        Tache t = new Tache();
        t.setTitre("Nouvelle tâche");
        t.setStatut(Statut.A_FAIRE);
        t.setDateDebut(LocalDate.now());

        when(tacheService.saveTache(any())).thenReturn(t);

        mockMvc.perform(post("/api/taches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(t)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteTache() throws Exception {
        doNothing().when(tacheService).deleteTache(1L);

        mockMvc.perform(delete("/api/taches/1"))
                .andExpect(status().isOk());
    }
}
