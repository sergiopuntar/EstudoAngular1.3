define(['./filter/start-from'],
function(StartFromFilter) {
	var anbCommons = angular.module('anbCommons', []);
	anbCommons.filter('startFrom', StartFromFilter);
	
	return anbCommons;
});