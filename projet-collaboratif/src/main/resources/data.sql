
INSERT INTO utilisateur (nom, email, mot_de_passe,role_id) VALUES 
('Marwa Elhajouji', 'marwa@mail.com', '78965',1),
('Claire Dubois', 'claire.dubois@mail.com', 'abcdef',2),
('Julien Lefevre', 'julien.lefevre@mail.com', 'pass123',2),
('Antoine Girard', 'antoine.girard@mail.com', '7854',2);


INSERT INTO projet (nom, description, date_debut, date_fin, utilisateur_id) VALUES
('Portail RH', 'Développement d\'un portail de gestion RH', '2024-01-01', '2024-06-30', 1),
('Application mobile', 'Création d\'une app pour les clients', '2024-02-15', '2024-09-01', 2),
('Refonte site web', 'Modernisation du site institutionnel', '2024-03-01', '2024-08-31', 3);



INSERT INTO tache (titre, description, date_debut, date_fin, statut, projet_id, utilisateur_id) VALUES
('Étude de marché', 'Analyser le besoin côté RH', '2024-01-05', '2024-01-12', 'A_FAIRE', 1, 1),
('Développement backend', 'Implémenter API en Java', '2024-03-15', '2024-04-30', 'A_FAIRE', 1, 1),
('UI Design', 'Créer le design visuel', '2024-04-01', '2024-05-01', 'EN_COURS', 3, 3),
('Recette client', 'Tester le livrable final', '2024-06-15', '2024-06-30', 'TERMINEE', 1, 4);



