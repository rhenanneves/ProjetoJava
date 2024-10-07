CREATE TABLE mesas (
    id SERIAL PRIMARY KEY,
    numero INT NOT NULL, -- Número da mesa
    status VARCHAR(50) NOT NULL, -- Status pode ser 'ocupada', 'reservada', etc.
    cliente VARCHAR(100) -- Nome do cliente que fez a reserva (opcional)
);

CREATE TABLE pratos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL, -- Nome do prato
    descricao TEXT, -- Descrição do prato
    preco DECIMAL(10, 2) NOT NULL, -- Preço do prato
    mesa_id INT REFERENCES mesas(id) -- Chave estrangeira para associar o prato à mesa
);

CREATE TABLE comandas (
    id SERIAL PRIMARY KEY,
    mesa_id INT REFERENCES mesas(id), -- Chave estrangeira para associar a comanda à mesa
    total DECIMAL(10, 2), -- Total da comanda
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Data e hora da criação da comanda
);

CREATE TABLE funcionarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL, -- Nome do funcionário
    cargo VARCHAR(50), -- Cargo do funcionário (ex.: garçom, cozinheiro, gerente, etc.)
    salario DECIMAL(10, 2), -- Salário do funcionário
    data_admissao DATE NOT NULL -- Data de admissão
);



CREATE TABLE bebidas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL
);
