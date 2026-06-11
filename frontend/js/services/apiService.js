/**
 * apiService.js
 * -----------------------------------------------------------------------------
 * Servicio de acceso a la API.
 *
 * Un "service" en AngularJS es una clase reutilizable. Aqui centralizamos
 * TODAS las llamadas HTTP al backend (usando $http). De esta forma, el
 * controlador no tiene que saber las URLs: solo llama a metodos con nombres
 * claros como ciudades.listar() o rutas.crear(ruta).
 *
 * Cada metodo devuelve una "promesa" ($http), que el controlador resuelve
 * con .then(...) cuando llega la respuesta del servidor.
 * -----------------------------------------------------------------------------
 */
app.service('ApiService', function ($http, API_URL) {

    // ----------------- CIUDADES -----------------
    this.ciudades = {
        listar: function () {
            return $http.get(API_URL + '/ciudades/');
        }
    };

    // ----------------- TIPOS (para mostrar el nombre del tipo de ruta) -----------------
    this.tipos = {
        listar: function () {
            return $http.get(API_URL + '/tipos/');
        }
    };

    // ----------------- RUTAS -----------------
    this.rutas = {
        // Lista solo las rutas de una ciudad (endpoint especial de la API).
        listarPorCiudad: function (idCiudad) {
            return $http.get(API_URL + '/rutas/ciudad/' + idCiudad);
        },
        crear: function (ruta) {
            return $http.post(API_URL + '/rutas/', ruta);
        },
        actualizar: function (ruta) {
            return $http.put(API_URL + '/rutas/', ruta);
        },
        eliminar: function (id) {
            return $http.delete(API_URL + '/rutas/' + id);
        }
    };

    // ----------------- PARADAS -----------------
    this.paradas = {
        // Lista las paradas de una ruta ya ordenadas por el campo orden.
        listarPorRuta: function (idRuta) {
            return $http.get(API_URL + '/paradas/ruta/' + idRuta);
        },
        crear: function (parada) {
            return $http.post(API_URL + '/paradas/', parada);
        },
        actualizar: function (parada) {
            return $http.put(API_URL + '/paradas/', parada);
        },
        eliminar: function (id) {
            return $http.delete(API_URL + '/paradas/' + id);
        }
    };
});
