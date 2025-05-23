package com.projet.projet_collaboratif.service;

import com.projet.projet_collaboratif.model.Projet;
import com.projet.projet_collaboratif.repository.ProjetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjetServiceTest {

    private ProjetRepository projetRepository;
    private ProjetService projetService;

    @BeforeEach
    void setUp() {
        projetRepository = mock(ProjetRepository.class);
        projetService = new ProjetService();
        projetService.setProjetRepository(projetRepository); 
    }

    @Test
    void testGetAllProjets() {
        Projet p1 = new Projet();
        p1.setNom("Projet Alpha");

        Projet p2 = new Projet();
        p2.setNom("Projet Beta");

        when(projetRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        var result = projetService.getAllProjets();

        assertEquals(2, result.size());
        verify(projetRepository, times(1)).findAll();
    }

    @Test
    void testGetProjetById() {
        Projet p = new Projet();
        p.setId(1L);
        p.setNom("Projet Test");

        when(projetRepository.findById(1L)).thenReturn(Optional.of(p));

        var result = projetService.getProjetById(1L);

        assertTrue(result.isPresent());
        assertEquals("Projet Test", result.get().getNom());
    }

    @Test
    void testSaveProjet() {
        Projet p = new Projet();
        p.setNom("Projet Nouveau");

        when(projetRepository.save(p)).thenReturn(p);

        var saved = projetService.saveProjet(p);

        assertNotNull(saved);
        assertEquals("Projet Nouveau", saved.getNom());
        verify(projetRepository).save(p);
    }
}
