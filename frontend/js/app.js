/**
 * app.js
 * -----------------------------------------------------------------------------
 * Modulo principal de la aplicacion AngularJS.
 *
 * AngularJS (version 1.x) organiza el codigo en "modulos". Aqui creamos el
 * modulo 'rutasApp', que es el que se enlaza con el <html> en index.html
 * mediante el atributo ng-app="rutasApp".
 *
 * Tambien definimos una constante con la URL base de la API del backend.
 * Si el backend corre en otra direccion, solo se cambia aqui.
 * -----------------------------------------------------------------------------
 */
var app = angular.module('rutasApp', []);

// URL base donde responde la API de Spring Boot.
app.constant('API_URL', 'http://localhost:8080/api');
