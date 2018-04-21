'use strict';

angular.module('placeEdit')
	.component('myPlaceEdit',{
		templateUrl: '/part/place-edit/place-edit.template.html',
		controller: function($stateParams, PlaceService){
			
			this.placeId = $stateParams.id;
			
			PlaceService.getOne(this.placeId)
			.then( (response) => {
				this.place = response.data;
				if(this.place.lat) {
					this.marker = [this.place.lat, this.place.lng];
				}
			}, () => {
				this.place = null;
			});
			 
			this.send = () => {
				if(this.marker) {
					this.place.lat = this.marker[0];
					this.place.lng = this.marker[1];
				} else {
					this.place.lat = undefined;
					this.place.lng = undefined;
				}
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
			
			
			this.removeMarker = () => {
				this.marker = null;
			};
			this.getpos = (event) => {
				this.marker = [event.latLng.lat(), event.latLng.lng()];
			};
		}
	});