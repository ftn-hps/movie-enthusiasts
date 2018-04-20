'use strict';

angular.module('core.place')
	.service('PlaceService', function($http) {
		this.getAll = () => {
			return $http.get('/api/places/');
		};
		this.getAllOfType = (type) => {
			return $http.get(`/api/places/${type}`);
		};
		this.getOne = (id) => {
			return $http.get(`/api/places/${id}`);
		};
		this.getChartData = (id) => {
			return $http.get('/api/places/chart/'+id);
		};
		this.getChartDataMonthTime = (id) => {
			return $http.get('/api/places/chart/month/'+id);
		};
		this.add = (data) => {
			return $http.post('/api/places/', data);
		};
		this.edit = (id, data) => {
			return $http.put(`/api/places/${id}`, data);
		};
	});
