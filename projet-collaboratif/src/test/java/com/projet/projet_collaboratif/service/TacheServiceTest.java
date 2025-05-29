package com.projet.projet_collaboratif.service;

import com.projet.projet_collaboratif.model.Tache;
import com.projet.projet_collaboratif.model.Statut;
import com.projet.projet_collaboratif.repository.TacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TacheServiceTest {

    private TacheRepository tacheRepository;
    private TacheService tacheService;

    @BeforeEach
    void setUp() {
        tacheRepository = mock(TacheRepository.class);
        tacheService = new TacheService();
        tacheService.setTacheRepository(tacheRepository);
    }

    @Test
    void testGetAllTaches() {
        Tache t1 = new Tache();
        t1.setTitre("Tâche 1");

        Tache t2 = new Tache();
        t2.setTitre("Tâche 2");

        when(tacheRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        var result = tacheService.getAllTaches();

        assertEquals(2, result.size());
        verify(tacheRepository, times(1)).findAll();
    }

    @Test
    void testGetTacheById() {
        Tache t = new Tache();
        t.setId(1L);
        t.setTitre("Tâche test");

        when(tacheRepository.findById(1L)).thenReturn(Optional.of(t));

        var result = tacheService.getTacheById(1L);

        assertTrue(result.isPresent());
        assertEquals("Tâche test", result.get().getTitre());
    }

    @Test
    void testSaveTache() {
        Tache t = new Tache();
        t.setTitre("Tâche à enregistrer");
        t.setStatut(Statut.A_FAIRE);
        t.setDateDebut(LocalDate.now());

        when(tacheRepository.save(t)).thenReturn(t);

        var saved = tacheService.saveTache(t);

        assertNotNull(saved);
        assertEquals("Tâche à enregistrer", saved.getTitre());
        verify(tacheRepository).save(t);
    }

    @Test
    void testDeleteTache() {
        doNothing().when(tacheRepository).deleteById(1L);
        tacheService.deleteTache(1L);
        verify(tacheRepository).deleteById(1L);
    }

    @Test
    void testGetTacheByIdNotFound() {
        when(tacheRepository.findById(888L)).thenReturn(Optional.empty());
        Optional<Tache> result = tacheService.getTacheById(888L);
        assertTrue(result.isEmpty());
}

}