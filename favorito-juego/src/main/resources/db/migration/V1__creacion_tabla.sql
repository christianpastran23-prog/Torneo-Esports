CREATE TABLE favoritos(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuarioId BIGINT NOT NULL,
    juegoId BIGINT NOT NULL,
    username VARCHAR(255) NOT NULL,
    region VARCHAR(255) NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    versionJuego INTEGER,
    desarrollador VARCHAR(255) NOT NULL,
    horasDeJuego INTEGER NOT NULL
);