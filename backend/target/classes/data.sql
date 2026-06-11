-- ============================================================================
--  data.sql  -  Datos de prueba (se cargan automaticamente al iniciar la app)
-- ----------------------------------------------------------------------------
--  Este script inserta informacion de ejemplo para poder probar la aplicacion
--  sin tener que registrar todo a mano. Los datos coinciden con el ejemplo
--  que aparece en el enunciado del examen (ciudades, ruta de Paris, etc.).
-- ============================================================================

-- ----- TIPOS de ruta -----
INSERT INTO tipo (nombre) VALUES ('Bus');          -- id = 1
INSERT INTO tipo (nombre) VALUES ('Fluvial');      -- id = 2
INSERT INTO tipo (nombre) VALUES ('Caminata');     -- id = 3
INSERT INTO tipo (nombre) VALUES ('Bicicleta');    -- id = 4
INSERT INTO tipo (nombre) VALUES ('Tren');         -- id = 5

-- ----- PAISES -----
INSERT INTO pais (nombre, codigo_alfa2) VALUES ('Espana', 'ES');          -- id = 1
INSERT INTO pais (nombre, codigo_alfa2) VALUES ('Colombia', 'CO');        -- id = 2
INSERT INTO pais (nombre, codigo_alfa2) VALUES ('Mexico', 'MX');          -- id = 3
INSERT INTO pais (nombre, codigo_alfa2) VALUES ('Peru', 'PE');            -- id = 4
INSERT INTO pais (nombre, codigo_alfa2) VALUES ('Egipto', 'EG');          -- id = 5
INSERT INTO pais (nombre, codigo_alfa2) VALUES ('Japon', 'JP');           -- id = 6
INSERT INTO pais (nombre, codigo_alfa2) VALUES ('Francia', 'FR');         -- id = 7
INSERT INTO pais (nombre, codigo_alfa2) VALUES ('Italia', 'IT');          -- id = 8
INSERT INTO pais (nombre, codigo_alfa2) VALUES ('Estados Unidos', 'US');  -- id = 9

-- ----- CIUDADES (id_pais hace referencia a la tabla pais) -----
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Barcelona', 1, 2.1734, 41.3851);          -- id = 1
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Bogota', 2, -74.0721, 4.7110);            -- id = 2
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Cancun', 3, -86.8515, 21.1619);           -- id = 3
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Ciudad de Mexico', 3, -99.1332, 19.4326); -- id = 4
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Cusco', 4, -71.9675, -13.5320);           -- id = 5
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('El Cairo', 5, 31.2357, 30.0444);          -- id = 6
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Kioto', 6, 135.7681, 35.0116);            -- id = 7
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Madrid', 1, -3.7038, 40.4168);            -- id = 8
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Medellin', 2, -75.5636, 6.2518);          -- id = 9
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Nueva York', 9, -74.0060, 40.7128);       -- id = 10
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Paris', 7, 2.3522, 48.8566);              -- id = 11
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Roma', 8, 12.4964, 41.9028);              -- id = 12
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Tokio', 6, 139.6503, 35.6762);            -- id = 13
INSERT INTO ciudad (nombre, id_pais, longitud, latitud) VALUES ('Venecia', 8, 12.3155, 45.4408);           -- id = 14

-- ----- RUTAS (id_ciudad y id_tipo referencian a ciudad y tipo) -----
-- Ruta de Paris (ciudad 11), tipo Fluvial (tipo 2). Es la del ejemplo del PDF.
INSERT INTO ruta (nombre, id_tipo, id_ciudad, descripcion)
VALUES ('Crucero Historico por el Sena', 2, 11, 'Recorrido en barco por los principales monumentos de Paris'); -- id = 1

-- Algunas rutas adicionales para otras ciudades.
INSERT INTO ruta (nombre, id_tipo, id_ciudad, descripcion)
VALUES ('City Tour Centro Historico', 1, 9, 'Recorrido en bus por el centro de Medellin');                     -- id = 2
INSERT INTO ruta (nombre, id_tipo, id_ciudad, descripcion)
VALUES ('Ruta de los Templos', 3, 7, 'Caminata por los templos mas famosos de Kioto');                         -- id = 3

-- ----- PARADAS (id_ruta referencia a la tabla ruta) -----
-- Paradas de la ruta 1 (Crucero por el Sena), tal como en el ejemplo del PDF.
INSERT INTO parada (nombre, orden, id_ruta, longitud, latitud, tiempo, descripcion)
VALUES ('Embarcadero Torre Eiffel', 1, 1, 2.2931, 48.8592, 15, 'Punto de inicio a los pies de la torre');
INSERT INTO parada (nombre, orden, id_ruta, longitud, latitud, tiempo, descripcion)
VALUES ('Muelle del Museo de Orsay', 2, 1, 2.3265, 48.8606, 20, 'Avistamiento de la antigua estacion');
INSERT INTO parada (nombre, orden, id_ruta, longitud, latitud, tiempo, descripcion)
VALUES ('Isla de la Cite - Notre Dame', 3, 1, 2.3499, 48.8530, 25, 'Parada junto a la emblematica catedral');

-- Paradas de la ruta 2 (City Tour Medellin).
INSERT INTO parada (nombre, orden, id_ruta, longitud, latitud, tiempo, descripcion)
VALUES ('Plaza Botero', 1, 2, -75.5685, 6.2524, 30, 'Esculturas de Fernando Botero');
INSERT INTO parada (nombre, orden, id_ruta, longitud, latitud, tiempo, descripcion)
VALUES ('Parque de las Luces', 2, 2, -75.5705, 6.2503, 20, 'Plaza Cisneros y sus columnas de luz');
