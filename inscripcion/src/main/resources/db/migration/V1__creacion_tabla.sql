CREATE TABLE inscripciones(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuarioId BIGINT NOT NULL,
    torneoId BIGINT NOT NULL,
    juegoId BIGINT NOT NULL,
    username VARCHAR(255) NOT NULL,
    region VARCHAR(255) NOT NULL,
    nombreTorneo VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    versionJuego INTEGER,
    desarrollador VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    rolEquipo VARCHAR(255) NOT NULL,
    fechaInscripcion DATETIME NOT NULL,
    cupoConfirmado BOOLEAN NOT NULL
);