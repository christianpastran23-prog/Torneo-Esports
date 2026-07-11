# Plataforma de Gestión de Torneos de E-Sports

## Descripción del Proyecto (Contexto / Dominio)

Este proyecto es un ecosistema backend distribuido basado en arquitectura de microservicios, diseñado para gestionar de manera integral plataformas de torneos de E-Sports (videojuegos competitivos).

El sistema permite la administración completa del ciclo de vida de un torneo competitivo: desde la creación del evento, el registro de usuarios y la gestión del catálogo de videojuegos, hasta el control de inscripciones, registro de estadísticas (fichas de partidas y K/D), historial de juego, sistema de comentarios, notificaciones y la asignación de patrocinadores. Todo el tráfico es orquestado a través de un API Gateway y securizado mediante un servicio de autenticación dedicado.

## Nombres de los/las Estudiantes

* **\[CHRISTIAN PASTRAN]** - Estudiante de Programación, DuocUC.
* &#x20;Listado de Microservicios Implementados

El sistema está compuesto por una arquitectura robusta en Spring Boot 3.3.x y Java 21, dividida en los siguientes microservicios:

**Infraestructura y Seguridad:**

* `eureka-server` (Puerto 8761): Servidor de descubrimiento de servicios (Service Discovery).
* `gateway-service` (Puerto 8080): Punto de entrada único para los clientes y enrutamiento.
* `auth-service` (Puerto 8081): Gestión de credenciales, seguridad y control de acceso.

**Lógica de Negocio (Core):**

* `usuario` (Puerto 8082): Gestión de perfiles de jugadores, correos y puntajes globales.
* `inscripcion` (Puerto 8083): Control de cupos, confirmaciones y roles de equipo en torneos.
* `torneo` (Puerto 8084): Creación de torneos, fechas, estados y premios.
* `historial-juego` (Puerto 8085): Registro de horas jugadas por usuario en cada título.
* `notificacion-usuario` (Puerto 8086): Sistema de alertas (ej. confirmación de inscripciones).
* `juegos` (Puerto 8087): Catálogo maestro de videojuegos con versiones y categorías.
* `comentario-torneo` (Puerto 8088): Foro e interacción social dentro de cada torneo.
* `favorito-juego` (Puerto 8089): Listado personalizado de preferencias de cada jugador.
* `ficha-partida` (Puerto 8090): Estadísticas post-partida (Resultado, K/D, Puntos obtenidos).
* `torneo-patrocinador` (Puerto 8091): Enlace entre empresas patrocinadoras y torneos específicos.

## &#x20;Rutas Principales del Gateway

El `gateway-service` unifica las peticiones en el puerto `8080`. Todas las rutas base de las entidades principales se exponen bajo el prefijo `/api/`:

* **Autenticación:** `POST http://localhost:8080/auth/login`
* **Usuarios:** `GET / POST http://localhost:8080/api/usuarios`
* **Torneos:** `GET / POST http://localhost:8080/api/torneos`
* **Inscripciones:** `GET / POST http://localhost:8080/api/inscripciones`
* **Juegos:** `GET / POST http://localhost:8080/api/juegos`
* **Fichas de Partidas:** `GET / POST http://localhost:8080/api/ficha\\\_partidas`
* *(Las demás entidades siguen el mismo patrón: `/api/comentario-torneos`, `/api/favorito-juegos`, etc.)*

## Enlaces a la Documentación Swagger

La documentación interactiva de la API (OpenAPI/Swagger) está disponible para cada microservicio:



* **Local:** En este caso seria específicamente para usuarios `http://localhost:8082/swagger-ui.html`
* **Remota (Render):** [https://com-fullstack-usuario.onrender.com/swagger-ui.html](https://com-fullstack-usuario.onrender.com/swagger-ui.html)



## Enlaces principales en render

Para ocuparlo de forma remota deberá iniciar sesión en render dashboard, con la cuenta de GitHub con la dirección del respositorio exacto en el que se encuentra el proyecto, añadiendo el blueprint para indicar el nombre del archivo de configuración render.yml + aiven MySQL, se deberá crear una base de datos con MySQL, este le dará todas las credenciales que debe ocupar para poder conectarse de forma remota atravez de render, en render, deberá crear un envorinment group, con la contraseña, usuario y el link que lo dirigira hacia la base de datos, le deberá asignar a cada servicio ese envorinment group para que todos puedan conectarse correctamente a la base de datos 



https://com-fullstack-ficha-partida.onrender.com

https://com-fullstack-torneo-patrocinador.onrender.com

https://com-fullstack-inscripcion.onrender.com

https://com-fullstack-favorito-juego.onrender.com

https://com-fullstack-torneo.onrender.com

https://com-fullstack-usuario.onrender.com

https://com-fullstack-juegos.onrender.com

https://com-fullstack-historial-juego.onrender.com

https://com-fullstack-auth.onrender.com

https://com-fullstack-comentario-torneo.onrender.com

https://com-fullstack-notificacion-usuario.onrender.com

https://com-fullstack-gateway.onrender.com

