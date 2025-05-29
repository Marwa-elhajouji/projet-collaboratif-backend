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
        p1.setNom("Projet A");

        Projet p2 = new Projet();
        p2.setNom("Projet B");

        when(projetRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        var result = projetService.getAllProjets();

        assertEquals(2, result.size());
        verify(projetRepository).findAll();
    }

    @Test
    void testGetProjetById() {
        Projet p = new Projet();
        p.setId(1L);
        p.setNom("Unique Projet");

        when(projetRepository.findById(1L)).thenReturn(Optional.of(p));

        var result = projetService.getProjetById(1L);

        assertTrue(result.isPresent());
        assertEquals("Unique Projet", result.get().getNom());
    }

    @Test
    void testSaveProjet() {
        Projet p = new Projet();
        p.setNom("To be saved");

        when(projetRepository.save(p)).thenReturn(p);

        var saved = projetService.saveProjet(p);

        assertNotNull(saved);
        assertEquals("To be saved", saved.getNom());
        verify(projetRepository).save(p);
    }

    @Test
    void testDeleteProjet() {
        doNothing().when(projetRepository).deleteById(1L);
        projetService.deleteProjet(1L);
        verify(projetRepository).deleteById(1L);
    }

    @Test
    void testGetProjetByIdNotFound() {
        when(projetRepository.findById(999L)).thenReturn(Optional.empty());
        Optional<Projet> result = projetService.getProjetById(999L);
        assertTrue(result.isEmpty());
}

}