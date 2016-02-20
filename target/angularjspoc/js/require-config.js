requirejs.config({
	baseUrl: 'js',
	paths: {
		'angular': 'lib/angular/angular.min',
		'lodash': 'lib/lodash/lodash.min',
		'angular-ui-bootstrap': 'lib/angular-ui/angular-ui-bootstrap-tpls.min',
		'angular-ui-router': 'lib/angular-ui/angular-ui-router.min',
		'restangular': 'lib/restangular/restangular.min'
	},
	shim: { 
		'angular-ui-bootstrap': ['angular'],
		'angular-ui-router': ['angular'],
		'restangular': ['angular', 'lodash']
	},
});

requirejs(['angular', 'angular-ui-bootstrap', 'angular-ui-router', 'restangular', 'app/angularjspoc'], function() {
	angular.bootstrap(document, ['angularjspoc']);
});