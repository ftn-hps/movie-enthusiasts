'use strict';

angular.module('core.user')
	.service('UserAuthService', function($http) {
		this.register = (data) => {
			return $http.post('/api/user-auth/', data);
		};
		this.logIn = (data) => {
			return $http.put('/api/user-auth/', data);
		};
		this.logOut = () => {
			return $http.delete('/api/user-auth/');
		};
		this.getUser = () => {
			return $http.get('/api/user-auth/');
		};
		this.editUser = (data) => {
			return $http.put('/api/user-auth/edit', data);
		};
		this.changeRole = (data) => {
			return $http.put('api/user-auth/change-role', data);
		};
	});
