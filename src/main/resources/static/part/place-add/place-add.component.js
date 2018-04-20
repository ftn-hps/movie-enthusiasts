'use strict';

angular.module('addPlace').component('myAddPlace',{
		templateUrl: '/part/place-add/place-add.template.html',
		controller: function($stateParams, PlaceService, $rootScope, $state){
			if($rootScope.user == null || $rootScope.user.userType != 'SYSADMIN')
				$state.go('home');
			if(this.place == null) {
				this.place = new Object();
				this.place.type = 'CINEMA';
			}
			this.send = () => {
				if(this.marker) {
					this.place.lat = this.marker[0];
					this.place.lng = this.marker[1];
				}
				PlaceService.add(this.place).then( (response) => {
					this.status = response.status;
				}, (response) => {
					this.status = response.status;
				});
			};
			
			this.marker = null;
			this.removeMarker = () => {
				this.marker = null;
			};
			this.getpos = (event) => {
				this.marker = [event.latLng.lat(), event.latLng.lng()];
			};
		}
	});