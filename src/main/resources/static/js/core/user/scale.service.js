'use strict';

angular.module('core.scale').service('ScaleService', function($http) {
	this.getScale = () => {
		return $http.get('/api/scale');
	};
	this.setScale = (data) => {
		return $http.post('/api/scale', data);
	};
});
