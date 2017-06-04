/**
 * 
 */
var appCliente = angular.module('clientes', [ 'ngRoute', 'ngCookies' ]);

appCliente.service('usuarios', function($http, $cookies, $location) {
	this.autenticar = function(usuario, passwd) {
		return $http({
			url : 'http://localhost:8080/BetUdeA/BetUdeA/usuarios/autenticar',
			method : 'POST',
			params : {
				nombreUsuario : usuario,
				password : passwd
			}
		});
	}
});
	
appCliente.controller('login', function($scope, $location, usuarios,$cookies) {
	$scope.nombreUsuario = '';
	$scope.passwd = '';
	$scope.login = function() {
		usuarios.autenticar($scope.nombreUsuario, $scope.passwd).then(
				function success(data) {
					console.log(data);
					alert(data.data);
					if (data.data != '') {
						alert(data.data);
						$scope.nombreUsuario = '';
						$scope.passwd = '';
						return;
					}
					$cookies.nombreUsuario = $scope.nombreUsuario;
					$location.url('/resultados');
				},

				function failure(data) {
					console.log(data);
				})
	} 
});

appCliente.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'Login.html',
		controller : 'login'
	});
	$routeProvider.when('/resultados', {
		templateUrl : 'Resultados.html',
		controller : 'Resultados'
	});
} ]);
