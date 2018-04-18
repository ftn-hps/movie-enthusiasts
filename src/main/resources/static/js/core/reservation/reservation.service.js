'use strict';

angular.module('core.reservation')
	.service('ReservationService', function($http) {

		this.rate = (id,data) => {
			return $http.post('/api/reservations/rate/'+id, data);
		};

		this.history = () => {
			return $http.get('/api/reservations/history');
		};

		this.add = (data) => {
			return $http.post('/api/reservations/', data);
		};
	});
