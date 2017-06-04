/**
 * 
 */
var appUser = angular.module('usuarios', [ 'ngRoute', 'ngCookies' ]);

appUser.service('usuarios', function($http, $cookies, $location) {
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
	
	this.registrarUsuario = function(usuario) {
		return $http({
			url : 'http://localhost:8080/BetUdeA/BetUdeA/usuarios',
			method : 'POST',
			params : {
				nombreUsuario : usuario.nombreUsuario,
				tipoDocumento : usuario.tipoDocumento,
				numeroDocumento : usuario.numeroDocumento,
				nombres : usuario.nombres,
				apellidos : usuario.apellidos,
				fechaNacimiento : usuario.fechaNacimiento,
				email : usuario.email,
				password : usuario.password
			}
		});
	}
	
});

appUser.controller('login', function($scope, $location, usuarios,$cookies) {
	$scope.nombreUsuario = '';
	$scope.passwd = '';
	$scope.login = function() {
		console.log(nombreUsuario);
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
	$scope.Register = function() {
		$location.url('/registrarse');
		}		
});


appUser.controller('Registro',
		function($scope, $location, usuarios) {
			$scope.usuario = {
				nombreUsuario : '',
				tipoDocumento : '',
				numeroDocumento : '',
				nombres : '',
				apellidos : '',
				fechaNacimiento: '',
				email : '',
				password : ''
			};
			$scope.guardar = function() {
				//NO estoy seguro de que asi se modifique la fecha para mandarla por la url
				console.log($scope.usuario.fechaNacimiento.toLocaleDateString())
				var d=($scope.usuario.fechaNacimiento.toLocaleDateString()).slice(0, 10).split('-');
				$scope.usuario.fechaNacimiento=d[2]+'-'+d[1]+'-'+d[0];
				usuarios.registrarUsuario($scope.usuario).then(function success(data) {
					alert('Cliente creado');
					$location.url('/');
				});
					}
			$scope.RegisterBack = function() {
				$location.url('/');
				}	
		});


appUser.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'Login.html',
		controller : 'login'
	});
	$routeProvider.when('/resultados', {
		templateUrl : 'Resultados.html',
		controller : 'Resultados'
	});
	$routeProvider.when('/registrarse', {
		templateUrl : 'Registro.html',
		controller : 'Registro'
	});
} ]);
