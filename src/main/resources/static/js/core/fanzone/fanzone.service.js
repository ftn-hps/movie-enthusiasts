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
		this.deletePropNew = (id) => {
			return $http.delete(`/api/fanzone/propsnew/delete/${id}`);
		};
		
		this.getPropsUsed = () => {
			return $http.get('/api/fanzone/propsused');
		};
		this.getPropsUsedOfApproved = (app) => {
			return $http.get(`/api/fanzone/propsused/approved/${app}`);
		};
		this.getUsersPropsUsed = () => {
			return $http.get('/api/fanzone/propsused/user');
		}
		this.getPropUsed = (id) => {
			return $http.get(`/api/fanzone/propsused/${id}`);
		};
		this.addPropUsed = (data) => {
			return $http.post('/api/fanzone/propsused/add', data);
		};
		this.approvePropUsed = (id, app) => {
			return $http.put(`/api/fanzone/propsused/${id}/approve/${app}`);
		};
		
		this.getBids = (propId)  => {
			return $http.get(`/api/fanzone/propsused/bids/${propId}`);
		};
		this.addBid = (data) => {
			return $http.post('/api/fanzone/propsused/bids/add', data);
		};
		this.acceptBid = (bidId) => {
			return $http.put(`/api/fanzone/propsused/bids/accept/${bidId}`);
		};
});