'use strict';

angular.module('core.reservation')
	.service('ReservationService', function($http) {

		this.rate = (id,data) => {
			return $http.post('/api/reservations/rate/'+id, data);
		};

		this.getAll = (type) => {
			return $http.get(`/api/reservations/${type}`);
		};

		this.getFast = (id) => {
			return $http.get('/api/reservations/fast/'+id);
		};

		this.add = (data) => {
			return $http.post('/api/reservations/', data);
		};

		this.addFast = (data) => {
			return $http.post('/api/reservations/fast/', data);
		};

		this.reserveFast = (id) => {
			return $http.put('/api/reservations/reserveFast/'+id);
		};

		this.cancel = (id) => {
			return $http.delete(`/api/reservations/${id}`);
		};

	});
