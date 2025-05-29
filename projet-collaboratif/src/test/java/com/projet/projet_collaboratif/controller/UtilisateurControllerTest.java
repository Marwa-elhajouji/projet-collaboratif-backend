package com.projet.projet_collaboratif.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.projet_collaboratif.model.Utilisateur;
import com.projet.projet_collaboratif.service.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UtilisateurController.class)
class UtilisateurControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtilisateurService utilisateurService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllUtilisateurs() throws Exception {
        Utilisateur u = new Utilisateur();
        u.setNom("Alice");

        when(utilisateurService.getAllUtilisateurs()).thenReturn(List.of(u));

        mockMvc.perform(get("/api/utilisateurs"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundForInvalidId() throws Exception {
        when(utilisateurService.getUtilisateurById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/utilisateurs/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateUtilisateur() throws Exception {
        Utilisateur u = new Utilisateur();
        u.setNom("Created");
        when(utilisateurService.saveUtilisateur(any())).thenReturn(u);

        mockMvc.perform(post("/api/utilisateurs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateUtilisateur() throws Exception {
        Utilisateur u = new Utilisateur();
        u.setId(1L);
        u.setNom("Updated");

        when(utilisateurService.getUtilisateurById(1L)).thenReturn(Optional.of(u));
        when(utilisateurService.updateUtilisateur(eq(1L), any())).thenReturn(u);

        mockMvc.perform(put("/api/utilisateurs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteUtilisateur() throws Exception {
        doNothing().when(utilisateurService).deleteUtilisateur(1L);

        mockMvc.perform(delete("/api/utilisateurs/1"))
                .andExpect(status().isOk());
    }
}
