'use strict';

angular.module('reservation')
	.component('myReservation', {
		templateUrl: '/part/reservation/reservation.template.html',
		controller: function($stateParams, ProjectionService, DateTimeService, ReservationService) {
			this.placeId = $stateParams.placeId;
			this.projectionId = $stateParams.projectionId;

			ProjectionService.getOne(this.projectionId)
				.then( (response) => {
					this.projection = response.data;
				}, () => {
					this.projection = null;
				});

			this.selectedDate = null;
			this.selectedFilteredDate = null;

			DateTimeService.getByProjectionId(this.projectionId)
				.then( (response) => {
					this.dates = response.data;
				});

			this.selectedDateChanged = () => {
				this.filteredDates = [];
				for(let date of this.dates)
				{
					if(this.selectedDate.timeStamp === date.timeStamp)
					{
						this.filteredDates.push(date);
					}
				}
			};

			this.seatsChanged = (output) => {
				this.seats = output;
			};

			this.send = () => {
				let reservation = {
					dateAndTimeId: this.selectedFilteredDate.id,
					seats: this.seats
				};
				ReservationService.add(reservation)
					.then( () => {
						this.status = 'Reservation created successfully';
					}, () => {
						this.status = 'Error';
					});
			};
		}
	});
