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

appUser.service('resultados', function($http, $location) {
	this.allGames = function(season) {
		return $http({
			url : 'https://www.mysportsfeeds.com/api/feed/pull/nba/'+season+'/full_game_schedule.json',
			method : 'GET',
			headers: {
				"Authorization": "Basic " + btoa("ceballos1019" + ":" + "1036954574")
			}
		});
	}
	this.currentSeason = function(date) {
		return $http({
			url : 'https://www.mysportsfeeds.com/api/feed/pull/nba/current_season.json',
			method : 'GET',
			params: {
				fordate : date
			},
			headers: {
				"Authorization": "Basic " + btoa("ceballos1019" + ":" + "1036954574")
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


appUser.controller('Registro', function($scope, $location, usuarios) {
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

appUser.controller('Resultados', function($scope, $location,resultados) {
	//Obtener la fecha y modificarla para mandarla como parametro 
	var date= new Date();
	date= (date.toISOString()).slice(0, 10).split('-');
	$scope.fecha=date[0]+'-'+date[1]+'-'+date[2];
	date=date[0]+date[1]+date[2];
	var season;
//Obtenemos la temporada actual para mandarla como parametro
	resultados.currentSeason(date).then(function success(data){
     season=data.data.currentseason.season["0"].details.slug;
     //Obtenemos todos los juegos de esa temporada
     resultados.allGames(season).then(function success(data) {
    	  $scope.games=data.data.fullgameschedule.gameentry;
    	console.log($scope.games);
     });	
	});
	
	
	      
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
