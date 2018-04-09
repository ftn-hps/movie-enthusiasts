'use strict';

angular.module('placeList')
	.component('myPlaceList', {
		templateUrl: '/part/place-list/place-list.template.html',
		controller: function($stateParams) {
			this.placeType = $stateParams.placeType.toUpperCase();
			//TODO fetch real data
			this.places = [
				{id:1, name:'Test 1', address:'address1', description:'description1', rating:4},
				{id:2, name:'Test 2', address:'address2', description:'description2', rating:3}
			];
		}
	});
