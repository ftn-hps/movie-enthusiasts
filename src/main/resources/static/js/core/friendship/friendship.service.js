'use strict';

angular.module('core.friendship')
	.service('FriendshipService', function($http) {
		this.getAll = () => {
			return $http.get('/api/friendships/');
		};
		this.add = (receiverId) => {
			return $http.post(`/api/friendships/${receiverId}`);
		};
		this.accept = (id) => {
			return $http.put(`/api/friendships/${id}`);
		};
		this.remove = (id) => {
			return $http.delete(`/api/friendships/${id}`);
		};
	});
