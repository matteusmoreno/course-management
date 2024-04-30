CREATE TABLE students (
    id BINARY(16) NOT NULL,
    name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    age INT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    cep VARCHAR(10),
    logradouro VARCHAR(255),
    bairro VARCHAR(255),
    localidade VARCHAR(255),
    uf VARCHAR(2),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME,
    active BOOLEAN,

    PRIMARY KEY (id)
);
