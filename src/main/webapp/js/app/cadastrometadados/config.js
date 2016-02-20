define(function() {
	var config = function ($stateProvider, $urlRouterProvider) {
		
		$stateProvider
			.state({
				name: 'cadastrometadados',
				abstract: true,
				url: '/cadastrometadados',
				templateUrl: 'templates/cadastrometadados/home.html',
				controller: 'ListaEntidadesCtrl'
			})
			.state({
				name: 'cadastrometadados.entidades',
				parent: 'cadastrometadados',
				url: '',
				templateUrl: 'templates/cadastrometadados/lista-entidades.html'
			})
			.state({
				name: 'cadastrometadados.entidade',
				parent: 'cadastrometadados',
				url: '/entidade',
				templateUrl: 'templates/cadastrometadados/entidade.html',
				controller: 'EntidadeCtrl',
				params: {
					oidEntidade: null
				}
			});
	};
	
	config.$inject = ['$stateProvider', '$urlRouterProvider'];
	
	return config;
});