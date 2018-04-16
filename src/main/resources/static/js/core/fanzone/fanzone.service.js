'use strict';

angular.module('core.fanzone').service('FanZoneService', function($http) {
		this.getPropsNew = () => {
			return $http.get('/api/fanzone/propsnew');
		};
		this.getPropsNewOfPlace = (place) => {
			return $http.get(`/api/fanzone/propsnew/place/${place}`);
		};
		this.getPropNew = (id) => {
			return $http.get(`/api/fanzone/propsnew/${id}`);
		};
		this.addPropNew = (data) => {
			return $http.post('/api/fanzone/propsnew/add', data);
		};
		this.editPropNew = (id, data) => {
			return $http.put(`/api/fanzone/propsnew/edit/${id}`, data);
		};
		
		this.getPropsUsed = () => {
			return $http.get('/api/fanzone/propsused');
		};
		this.getPropsUsedOfApproved = (app) => {
			return $http.get(`/api/fanzone/propsused/approved/${app}`);
		};
		this.getPropUsed = (id) => {
			return $http.get(`/api/fanzone/propsused/${id}`);
		};
		this.addPropUsed = (data) => {
			return $http.post('/api/fanzone/propsused/add', data);
		};
		this.editPropUsed = (id, data) => {
			return $http.put(`/api/fanzone/propsused/edit/${id}`, data);
		};
});