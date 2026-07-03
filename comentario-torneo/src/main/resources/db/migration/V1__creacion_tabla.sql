CREATE TABLE comentarios(
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuarioId INT NOT NULL,
    torneoId INT NOT NULL,
    username VARCHAR(255) NOT NULL,
    region VARCHAR(255) NOT NULL,
    mensajeComentario VARCHAR(255) NOT NULL,
    nombreTorneo VARCHAR(255) NOT NULL
);