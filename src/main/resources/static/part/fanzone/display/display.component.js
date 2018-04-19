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
	controller: function(FanZoneService, $stateParams, $state) {
		this.propId = $stateParams.id;
		FanZoneService.getPropNew(this.propId).then( (response) => {
			this.prop = response.data;
		}, () => {
			this.prop = null;
		});
		this.deleteProp = () => {
			FanZoneService.deletePropNew(this.propId).then( () => {
				$state.go('propsnew');
			}, (response) => {
				this.status = response.status;
			});
		};
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

angular.module('propsused.display').component('myUsersPropsUsedDisplay', {
	templateUrl: '/part/fanzone/display/display.myprops.template.html',
	controller: function(FanZoneService) {
		FanZoneService.getUsersPropsUsed().then( (response) => {
			this.props = response.data;
		}, () => {
			this.props = null;
		});
	}
});

angular.module('propsused.display').component('myPropUsedDisplay', {
	templateUrl: '/part/fanzone/display/display.prop.template.html',
	controller: function(FanZoneService, $stateParams, $rootScope, $state) {
		this.propId = $stateParams.id;
		this.myInit = (response) => {
			this.prop = response.data;
			var date = new Date();
			var offset = - (date.getTimezoneOffset()/60);
			this.prop.date = new Date(this.prop.date[0], this.prop.date[1] - 1, this.prop.date[2], this.prop.date[3] + offset, this.prop.date[4]);
			if($rootScope.user.id == this.prop.user.id) {
				this.canAccept = true;
			}
			if(this.prop.date > date && !this.prop.acceptedBid && this.prop.approved) {
				this.enableBidding = true;
			}
		};
		FanZoneService.getPropUsed(this.propId).then( (response) => {
			this.myInit(response);
		}, () => {
			this.prop = null;
		});
		
		this.propertyName = null;
		this.reverse = false;
		this.sortBy = function(propertyName) {
			this.reverse = (this.propertyName === propertyName) ? !this.reverse : false;
			this.propertyName = propertyName;
		};
		
		this.refreshBids = () => {
			this.propertyName = null;
			FanZoneService.getBids(this.propId).then( (response) => {
				this.bids = response.data;
			}, () => {
				this.bids = null;
			});
		};
		this.refreshBids();
		
		this.send = () => {
			var d = new Date();
			if(d.valueOf() < this.prop.date.valueOf()) {
				this.bid.propId = this.prop.id;
				FanZoneService.addBid(this.bid).then( () => {
						this.status = 'Bid succesfull!';
						FanZoneService.getBids(this.propId).then( (response) => {
							this.bids = response.data;
						}, () => {
							this.bids = null;
						});
					}, (response) => {
						this.status = response.status;
					});
			} else {
				this.status = "Too late!"
			}
		};
		
		this.acceptBid = (bidId) => {
			FanZoneService.acceptBid(bidId).then( (response) => {
				$state.reload();
			}, (response) => {
				this.status = response.data;
			});
		};
	}
});

angular.module('propsused.display').component('myPropUsedForm', {
	templateUrl: '/part/fanzone/display/display.prop.form.template.html',
	controller: function(FanZoneService) {
		this.date = new Date();
		this.send = () => {
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
