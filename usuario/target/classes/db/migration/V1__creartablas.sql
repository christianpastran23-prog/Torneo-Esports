CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(255) NOT NULL,
                          region VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          puntaje INT NOT NULL
);
