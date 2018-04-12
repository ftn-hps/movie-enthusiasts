'use strict';

angular.module('placeEdit')
	.component('myPlaceEdit',{
		templateUrl: '/part/place-edit/place-edit.template.html',
		controller: function($stateParams, PlaceService){
			
			this.placeId = $stateParams.id;
			
			PlaceService.getOne(this.placeId)
			.then( (response) => {
				this.place = response.data;
			}, () => {
				this.place = null;
			});
			
			this.send = () => {
				PlaceService.edit(this.placeId,this.place)
				.then(
						(response) =>{
							this.status = response.status;
						},
						() =>{
							this.status = 'Editing failed';
						}
				);
			};
		}
	});