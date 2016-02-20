define(['./config',
        './commons/commons',
        './cadastrometadados/cadastro-metadados'],
function(config) {
	var angularjspoc = angular.module('angularjspoc', ['ui.bootstrap', 'ui.router', 'restangular', 'anbCommons', 'anbCadastroMetadados']);
	angularjspoc.config(config);
	
	angularjspoc.controller('ShowcaseCtrl', ['$rootScope', '$scope', '$state', function($rootScope, $scope, $state) {
		
		$rootScope.ehEstadoAtual = function(stateOrName) {
			return $state.includes(stateOrName);
		};
		
		$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
		    //console.log(toState);
		});
	}]);
	
	return angularjspoc;
});