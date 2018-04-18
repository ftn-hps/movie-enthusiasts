'use strict';

angular.module('rateReservation')
	.component('myRateReservation',{
		templateUrl: '/part/rate-reservation/rate-reservation.template.html',
		controller: function($stateParams, ReservationService,  $window){
			
			this.reservationId = $stateParams.idReservation;
			
			this.send = () => {
				ReservationService.rate(this.reservationId,this.rateDTO)
				.then( (response) => {
					this.status = response.status;
					$window.location.href = '#!/history'
				}, () => {
					this.status = "Rating failed"
				});
			};
		}
	});