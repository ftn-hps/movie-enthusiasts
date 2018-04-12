'use strict';

angular.module('placeEdit')
	.component('myPlaceEdit',{
		templateUrl: '/part/place-edit/place-edit.template.html',
		controller: function($stateParams, ProjectionService, PlaceService){
			
			this.placeId = 1;
			
			PlaceService.getOne(this.placeId)
			.then( (response) => {
				this.place = response.data;
			}, () => {
				this.place = null;
			});
	
			
			ProjectionService.getProjectionsByPlaceId(this.place.id)
				.then( (response) => {
					this.projections = response.data;
				}, () =>{
					this.projections = null;
				});
		}
	});