'use strict';

angular.module('reservationList')
	.component('myReservationList',{
		templateUrl: '/part/reservation-list/reservation-list.template.html',
		controller: function($stateParams, ReservationService){
			this.type = $stateParams.type;

			ReservationService.getAll(this.type)
				.then( (response) => {
					this.reservations = response.data;
				},
				() => {
					this.reservations = null;
				});

			this.cancel = (reservation) => {
				ReservationService.cancel(reservation.id)
					.then( () => {
						let index = this.reservations.indexOf(reservation);
						this.reservations.splice(index, 1);
					});
			};
		}
	});
