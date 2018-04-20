'use strict';

angular.module('placeDetail')
	.component('myPlaceDetail', {
		templateUrl: '/part/place-detail/place-detail.template.html',
		controller: function($stateParams, PlaceService, ProjectionService, ReservationService) {
			this.placeId = $stateParams.id;

			PlaceService.getOne(this.placeId)
				.then((response) => {
					this.place = response.data;
					if(this.place.lat){
						this.mark = [this.place.lat, this.place.lng];
					}
				}, () => {
					this.place = null;
				});

			ProjectionService.getProjectionsByPlaceId(this.placeId)
				.then( (response) => {
					this.projections = response.data;
				}, () => {
					this.projections = null;
				});

			ReservationService.getFast(this.placeId)
				.then( (response) => {
					this.fastReservations = response.data;
				}, () => {
					this.fastReservations = null;
				});

			this.sendFastReservation = (id) => {
				ReservationService.reserveFast(id)
					.then( () => {
						// Ako sam uspeo opet povalcim sada
						ReservationService.getFast(this.placeId)
							.then( (response) => {
								this.fastReservations = response.data;
							}, () => {
								this.fastReservations = null;
							});
					}, () => {
						alert('Fast resevation was not successful');
					});
			};
		}
	});
