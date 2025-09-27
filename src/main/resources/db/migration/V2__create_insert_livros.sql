CREATE TABLE livro (
    id UUID DEFAULT random_uuid() PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    pessoa_id UUID NOT NULL, 
    FOREIGN KEY (pessoa_id) REFERENCES pessoa (id) 
);

INSERT INTO livro (titulo, pessoa_id) VALUES ('Livro legal', '07f86fcd-1d50-40f6-b102-4114c263306b');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Livro legal', '1955f7c7-67d4-4dc6-a63a-364287511c0d');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Livro legal', 'b05d87a4-6a63-4849-a18d-42f410b720be');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Meu Malvado Favorito', '07f86fcd-1d50-40f6-b102-4114c263306b');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Meu Malvado Favorito', '1955f7c7-67d4-4dc6-a63a-364287511c0d');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Meu Malvado Favorito', '6cd7b268-7df0-40de-9779-bba26892b6b2');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Pequeno Principe', '07f86fcd-1d50-40f6-b102-4114c263306b');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Pequeno Principe', 'b05d87a4-6a63-4849-a18d-42f410b720be');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Maquiavel', '07f86fcd-1d50-40f6-b102-4114c263306b');
INSERT INTO livro (titulo, pessoa_id) VALUES ('Maquiavel', '1955f7c7-67d4-4dc6-a63a-364287511c0d');
