CREATE TABLE pessoa (
    id UUID DEFAULT random_uuid() PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    cpf VARCHAR(30) NOT NULL
);

INSERT INTO pessoa (id, nome, email, cpf) VALUES ('07f86fcd-1d50-40f6-b102-4114c263306b','Maria Silva', 'maria@gmail.com', 123456);
INSERT INTO pessoa (id, nome, email, cpf) VALUES ('1955f7c7-67d4-4dc6-a63a-364287511c0d','João Souza', 'joao@outlook.com', 222555);
INSERT INTO pessoa (id, nome, email, cpf) VALUES ('b05d87a4-6a63-4849-a18d-42f410b720be','Ana Oliveira', 'oliveira@hotmail.com', 678444);
INSERT INTO pessoa (id, nome, email, cpf) VALUES ('6cd7b268-7df0-40de-9779-bba26892b6b2','Carlos Pereira', 'carlos@proton.me', 999123);
