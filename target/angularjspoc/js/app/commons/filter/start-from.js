define(function() {
	var startFrom = function() {
		return function(input, start) {
			start = +start;
			return input.slice(start);
		}
	};
	
	return startFrom;
});