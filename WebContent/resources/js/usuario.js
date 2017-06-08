/**
 *
 */
var appUser = angular.module('usuarios', [ 'ngRoute', 'ngCookies',  ]);
var usuario;
var apuesta;
var equipo;     //Equipo seleccionado por el usuario
var local, guest;   //Cuotas para equipo local y equipo visitante
var premio;
var select=false;
var cuota = 0.0;

//Servicio para autenticar y registrar usuarios
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
	
	//Función para registrar usuario
	this.registrarUsuario = function(usuario) {
		return $http({
			url : 'http://localhost:8080/BetUdeA/BetUdeA/usuarios',
			method : 'POST',
			params : {
				nombreUsuario : usuario.nombreUsuario,
				tipoDocumento : 'CC',
				numeroDocumento : usuario.numeroDocumento,
				nombres : usuario.nombres,
				apellidos : usuario.apellidos,
				fechaNacimiento : usuario.fechaNacimiento,
				email : usuario.email,
				password : usuario.password
			}
		});
	}
	
	//Función para editar el correo de un usuario
	this.editarCorreo=function(cemail,password,nemail){
		return $http({
		url : 'http://localhost:8080/BetUdeA/BetUdeA/usuarios/editaremail',
		method : 'POST',
		params: {
			currentEmail: cemail,
			currentPassword: password,
			newEmail: nemail
		}
			});
	}
	//Función para editar la contraseña de un usuario
	this.editarContrasena=function(cemail,password,npassword){
		return $http({
		url : 'http://localhost:8080/BetUdeA/BetUdeA/usuarios/editarpassword',
		method : 'POST',
		params: {
			currentEmail: cemail,
			currentPassword: password,
			newPassword: npassword
		}
			});
	}

});
//Servicio que trae los resultados del API
appUser.service('resultados', function($http, $location, $cookies) {
	//Función que trae todos los juegos de una temporada
	this.allGames = function(season) {
		return $http({
			url : 'https://www.mysportsfeeds.com/api/feed/pull/nba/'+season+'/full_game_schedule.json',
			method : 'GET',
			headers: {
				"Authorization": "Basic " + btoa("ceballos1019" + ":" + "1036954574")
			}
		});
	}
	//Función que nos devuelve la temporada de la fecha actual
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
	//Función para validar el ingreso por la url
		this.validarEstado = function() {
			if($location.url() == '/'|| $location.url() == '/registrarse') {
				return true;
			}
			if (typeof ($cookies.nombreUsuario) == 'undefined'
					|| $cookies.nombreUsuario == "" ) {
		
				$location.url('/');
				return false;
			}
		
			
		}
	
});

//Servicio de los periodos de simulación
appUser.service('simulacion', function($http, $location) {
	//Función para registrar un perido de un usuario
	this.registrarPeriodo = function(usuario) {
		return $http({
			url : 'http://localhost:8080/BetUdeA/BetUdeA/periodos',
			method : 'POST',
			params : {
				nombreUsuario : usuario
			}
		});
	}
	//Función para consultar los periodos de un usuario
	this.consultarPeriodos = function(usuario) {
		return $http({
			url : 'http://localhost:8080/BetUdeA/BetUdeA/periodos',
			method : 'GET',
			params : {
				nombreUsuario : usuario
			}
		});
	}
	//Función para consultar el periodo activo de un usuario
	this.consultarPeriodoActivo = function(usuario) {
		return $http({
			url : 'http://localhost:8080/BetUdeA/BetUdeA/periodos/periodoactivo',
			method : 'GET',
			params : {
				nombreUsuario : usuario
			}
		});
	}
});

//Servicio para consultar y registrar una apuesta
appUser.service('apuestas', function($http, $location, $cookies) {
	//Función para registrar apuesto
	this.registrarApuesta = function(apuesta)
	{
		return $http({
			url: 'http://localhost:8080/BetUdeA/BetUdeA/apuestas',
			method: 'POST',
			params: {
				evento : 'Warriors vs Cavaliers',
				fecha : '2017-06-12',
				valor : apuesta.valor,
				cuota : apuesta.cuota,
				opcion : apuesta.opcion,
				usuario : $cookies.nombreUsuario
			}
		});
	}
	
	//Función para consultar apuestas
	this.consultarApuestas = function(periodoId) {
		return $http({
			url : 'http://localhost:8080/BetUdeA/BetUdeA/apuestas',
			method : 'GET',
			params : {
				periodo : periodoId
			}
		});
	}
});

//Controlador del login
appUser.controller('login', function($scope, $location, usuarios,$cookies) {
	
	$scope.nombreUsuario = '';
	$scope.passwd = '';
	//Función que utiliza el servicio para autenticar
	$scope.login = function() {
		usuarios.autenticar($scope.nombreUsuario, $scope.passwd).then(
				function success(data) {
					usuario=$scope.nombreUsuario;
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
					alert("Nombre de usuario o contraseña incorrecta");
				})
	};	
	
	//Función para dirigirse al registro
	$scope.Register = function() {
		$location.url('/registrarse');
		}		

});

//Controlador del registro
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
				//modificar la fecha para mandarla por la url
				var d=($scope.usuario.fechaNacimiento.toLocaleDateString()).slice(0, 10).split('/');
				$scope.usuario.fechaNacimiento=d[2]+'-'+d[1]+'-'+d[0];
				alert($scope.usuario.fechaNacimiento);
				usuarios.registrarUsuario($scope.usuario).then(function success(data) {
					alert('Cliente creado');
					$location.url('/');
				});
					}
			//Función para devolver al login
			$scope.RegisterBack = function() {
				$location.url('/');
				};
			
			
		});
//Controlador de resultados
appUser.controller('Resultados', function($scope, $location,$cookies,resultados, apuestas, simulacion) {
	//Cambiamos el color de fondo
	document.body.style.backgroundColor = "white";	
	//Obtener la fecha y modificarla para mandarla como parametro para obtener el nombre de la temporada 	
	var date= new Date();
	date= (date.toISOString()).slice(0, 10).split('-');
	$scope.fecha=date[0]+'-'+date[1]+'-'+date[2];
	date=date[0]+date[1]+date[2];
	var season;
	simulacion.consultarPeriodoActivo($cookies.nombreUsuario).then(function success(data){
		$scope.saldo = data.data.saldo;
	});
//Obtenemos la temporada actual para mandarla como parametro
	resultados.currentSeason(date).then(function success(data){
     season=data.data.currentseason.season["0"].details.slug;
     
     //Obtenemos todos los juegos de esa temporada
     resultados.allGames(season).then(function success(data) {
    	  $scope.games=data.data.fullgameschedule.gameentry;
    	console.log($scope.games);
     });	
	});
	
	$scope.cuota = "0.0";
	$scope.equipo="Seleccione uno";
	$scope.local=  (Math.random() * (1.7 - 1.1) + 1.1).toFixed(2);
	$scope.guest=  (Math.random() * (1.7 - 1.1) + 1.1).toFixed(2);
	$scope.details=function(team,cuota){
	    $('.table').on('click', 'td', function() {    
	    	$('td').removeClass('actives');
	    	if($(this).hasClass("actives")){
	    	 	   $(this).removeClass('actives');    		
	    	 	} else {
	    	 		$(this).addClass('actives');
	    	 	}
	  });		
		$scope.cuota = cuota;
		$scope.equipo=team;
		$scope.premio = ($scope.apuesta.valor*cuota).toFixed(2);
		$scope.select = !$scope.select;
		}
	
	$scope.contacto=function(){
		$location.url('/contacto');
	}
	$scope.perfil=function(){
		$location.url('/perfil');
	}
	$scope.login=function(){
		document.body.style.backgroundColor = "#2bbbad";
		$cookies.nombreUsuario='';
		$location.url('/');
	}
	$scope.registrarApuesta=function(cuota,opcion)
	{
		$scope.apuesta.cuota = cuota;
		$scope.apuesta.opcion= opcion;
		apuestas.registrarApuesta($scope.apuesta).then(function success(data) {
			alert('Apuesta registrada');
			$location.url('/resultados');
		});
	}

		});

appUser.controller('Perfil', function($cookies,$scope, $location,simulacion, $cookies) {
	
	$scope.nombreUsuario=usuario;
	$scope.editarCorreo=function(){
		$location.url('/editarCorreo');
	}
	$scope.editarContrasena=function(){
		$location.url('/editarContrasena');
	}
	$scope.perfilB=function(){
		$location.url('/resultados');
	}
	$scope.verApuestas=function(){
		$location.url('/listaApuestas');
	}
	$scope.verPeriodos=function(){
		$location.url('/listaPeriodos');
	}
	$scope.login=function(){
		document.body.style.backgroundColor = "#2bbbad";
		$cookies.nombreUsuario='';
		$location.url('/');
	}

	
	$scope.registrarSimulacion=function(){
		 //Obtenemos todos los juegos de esa temporada
	     simulacion.registrarPeriodo($scope.nombreUsuario).then(function success(data) {
	    	  alert("periodo registrado");
	     },
				function failure(data) {
					alert("No fue posible crear otro periodo de simulacion");
	     });	
	}
	
		});

appUser.controller('EditarCorreo', function($cookies,$scope, $location,usuarios, $cookies) {
	

		$scope.password = '';
		$scope.cemail = '';
		$scope.nemail = '';
		$scope.guardar=function(){
			usuarios.editarCorreo($scope.cemail,$scope.password,$scope.nemail).then(
					function success(data){
						alert("Cambios realizados efectivamente ");
						$location.url('/perfil');
			},
			function failure(data) {
				alert("Email o contraseña incorrecta");
			})
			
		}
	$scope.perfilBack=function(){
		$location.url('/perfil');
	}
	$scope.login=function(){
		document.body.style.backgroundColor = "#2bbbad";
		$cookies.nombreUsuario='';
		$location.url('/');
	}

		});


appUser.controller('EditarContrasena', function($cookies,$scope, $location,usuarios, $cookies) {

	$scope.password = '';
	$scope.cemail = '';
	$scope.npassword = '';
	$scope.guardar=function(){
		usuarios.editarContrasena($scope.cemail,$scope.password,$scope.npassword).then(
				function success(data){
					alert("Cambios realizados efectivamente ");
					$location.url('/perfil');
		},
		function failure(data) {
			alert("Email o contraseña incorrecta");
		})
		
	}
$scope.perfilBack=function(){
	$location.url('/perfil');
}
$scope.login=function(){
	document.body.style.backgroundColor = "#2bbbad";
	$cookies.nombreUsuario='';
	$location.url('/');
}

	});

appUser.controller('listaApuestas', function($cookies,$scope, $location,simulacion,apuestas, $cookies) {
		simulacion.consultarPeriodoActivo(usuario).then(
				function success(data){
					apuestas.consultarApuestas(data.data.id).then(
						function success(data){
							console.log(data.data);
							if(data.data.apuesta.length==undefined){
								$scope.apuestas=data.data;
								}else{
									$scope.apuestas=data.data.apuesta;
								}
							},
						function failure(data) {
							console.log("Sin apuestas");
						})
					},
					function failure(data) {
						console.log("Sin periodos activos");
		})
		
		
		$scope.periodoId=function(id){
			
		}
		$scope.login=function(){
			document.body.style.backgroundColor = "#2bbbad";
			$cookies.nombreUsuario='';
			$location.url('/');
		}

	
$scope.perfilBack=function(){
	$location.url('/perfil');
}
	});



appUser.controller('listaPeriodos', function($cookies,$scope, $location,simulacion, $cookies) {
simulacion.consultarPeriodos(usuario).then(
		function success(data){
			if(data.data.simulacion.length==undefined){
				$scope.periodos=data.data;
				}else{
					$scope.periodos=data.data.simulacion;
				}
},
function failure(data) {
	console.log("Sin periodos");
})

$scope.login=function(){
	document.body.style.backgroundColor = "#2bbbad";
	$cookies.nombreUsuario='';
	$location.url('/');
}

$scope.perfilBack=function(){
	$location.url('/perfil');
}
	});

appUser.controller('Detalles', function($scope, $location,apuestas) {
simulacion.consultarPeriodos($scope.nombreUsuario).then(
		function success(data){
			$scope.periodos=data.data.simulacion;
			console.log($scope.periodos)
},
function failure(data) {
	console.log("Sin periodos");
})

$scope.perfilBack=function(){
	$location.url('/perfil');
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
	$routeProvider.when('/detalles', {
		templateUrl : 'Detalles.html',
		controller : 'Detalles'
	});
	$routeProvider.when('/contacto', {
		templateUrl : 'Contacto.html',
		controller : 'Contacto'
	});
	$routeProvider.when('/perfil', {
		templateUrl : 'Perfil.html',
		controller : 'Perfil'
	});
	$routeProvider.when('/editarCorreo', {
		templateUrl : 'EditarCorreo.html',
		controller : 'EditarCorreo'
	});
	$routeProvider.when('/editarContrasena', {
		templateUrl : 'EditarContrasena.html',
		controller : 'EditarContrasena'
	});
	$routeProvider.when('/listaApuestas', {
		templateUrl : 'listaApuestas.html',
		controller : 'listaApuestas'
	});
	$routeProvider.when('/listaPeriodos', {
		templateUrl : 'listaPeriodos.html',
		controller : 'listaPeriodos'
	});
} ]);

appUser.run(function($rootScope, resultados) {
	$rootScope.$on('$routeChangeStart', function() {	
		resultados.validarEstado();
		
	});
})
