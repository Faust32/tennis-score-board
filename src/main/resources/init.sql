INSERT INTO players (id, name) values (1, 'Jannik Sinner');
INSERT INTO players (id, name) values (2, 'Novak Djokovic');
INSERT INTO players (id, name) values (3, 'Jimmy Connors');
INSERT INTO players (id, name) values (4, 'John Newcombe');
INSERT INTO players (id, name) values (5, 'Ivan Lendl');
INSERT INTO players (id, name) values (6, 'Boris Becker');
INSERT INTO players (id, name) values (7, 'Jim Courier');
INSERT INTO players (id, name) values (8, 'Marat Safin');
INSERT INTO players (id, name) values (9, 'Roger Federer');
INSERT INTO players (id, name) values (10, 'Rafael Nadal');
ALTER TABLE players ALTER COLUMN id RESTART WITH 11;

INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 1, 2, 1);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 3, 2, 2);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 3, 1, 3);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 1, 4, 4);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 5, 3, 3);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 2, 4, 2);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 5, 7, 7);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 5, 3, 3);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 9, 5, 5);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 10, 1, 1);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 3, 4, 4);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 8, 6, 6);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 7, 9, 9);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 3, 10, 10);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 4, 7, 7);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 1, 5, 5);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 3, 10, 3);
INSERT INTO matches (id, player1, player2, winner) values (random_uuid(), 9, 8, 8);


