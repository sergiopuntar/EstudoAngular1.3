define(['./config',
        './service/entidades-service',
        './controller/lista-entidades-ctrl',
        './controller/entidade-ctrl'],
function(config, EntidadesService, EntidadesCtrl, EntidadeCtrl) {
	var anbCadastroMetadados = angular.module('anbCadastroMetadados', []);	
	anbCadastroMetadados.config(config);
	anbCadastroMetadados.factory('EntidadesService', EntidadesService);
	anbCadastroMetadados.controller('ListaEntidadesCtrl', EntidadesCtrl);
	anbCadastroMetadados.controller('EntidadeCtrl', EntidadeCtrl);
	
	return anbCadastroMetadados;
});