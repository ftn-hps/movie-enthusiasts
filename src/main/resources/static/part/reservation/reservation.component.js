'use strict';

angular.module('reservation')
	.component('myReservation', {
		templateUrl: '/part/reservation/reservation.template.html',
		controller: function($stateParams, ProjectionService, DateTimeService, FriendshipService, ReservationService) {
			this.placeId = $stateParams.placeId;
			this.projectionId = $stateParams.projectionId;

			ProjectionService.getOne(this.projectionId)
				.then( (response) => {
					this.projection = response.data;
				}, () => {
					this.projection = null;
				});

			FriendshipService.getAll()
				.then( (response) => {
					this.friendships = response.data;
				}, () => {
					this.friendships = null;
				});

			this.selectedDate = null;
			this.selectedFilteredDate = null;
			this.selectedFriendships = [];
			this.discount = 0;

			DateTimeService.getFutureByProjectionId(this.projectionId)
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
				if(this.seats.length < 1)
				{
					this.status = 'Select a seat you want to reserve';
					return;
				}
				if(this.seats.length != this.selectedFriendships.length + 1)
				{
					this.status = `You have to invite
						${this.selectedFriendships.length + 1} friends
						because you reserved ${this.seats.length} seats`;
					return;
				}

				let friendIds = [];
				for(let friendship of this.selectedFriendships)
					friendIds.push(friendship.friend.id);
				let reservation = {
					dateAndTimeId: this.selectedFilteredDate.id,
					seats: this.seats,
					friendIds: friendIds
				};
				ReservationService.add(reservation)
					.then( () => {
						this.status = 'Reservation created successfully';
					}, () => {
						this.status = 'Error';
					});
			};

			this.sendFast = () => {
				let reservation = {
					dateAndTimeId: this.selectedFilteredDate.id,
					seats: this.seats,
					discount: this.discount
				};
				ReservationService.addFast(reservation)
					.then( () => {
						this.status = 'Fast reservation created successfully';
					}, () => {
						this.status = 'Error';
					});
			};
		}
	});
