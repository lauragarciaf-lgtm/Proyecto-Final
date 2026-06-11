/**
 * mainController.js
 * -----------------------------------------------------------------------------
 * Controlador principal de la aplicacion.
 *
 * Un "controller" en AngularJS conecta los datos (variables en $scope) con la
 * vista (index.html). Todo lo que se guarde en $scope queda disponible en el
 * HTML, y los botones del HTML llaman a las funciones definidas aqui.
 *
 * Flujo de la aplicacion (segun el enunciado):
 *   1. Al cargar, se listan las ciudades.
 *   2. Al seleccionar una ciudad, se listan sus rutas.
 *   3. Se pueden agregar / editar / eliminar rutas.
 *   4. Al seleccionar una ruta, se listan sus paradas (ordenadas por orden)
 *      y se dibujan en un mapa.
 *   5. Se pueden agregar / editar / eliminar paradas.
 * -----------------------------------------------------------------------------
 */
app.controller('MainController', function ($scope, ApiService) {

    // ===================== ESTADO (variables de la pantalla) =====================
    $scope.ciudades = [];          // lista de ciudades del panel izquierdo
    $scope.tipos = [];             // lista de tipos (para mostrar el nombre del tipo)
    $scope.rutas = [];             // rutas de la ciudad seleccionada
    $scope.paradas = [];           // paradas de la ruta seleccionada

    $scope.ciudadSeleccionada = null;
    $scope.rutaSeleccionada = null;

    // Objetos que usan los formularios de creacion/edicion.
    $scope.rutaForm = null;        // ruta que se esta creando o editando
    $scope.paradaForm = null;      // parada que se esta creando o editando

    var mapa = null;               // referencia al mapa de Leaflet
    var capaMarcadores = null;     // capa donde se ponen los marcadores de las paradas

    // ===================== INICIALIZACION =====================
    // Se ejecuta automaticamente al cargar la pagina.
    function init() {
        // Cargar los tipos (Bus, Fluvial, ...) para poder mostrar su nombre.
        ApiService.tipos.listar().then(function (resp) {
            $scope.tipos = resp.data;
        });
        // Cargar las ciudades.
        ApiService.ciudades.listar().then(function (resp) {
            $scope.ciudades = resp.data;
        }, function () {
            alert('No se pudo conectar con el backend. Verifica que este corriendo en el puerto 8080.');
        });
    }

    // Devuelve el nombre de un tipo a partir de su id (para mostrarlo en la tabla).
    $scope.nombreTipo = function (idTipo) {
        for (var i = 0; i < $scope.tipos.length; i++) {
            if ($scope.tipos[i].id === idTipo) {
                return $scope.tipos[i].nombre;
            }
        }
        return '';
    };

    // ===================== CIUDADES =====================
    // Se llama al hacer clic en una ciudad del panel izquierdo.
    $scope.seleccionarCiudad = function (ciudad) {
        $scope.ciudadSeleccionada = ciudad;
        $scope.rutaSeleccionada = null;   // se reinicia la ruta
        $scope.paradas = [];
        $scope.rutaForm = null;
        $scope.paradaForm = null;
        cargarRutas();
        limpiarMapa();
    };

    // ===================== RUTAS =====================
    // Carga las rutas de la ciudad seleccionada llamando a la API.
    function cargarRutas() {
        ApiService.rutas.listarPorCiudad($scope.ciudadSeleccionada.id).then(function (resp) {
            $scope.rutas = resp.data;
        });
    }

    // Prepara el formulario para agregar una ruta nueva.
    $scope.nuevaRuta = function () {
        $scope.rutaForm = {
            id: null,
            nombre: '',
            idTipo: $scope.tipos.length ? $scope.tipos[0].id : null,
            idCiudad: $scope.ciudadSeleccionada.id,
            descripcion: ''
        };
    };

    // Prepara el formulario para editar una ruta existente.
    $scope.editarRuta = function (ruta) {
        // Se copia el objeto (angular.copy) para no modificar la tabla mientras se edita.
        $scope.rutaForm = angular.copy(ruta);
    };

    // Cancela el formulario de ruta.
    $scope.cancelarRuta = function () {
        $scope.rutaForm = null;
    };

    // Guarda la ruta: si tiene id -> actualiza (PUT); si no -> crea (POST).
    $scope.guardarRuta = function () {
        var peticion;
        if ($scope.rutaForm.id) {
            peticion = ApiService.rutas.actualizar($scope.rutaForm);
        } else {
            peticion = ApiService.rutas.crear($scope.rutaForm);
        }
        peticion.then(function () {
            $scope.rutaForm = null;
            cargarRutas();
        }, function () {
            alert('Ocurrio un error al guardar la ruta.');
        });
    };

    // Elimina una ruta (pide confirmacion).
    $scope.eliminarRuta = function (ruta) {
        if (!confirm('Eliminar la ruta "' + ruta.nombre + '"?')) {
            return;
        }
        ApiService.rutas.eliminar(ruta.id).then(function () {
            // Si la ruta eliminada estaba seleccionada, limpiamos las paradas.
            if ($scope.rutaSeleccionada && $scope.rutaSeleccionada.id === ruta.id) {
                $scope.rutaSeleccionada = null;
                $scope.paradas = [];
                limpiarMapa();
            }
            cargarRutas();
        });
    };

    // ===================== PARADAS =====================
    // Se llama al hacer clic en una ruta: carga sus paradas y dibuja el mapa.
    $scope.seleccionarRuta = function (ruta) {
        $scope.rutaSeleccionada = ruta;
        $scope.paradaForm = null;
        cargarParadas();
    };

    // Carga las paradas de la ruta seleccionada (la API ya las trae ordenadas).
    function cargarParadas() {
        ApiService.paradas.listarPorRuta($scope.rutaSeleccionada.id).then(function (resp) {
            $scope.paradas = resp.data;
            dibujarMapa();
        });
    }

    // Prepara el formulario para agregar una parada nueva.
    // El orden sugerido es el siguiente numero disponible.
    $scope.nuevaParada = function () {
        var siguienteOrden = $scope.paradas.length + 1;
        $scope.paradaForm = {
            id: null,
            nombre: '',
            orden: siguienteOrden,
            idRuta: $scope.rutaSeleccionada.id,
            latitud: null,
            longitud: null,
            tiempo: null,
            descripcion: ''
        };
    };

    // Prepara el formulario para editar una parada existente.
    $scope.editarParada = function (parada) {
        $scope.paradaForm = angular.copy(parada);
    };

    // Cancela el formulario de parada.
    $scope.cancelarParada = function () {
        $scope.paradaForm = null;
    };

    // Guarda la parada: actualiza si tiene id, crea si no.
    $scope.guardarParada = function () {
        var peticion;
        if ($scope.paradaForm.id) {
            peticion = ApiService.paradas.actualizar($scope.paradaForm);
        } else {
            peticion = ApiService.paradas.crear($scope.paradaForm);
        }
        peticion.then(function () {
            $scope.paradaForm = null;
            cargarParadas();
        }, function () {
            alert('Ocurrio un error al guardar la parada.');
        });
    };

    // Elimina una parada (pide confirmacion).
    $scope.eliminarParada = function (parada) {
        if (!confirm('Eliminar la parada "' + parada.nombre + '"?')) {
            return;
        }
        ApiService.paradas.eliminar(parada.id).then(function () {
            cargarParadas();
        });
    };

    // ===================== MAPA (Leaflet) =====================
    // Dibuja en el mapa las paradas de la ruta seleccionada, unidas por una linea.
    function dibujarMapa() {
        // Crear el mapa solo la primera vez.
        if (mapa === null) {
            mapa = L.map('mapa').setView([20, 0], 2);
            // Capa base de OpenStreetMap (mapa gratuito).
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '© OpenStreetMap contributors'
            }).addTo(mapa);
            capaMarcadores = L.layerGroup().addTo(mapa);
        }

        capaMarcadores.clearLayers();   // borrar marcadores anteriores

        var puntos = [];
        $scope.paradas.forEach(function (parada) {
            if (parada.latitud != null && parada.longitud != null) {
                var punto = [parada.latitud, parada.longitud];
                puntos.push(punto);
                // Marcador con un texto emergente (popup) que muestra el nombre.
                L.marker(punto)
                    .bindPopup('<b>' + parada.orden + '. ' + parada.nombre + '</b>')
                    .addTo(capaMarcadores);
            }
        });

        // Si hay puntos, dibujar una linea entre ellos y centrar el mapa.
        if (puntos.length > 0) {
            L.polyline(puntos, { color: '#2563eb' }).addTo(capaMarcadores);
            mapa.fitBounds(puntos, { padding: [40, 40] });
        }

        // Leaflet a veces necesita recalcular el tamano cuando el div estaba oculto.
        setTimeout(function () {
            if (mapa) { mapa.invalidateSize(); }
        }, 200);
    }

    // Limpia los marcadores del mapa.
    function limpiarMapa() {
        if (capaMarcadores) {
            capaMarcadores.clearLayers();
        }
    }

    // Arrancar la aplicacion.
    init();
});
