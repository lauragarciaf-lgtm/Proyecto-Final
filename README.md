# Rutas Turisticas - Aplicacion Web Fullstack

Proyecto para la evaluacion final de **Tecnicas de Programacion** (Universidad de Antioquia).

Es una aplicacion web completa (fullstack) que permite gestionar rutas de transporte
turistico alrededor del mundo, con:

- **Backend**: API RESTful con **Spring Boot + JPA** (base de datos H2 en memoria).
- **Frontend**: cliente web con **HTML, CSS y AngularJS**, con un mapa de Leaflet.

---

## 1. Estructura del proyecto

```
rutas-turisticas/
├── backend/                         # API REST (Spring Boot)
│   ├── pom.xml                      # Dependencias de Maven
│   └── src/main/
│       ├── java/com/uao/rutas/
│       │   ├── RutasTuristicasApplication.java   # Clase de arranque
│       │   ├── config/              # Configuracion (CORS y Swagger)
│       │   ├── model/               # Entidades (Tipo, Pais, Ciudad, Ruta, Parada)
│       │   ├── repository/          # Acceso a datos (Spring Data JPA)
│       │   ├── service/             # Logica de negocio
│       │   └── controller/          # Endpoints REST
│       └── resources/
│           ├── application.properties   # Configuracion (puerto, BD, etc.)
│           └── data.sql                 # Datos de prueba
│
└── frontend/                        # Cliente web (AngularJS)
    ├── index.html                   # Pagina principal
    ├── css/styles.css               # Estilos
    └── js/
        ├── app.js                   # Modulo de AngularJS
        ├── services/apiService.js   # Llamadas a la API
        └── controllers/mainController.js  # Logica de la pantalla
```

Esta organizacion sigue una **arquitectura por capas** (modelo - repositorio -
servicio - controlador), que es la buena practica sugerida para separar
responsabilidades.

---

## 2. Modelo de datos

| Tabla    | Campos                                                        |
|----------|--------------------------------------------------------------|
| Tipo     | id, nombre                                                   |
| Pais     | id, nombre, codigoAlfa2                                      |
| Ciudad   | id, nombre, idPais, longitud, latitud                       |
| Ruta     | id, nombre, idTipo, idCiudad, descripcion                   |
| Parada   | id, nombre, orden, idRuta, longitud, latitud, tiempo, descripcion |

Relaciones: un Pais tiene muchas Ciudades; una Ciudad tiene muchas Rutas; un Tipo
clasifica muchas Rutas; una Ruta tiene muchas Paradas.

---

## 3. Requisitos previos

- **Java 17** o superior (`java -version`)
- **Maven 3.8+** (`mvn -version`)
- Un navegador web moderno (Chrome, Firefox, Edge...)

No es necesario instalar ninguna base de datos: se usa **H2 en memoria**.

---

## 4. Como ejecutar el backend

Desde una terminal, dentro de la carpeta `backend`:

```bash
cd backend
mvn spring-boot:run
```

Cuando termine de arrancar, la API queda disponible en `http://localhost:8080`.

Recursos utiles del backend:

- **Documentacion Swagger**: http://localhost:8080/swagger-ui/index.html
- **Consola de la base de datos H2**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:rutasdb`
  - Usuario: `sa`  (sin contrasena)

Los datos de prueba (ciudades, la ruta del Sena en Paris, etc.) se cargan solos
al iniciar.

---

## 5. Como ejecutar el frontend

El frontend son archivos estaticos. Debe servirse con un servidor web sencillo
(abrirlo con doble clic NO funciona bien por las restricciones del navegador).

**Opcion A - Con Python** (si lo tienes instalado):

```bash
cd frontend
python -m http.server 5500
```

Luego abre en el navegador: `http://localhost:5500`

**Opcion B - Con Visual Studio Code:**

1. Instala la extension **Live Server**.
2. Abre la carpeta `frontend`.
3. Clic derecho sobre `index.html` -> "Open with Live Server".

> Importante: el backend debe estar corriendo en el puerto 8080 antes de usar
> el frontend. Si cambias el puerto o el host de la API, ajusta la constante
> `API_URL` en `frontend/js/app.js`.

---

## 6. Endpoints de la API

### Tipo  (`/api/tipos`)
| Metodo | Ruta              | Descripcion              |
|--------|-------------------|--------------------------|
| GET    | /api/tipos/       | Listar todos             |
| GET    | /api/tipos/{id}   | Obtener uno por id       |
| POST   | /api/tipos/       | Crear                    |
| PUT    | /api/tipos/       | Actualizar (id en cuerpo)|
| DELETE | /api/tipos/{id}   | Eliminar                 |

### Pais  (`/api/paises`)  — mismas operaciones que Tipo.
### Ciudad  (`/api/ciudades`)  — mismas operaciones que Tipo.

### Ruta  (`/api/rutas`)
Ademas del CRUD estandar:
| Metodo | Ruta                          | Descripcion                  |
|--------|-------------------------------|------------------------------|
| GET    | /api/rutas/ciudad/{idCiudad}  | Listar las rutas de una ciudad |

### Parada  (`/api/paradas`)
Ademas del CRUD estandar:
| Metodo | Ruta                        | Descripcion                            |
|--------|-----------------------------|----------------------------------------|
| GET    | /api/paradas/ruta/{idRuta}  | Listar las paradas de una ruta (por orden) |

---

## 7. Funcionalidad del cliente web

Tal como pide el enunciado, el cliente permite:

1. Listar las ciudades y seleccionar una.
2. Listar las rutas de la ciudad seleccionada.
3. Agregar, modificar y eliminar rutas de esa ciudad.
4. Seleccionar una ruta y listar sus paradas ordenadas por el campo *orden*.
5. Agregar, modificar y eliminar paradas de la ruta seleccionada.
6. Ver las paradas dibujadas sobre un mapa (Leaflet / OpenStreetMap).

---

## 8. Subir a un repositorio (Git)

```bash
cd rutas-turisticas
git init
git add .
git commit -m "Proyecto Rutas Turisticas - evaluacion final"
git branch -M main
git remote add origin <URL-DE-TU-REPOSITORIO>
git push -u origin main
```

El archivo `.gitignore` ya excluye la carpeta `target/` y archivos temporales.
