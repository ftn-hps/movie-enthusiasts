'use strict';

angular.module('placeList')
	.component('myPlaceList', {
		templateUrl: '/part/place-list/place-list.template.html',
		controller: function($stateParams, PlaceService) {
			this.placeType = $stateParams.placeType.toUpperCase();

			PlaceService.getAllOfType(this.placeType)
				.then( (response) => {
					this.places = response.data;
				}, () => {
					this.places = null;
				});

			this.order = null;
			this.isReverse = true;
			this.orderBy = (order) => {
				this.isReverse = (this.order === order) ? !this.isReverse : false;
				this.order = order;
			};
		}
	});
