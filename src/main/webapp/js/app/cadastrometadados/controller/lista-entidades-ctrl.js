define(function() {
	var ListaEntidadesCtrl = function($scope, $filter, $state, EntidadesService) {
		$scope.ListaEntidades = {
			itens: [],
			filteredItens: [],
			filter: {
				text: ''
			},
			currentPage: 1,
			pageSize: 7,
			loading: false,
			loadError: false
		};
		
		$scope.atualizarListaEntidades = function() {
			$scope.ListaEntidades.loading = true;
			
			EntidadesService.getList().then(function(entidades) {
				$scope.ListaEntidades.itens = entidades;
				$scope.filtrarListaEntidades();
			}, function(error) {
				console.error(error);
				$scope.ListaEntidades.loadError = false;
			}).finally(function() {
				$scope.ListaEntidades.loading = false;
			});
		};
		
		$scope.filtrarListaEntidades = function() {
			$scope.ListaEntidades.filteredItens = $filter('filter')
				($scope.ListaEntidades.itens, $scope.ListaEntidades.filter.text);
		}
		
		$scope.inicializarNovaEntidade = function() {
			$scope.limparMensagemSucesso();
			$state.go('cadastrometadados.entidade');
		};
		
		$scope.selecionarEntidade = function(oidEntidade) {
			$scope.limparMensagemSucesso();
			$state.go('cadastrometadados.entidade', {
				oidEntidade: oidEntidade
			});
		};
		
		$scope.limparMensagemSucesso = function() {
			$scope.mensagemSucesso = null;
		};
		
		$scope.$watch('ListaEntidades.filter.text', $scope.filtrarListaEntidades);
		
		$scope.atualizarListaEntidades();
	};
	
	ListaEntidadesCtrl.$inject = ['$scope', '$filter', '$state', 'EntidadesService'];
	
	return ListaEntidadesCtrl;
});