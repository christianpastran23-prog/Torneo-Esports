CREATE TABLE torneos(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre_torneo VARCHAR(255) NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    estado VARCHAR(255) NOT NULL,
    premio_dolares INT
);