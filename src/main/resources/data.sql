-- =========================
-- USUARIOS
-- =========================
INSERT INTO usuarios (id, username, password, activo, fecha_registro, email, role)
VALUES
(1, 'admin', 'admin123', true, '2024-01-01', 'admin@email.com', 'ADMIN'),
(2, 'maria', 'maria123', true, '2024-02-01', 'maria@email.com', 'USUARIO');

-- =========================
-- PERFILES (1:1 con Usuario)
-- =========================
INSERT INTO perfiles (id, nombre, apellido, fecha_nacimiento, pais, biografia, usuario_id)
VALUES
(1, 'Administrador', 'Sistema', '1990-01-01', 'España', 'Perfil administrador', 1),
(2, 'María', 'López', '1998-05-10', 'México', 'Amante de la música pop', 2);

-- =========================
-- PLAYLISTS
-- =========================
INSERT INTO playlists (id, nombre, publica, fecha_creacion)
VALUES
(1, 'Favoritas Admin', true, '2024-03-01'),
(2, 'Pop Hits María', true, '2024-03-02');

-- =========================
-- RELACIÓN USUARIO_PLAYLIST (N:M)
-- =========================
INSERT INTO usuario_playlist (usuario_id, playlist_id)
VALUES
(1, 1),
(2, 2);

-- =========================
-- ÁLBUMES
-- =========================
INSERT INTO albumes (id, titulo, genero, fecha_lanzamiento, portada_url)
VALUES
(1, 'Greatest Hits', 'Pop', '2020-01-01', 'https://img.com/hits.jpg'),
(2, 'Rock Legends', 'Rock', '2019-06-15', 'https://img.com/rock.jpg');

-- =========================
-- CANCIONES (1:N con Álbum)
-- =========================
INSERT INTO canciones (id, titulo, duracion, genero, reproducciones, fecha_publicacion, album_id)
VALUES
(1, 'Pop Song 1', 180, 'Pop', 1000, '2020-01-10', 1),
(2, 'Pop Song 2', 200, 'Pop', 2000, '2020-02-10', 1),
(3, 'Rock Anthem', 240, 'Rock', 3000, '2019-07-01', 2);

-- =========================
-- ARTISTAS
-- =========================
INSERT INTO artistas (id, nombre, pais, fecha, biografia)
VALUES
(1, 'DJ Popstar', 'USA', '1990-05-10', 'Artista de música pop internacional'),
(2, 'Rock Hero', 'UK', '1985-03-22', 'Leyenda del rock clásico');

-- =========================
-- RELACIÓN ARTISTA_CANCION (N:M)
-- =========================
INSERT INTO artista_cancion (artista_id, cancion_id)
VALUES
(1, 1),
(1, 2),
(2, 3);

-- =========================
-- RELACIÓN PLAYLIST_CANCION (N:M)
-- =========================
INSERT INTO playlist_cancion (playlist_id, cancion_id)
VALUES
(1, 1),
(1, 3),
(2, 2);

-- =========================
-- RELACIÓN USUARIO_CANCION (FAVORITAS N:M)
-- =========================
INSERT INTO usuario_cancion (usuario_id, cancion_id)
VALUES
(2, 1),
(2, 2);
