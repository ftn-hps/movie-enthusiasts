'use strict';

angular.module('projectionList')
	.component('myProjectionList',{
		templateUrl: '/part/projection-list/projection-list.template.html',
		controller: function ($stateParams, PlaceService,ProjectionService,$window){
			this.placeId = $stateParams.id;
			
			ProjectionService.getProjectionsByPlaceId(this.placeId)
				.then( (response) => {
					this.projections = response.data;
				}, () =>{
					this.projections = null;
				});
			
			this.deleteProjection = ( id )=>{
				
				ProjectionService.deleteProjection(id)
				.then( (response) => {
					ProjectionService.getProjectionsByPlaceId(this.placeId)
					.then( (response) => {
						this.projections = response.data;
					}, () =>{
						this.projections = null;
					});
				}, () =>{
					alert("Projection can't be deleted");
				});
			};
		}
	});