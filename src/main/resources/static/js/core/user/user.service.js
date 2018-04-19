'use strict';

angular.module('core.user')
	.service('UserService', function($http) {
		this.getAll = () => {
			return $http.get('/api/users/');
		};
	});
