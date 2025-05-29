package com.projet.projet_collaboratif.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.projet_collaboratif.model.Projet;
import com.projet.projet_collaboratif.service.ProjetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjetController.class)
public class ProjetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetService projetService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllProjets() throws Exception {
        Projet p = new Projet();
        p.setNom("Test Project");

        when(projetService.getAllProjets()).thenReturn(List.of(p));

        mockMvc.perform(get("/api/projets"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnProjetById() throws Exception {
        Projet p = new Projet();
        p.setId(1L);
        p.setNom("Unique");

        when(projetService.getProjetById(1L)).thenReturn(Optional.of(p));

        mockMvc.perform(get("/api/projets/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundIfProjetDoesNotExist() throws Exception {
        when(projetService.getProjetById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/projets/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateProjet() throws Exception {
        Projet p = new Projet();
        p.setNom("To Create");

        when(projetService.saveProjet(any())).thenReturn(p);

        mockMvc.perform(post("/api/projets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateProjet() throws Exception {
        Projet p = new Projet();
        p.setId(1L);
        p.setNom("To Update");

        when(projetService.getProjetById(1L)).thenReturn(Optional.of(p));
        when(projetService.saveProjet(any())).thenReturn(p);

        mockMvc.perform(put("/api/projets/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundIfProjetToUpdateDoesNotExist() throws Exception {
        Projet p = new Projet();
        p.setId(999L);
        p.setNom("Fake");

        when(projetService.getProjetById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/projets/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteProjet() throws Exception {
        doNothing().when(projetService).deleteProjet(1L);

        mockMvc.perform(delete("/api/projets/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundIfProjetMissing() throws Exception {
        when(projetService.getProjetById(123L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/projets/123"))
                .andExpect(status().isNotFound());
    }

}
