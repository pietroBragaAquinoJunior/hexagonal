CREATE TABLE viagem (
    id UUID DEFAULT random_uuid() PRIMARY KEY,
    destino VARCHAR(150) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL
);

CREATE TABLE pessoa_viagem (
    pessoa_id UUID NOT NULL,
    viagem_id UUID NOT NULL,

    PRIMARY KEY (pessoa_id, viagem_id),
    
    -- Chaves Estrangeiras
    FOREIGN KEY (pessoa_id) REFERENCES pessoa (id) ON DELETE CASCADE,
    FOREIGN KEY (viagem_id) REFERENCES viagem (id) ON DELETE CASCADE
);

INSERT INTO viagem (id, destino, data_inicio, data_fim) VALUES
('8a67f0bc-5859-4834-9c24-ea7db9cafb4c','Paris', '2025-05-01', '2025-05-10');

INSERT INTO viagem (id, destino, data_inicio, data_fim) VALUES
('33522472-0b36-48ff-b90e-1d66cdea00ef','França', '2025-02-01', '2025-03-10');

INSERT INTO pessoa_viagem (pessoa_id, viagem_id) VALUES
('07f86fcd-1d50-40f6-b102-4114c263306b','8a67f0bc-5859-4834-9c24-ea7db9cafb4c');

INSERT INTO pessoa_viagem (pessoa_id, viagem_id) VALUES
('07f86fcd-1d50-40f6-b102-4114c263306b','33522472-0b36-48ff-b90e-1d66cdea00ef'); 

INSERT INTO pessoa_viagem (pessoa_id, viagem_id) VALUES
('1955f7c7-67d4-4dc6-a63a-364287511c0d','8a67f0bc-5859-4834-9c24-ea7db9cafb4c');

INSERT INTO pessoa_viagem (pessoa_id, viagem_id) VALUES
('b05d87a4-6a63-4849-a18d-42f410b720be','33522472-0b36-48ff-b90e-1d66cdea00ef');