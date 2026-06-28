CREATE TABLE patrocinadores(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    torneoId BIGINT NOT NULL,
    contacto VARCHAR(255) NOT NULL,
    nombreTorneo VARCHAR(255) NOT NULL,
    nombreEmpresa VARCHAR(255) NOT NULL
);