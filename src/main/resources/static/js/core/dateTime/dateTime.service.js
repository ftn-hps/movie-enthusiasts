'use strict';

angular.module('core.dateTime')
	.service('DateTimeService', function($http) {
		this.getAll = () => {
			return $http.get('/api/dateAndTimeOfProjections/');
		};
		this.getOne = (id) => {
			return $http.get('/api/dateAndTimeOfProjections/'+id);
		};
		this.getByProjectionId = (id) =>{
			return $http.get('/api/dateAndTimeOfProjections/projection/' + id);
		};
		this.getFutureByProjectionId = (id) =>{
			return $http.get(`/api/dateAndTimeOfProjections/projection/${id}/future`);
		};
		this.add = (data) => {
			return $http.post('/api/dateAndTimeOfProjections/', data);
		};
		this.edit = (id, data) => {
			return $http.put('/api/dateAndTimeOfProjections/'+id, data);
		};
	});
