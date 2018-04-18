'use strict';

angular.module('core.projection')
	.service('ProjectionService',function ($http){
		this.getAll = () => {
			return $http.get('/api/projections');
		};
		this.getOne = (id) => {
			return $http.get('/api/projections/'+id);
		};
		this.getProjectionsByPlaceId = (id) =>{
			return $http.get('/api/projections/place/'+id);
		};
		this.add = (data) => {
			return $http.post('/api/projections/', data);
		};
		this.edit = (id, data) => {
			return $http.post('/api/projections/edit/', data);
		};
		this.deleteProjection = (id) =>{
			return $http.delete('/api/projections/'+ id);
		};
	});
