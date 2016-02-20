define(function() {
	var config = function ($urlRouterProvider, $stateProvider, RestangularProvider) {
		$urlRouterProvider.otherwise('');
		
		$stateProvider
			.state('home', {
	            url: ''
	        });
		
		RestangularProvider.setBaseUrl('rest/');
		
		RestangularProvider.setRestangularFields({
			id: 'oid',
		});
	};
	
	config.$inject = ['$urlRouterProvider', '$stateProvider', 'RestangularProvider'];
	
	return config;
});