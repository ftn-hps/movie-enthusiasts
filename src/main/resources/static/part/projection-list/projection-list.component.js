'use strict';

angular.module('projectionList')
	.component('myProjectionList',{
		templateUrl: '/part/projection-list/projection-list.template.html',
		controller: function ($stateParams, PlaceService,ProjectionService){
			this.placeId = $stateParams.id;
			
			ProjectionService.getProjectionsByPlaceId(this.placeId)
				.then( (response) => {
					this.projections = response.data;
				}, () =>{
					this.projections = null;
				});
			
	
			
		}
	});