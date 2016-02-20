define(function() {
	
	var EntidadesService = function(Restangular) {
		var rest = Restangular.service('metadados/entidades');
		
		return rest;
	};
	
	EntidadesService.$inject = ['Restangular'];
	
	return EntidadesService;
});