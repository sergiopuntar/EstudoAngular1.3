define(function() {
	var ListaEntidadesCtrl = function($scope, $filter, $state, EntidadesService) {
		$scope.ListaEntidades = {
			itens: [],
			filteredItens: [],
			filter: {
				text: ''
			},
			currentPage: 1,
			pageSize: 7
		};
		
		$scope.atualizarListaEntidades = function() {
			EntidadesService.getList().then(function(entidades) {
				$scope.ListaEntidades.itens = entidades;
				$scope.filtrarListaEntidades();
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