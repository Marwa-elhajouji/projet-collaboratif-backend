
INSERT INTO utilisateur (nom, email, mot_de_passe) VALUES 
('Alice Martin', 'alice@mail.com', 'alice123'),
('Bob Durand', 'bob@mail.com', 'bob123');


INSERT INTO projet (nom, description, date_debut, date_fin, utilisateur_id) VALUES
('Projet A', 'Gestion interne', '2024-01-01', '2024-06-01', 1),
('Projet B', 'Développement client', '2024-02-01', '2024-07-15', 2);


INSERT INTO tache (titre, description, date_debut, date_fin, statut, projet_id, utilisateur_id) VALUES
('Analyse besoin', 'Analyser besoins client', '2024-01-10', '2024-01-15', 'A_FAIRE', 1, 1),
('Développement module X', 'Coder module', '2024-02-01', '2024-03-01', 'EN_COURS', 2, 2);
