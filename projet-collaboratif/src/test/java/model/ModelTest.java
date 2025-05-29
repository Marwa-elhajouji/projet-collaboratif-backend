package com.projet.projet_collaboratif.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    @Test
    void shouldCreateUtilisateur() {
        Utilisateur u1 = new Utilisateur();
        u1.setId(1L);
        u1.setNom("Alice");
        u1.setEmail("alice@test.com");
        u1.setMotDePasse("secret");

        Utilisateur u2 = new Utilisateur();
        u2.setId(1L);
        u2.setNom("Alice");
        u2.setEmail("alice@test.com");
        u2.setMotDePasse("secret");

        assertEquals(u1, u2);
        assertEquals(u1.hashCode(), u2.hashCode());
        assertTrue(u1.toString().contains("Alice"));
    }

    @Test
    void shouldCreateProjet() {
        Projet p1 = new Projet();
        p1.setId(2L);
        p1.setNom("Project Test");

        Projet p2 = new Projet();
        p2.setId(2L);
        p2.setNom("Project Test");

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertTrue(p1.toString().contains("Project Test"));
    }

    @Test
    void shouldCreateTache() {
        LocalDate now = LocalDate.of(2025, 5, 30);

        Tache t1 = new Tache();
        t1.setId(3L);
        t1.setTitre("New Task");
        t1.setStatut(Statut.EN_COURS);
        t1.setDateDebut(now);

        Tache t2 = new Tache();
        t2.setId(3L);
        t2.setTitre("New Task");
        t2.setStatut(Statut.EN_COURS);
        t2.setDateDebut(now);

        assertEquals(t1, t2);
        assertEquals(t1.hashCode(), t2.hashCode());
        assertTrue(t1.toString().contains("New Task"));
    }

    @Test
    void shouldCreateRole() {
        Role role1 = new Role();
        role1.setId(4L);
        role1.setNom("ADMIN");

        Role role2 = new Role();
        role2.setId(4L);
        role2.setNom("ADMIN");

        assertEquals(role1, role2);
        assertEquals(role1.hashCode(), role2.hashCode());
        assertTrue(role1.toString().contains("ADMIN"));
    }

    @Test
    void shouldHandleDifferentUsers() {
        Utilisateur user1 = new Utilisateur();
        user1.setId(1L);
        user1.setNom("Alice");

        Utilisateur user2 = new Utilisateur();
        user2.setId(2L);
        user2.setNom("Bob");

        assertNotEquals(user1, user2);
    }

    @Test
    void shouldHandleDifferentProjets() {
        Projet p1 = new Projet();
        p1.setId(1L);

        Projet p2 = new Projet();
        p2.setId(2L);

        assertNotEquals(p1, p2);
    }

    @Test
    void shouldHandleDifferentRoles() {
        Role r1 = new Role();
        r1.setNom("USER");

        Role r2 = new Role();
        r2.setNom("ADMIN");

        assertNotEquals(r1, r2);
    }

    @Test
    void shouldHandleNullEquality() {
        Utilisateur user = new Utilisateur();
        assertNotEquals(null, user);
        assertNotEquals("", user);
    }
}
