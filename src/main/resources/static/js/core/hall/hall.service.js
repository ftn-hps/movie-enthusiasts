'use strict';

angular.module('core.hall')
	.service('HallService', function($http) {
		this.getAll = () => {
			return $http.get('/api/halls/');
		};
		this.getOne = (id) => {
			return $http.get(`/api/halls/${id}`);
		};
		this.getHallByPlaceId = (id) =>{
			return $http.get('/api/halls/place/' + id);
		};
		this.add = (data) => {
			return $http.post('/api/halls/', data);
		};
		this.delete = (id) => {
			return $http.delete('/api/halls/'+ id);
		};
		this.edit = (id, data) => {
			return $http.put(`/api/halls/${id}`, data);
		};
	});