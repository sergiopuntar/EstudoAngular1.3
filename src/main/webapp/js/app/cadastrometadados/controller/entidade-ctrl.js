define(function() {
	var EntidadeCtrl = function($scope, $state, $stateParams, EntidadesService) {
		$scope.salvarEntidade = function() {
			$scope.errors = null;
			
			var promise;

			if ($scope.entidade.$save) {
				promise = $scope.entidade.save();
			} else {
				promise = EntidadesService.post($scope.entidade);
				$scope.entidade = promise.$object;
			}

			promise.then(function() {
				//$scope.$parent.mensagemSucesso = 'Entidade salva com sucesso';
				$scope.$parent.atualizarListaEntidades();
				//$state.go('cadastrometadados.entidades');
			});

			promise['catch'](function(response) {
				if (response.status === 400) {
					$scope.errors = response.data;
				} else {
					$scope.errors = {
						general: ['Não foi possível salvar a entidade.']
					};
				}
			});
		};

		$scope.excluirEntidade = function() {
			$scope.errors = null;

			var promise = $scope.entidade.remove();
			
			promise.then(function() {
				$scope.$parent.mensagemSucesso = 'Entidade excluída com sucesso';
				$scope.$parent.atualizarListaEntidades();
				$state.go('cadastrometadados.entidades');
			});
			
			promise['catch'](function() {
				$scope.errors = {
					general: ['Não foi possível excluir a entidade.']
				};
			});
		};

		if ($stateParams.oidEntidade) {
			EntidadesService.one($stateParams.oidEntidade).get().then(function(entidade) {
				$scope.entidade = entidade;
			});
		} else {
			$scope.entidade = {};
		}
	};
	
	EntidadeCtrl.$inject = ['$scope', '$state', '$stateParams', 'EntidadesService'];
	
	return EntidadeCtrl;
});