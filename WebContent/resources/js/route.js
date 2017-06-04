/**
 * 
 */
var appCliente = angular.module('apuestas', [ 'ngRoute' ]);

appCliente.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'Login.html',
		controller : 'login'
	});
} ]);
