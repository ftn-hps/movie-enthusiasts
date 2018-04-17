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
	controller: function(FanZoneService, $stateParams, $rootScope, $state) {
		if($rootScope.user == null)
			$state.go('home');
		this.propId = $stateParams.id;
		FanZoneService.getPropUsed(this.propId).then( (response) => {
			this.prop = response.data;
			var date = new Date();
			var offset = - (date.getTimezoneOffset()/60);
			this.prop.date = new Date(this.prop.date[0], this.prop.date[1] - 1, this.prop.date[2], this.prop.date[3] + offset, this.prop.date[4]);
			if($rootScope.user.id == this.prop.userId) {
				this.canAccept = true;
			}
		}, () => {
			this.prop = null;
		});
		
		FanZoneService.getBids(this.propId).then( (response) => {
			this.bids = response.data;
		}, () => {
			this.bids = null;
		});
		
		this.send = () => {
			var d = new Date();
			if(d.valueOf() < this.prop.date.valueOf()) {
				this.bid.bidderId = $rootScope.user.id;
				this.bid.bidderName = $rootScope.user.name + " " + $rootScope.user.lastName; 
				this.bid.propId = this.prop.id;
				FanZoneService.addBid(this.bid).then(
					() => {
						this.status = 'Added succesfully!';
						FanZoneService.getBids(this.propId).then( (response) => {
							this.bids = response.data;
						}, () => {
							this.bids = null;
						});
					},
					(response) => {
						this.status = response.status;
					});
			} else {
				this.status = "Too late!"
			}
		};
	}
});

angular.module('propsused.display').component('myPropUsedForm', {
	templateUrl: '/part/fanzone/display/display.prop.form.template.html',
	controller: function(FanZoneService, $rootScope, $state) {
		if($rootScope.user == null)
			$state.go('home');
		this.propType = 'used';
		this.date = new Date();
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


