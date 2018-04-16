'use strict';

angular.module('propsnew.display').component('myPlacesDisplay', {
	templateUrl: '/part/fanzone/display/display.places.template.html',
	controller: function(PlaceService, $stateParams) {
		this.placeType = $stateParams.placeType;
		PlaceService.getAllOfType(this.placeType.toUpperCase()).then( (response) => {
			this.places = response.data;
		}, () => {
			this.places = null;
		});
	}
});

angular.module('propsnew.display').component('myPropsNewDisplay', {
	templateUrl: '/part/fanzone/display/display.props.template.html',
	controller: function(FanZoneService, $stateParams) {
		this.placeId = $stateParams.placeId;
		if(this.placeId) {
			FanZoneService.getPropsNewOfPlace(this.placeId).then( (response) => {
				this.props = response.data;
			}, () => {
				this.props = null;
			});
		} else {
			FanZoneService.getPropsNew().then( (response) => {
				this.props = response.data;
			}, () => {
				this.props = null;
			});
		}
	}
});

angular.module('propsnew.display').component('myPropNewDisplay', {
	templateUrl: '/part/fanzone/display/display.prop.template.html',
	controller: function(FanZoneService, $stateParams) {
		this.propId = $stateParams.id;
		FanZoneService.getPropNew(this.propId).then( (response) => {
			this.prop = response.data;
		}, () => {
			this.prop = null;
		});
	}
});

angular.module('propsused.display').component('myPropsUsedDisplay', {
	templateUrl: '/part/fanzone/display/display.props.template.html',
	controller: function(FanZoneService) {
		FanZoneService.getPropsUsed().then( (response) => {
			this.props = response.data;
		}, () => {
			this.props = null;
		});
	}
});

angular.module('propsused.display').component('myPropUsedDisplay', {
	templateUrl: '/part/fanzone/display/display.prop.template.html',
	controller: function(FanZoneService, $stateParams) {
		this.propId = $stateParams.id;
		FanZoneService.getPropUsed(this.propId).then( (response) => {
			this.prop = response.data;
		}, () => {
			this.prop = null;
		});
	}
});

angular.module('propsused.display').component('myPropUsedForm', {
	templateUrl: '/part/fanzone/display/display.prop.form.template.html',
	controller: function(FanZoneService, $rootScope, $state) {
		if($rootScope.user == null)
			$state.go('home');
		this.propType = 'used';
		this.send = () => {
			this.prop.userId = $rootScope.user.id;
			this.prop.approved = false;
			FanZoneService.addPropUsed(this.prop).then(
				() => {
					this.status = 'Added succesfully!';
				},
				(response) => {
					this.status = response.status;
				});
		};
	}
});


