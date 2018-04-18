'use strict';

angular.module('placeDetail')
	.component('myPlaceDetail', {
		templateUrl: '/part/place-detail/place-detail.template.html',
		controller: function($stateParams, PlaceService, ProjectionService) {
			this.placeId = $stateParams.id;

			PlaceService.getOne(this.placeId)
				.then((response) => {
					this.place = response.data;
				}, () => {
					this.place = null;
				});

			ProjectionService.getProjectionsByPlaceId(this.placeId)
				.then( (response) => {
					this.projections = response.data;
				}, () => {
					this.projections = null;
				});
		}
	});
