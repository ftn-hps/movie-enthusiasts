'use strict';

angular.module('fanzone.admin').component('myFanzoneAdmin', {
	templateUrl: '/part/fanzone/admin/fanzone.admin.template.html',
	controller: function($rootScope, $state) {
		if($rootScope.user == null || $rootScope.user.userType != 'FANZONEADMIN')
			$state.go('home');
	}
});

angular.module('fanzone.admin').component('myPropsUsedAdminDisplay', {
	templateUrl: '/part/fanzone/admin/fanzone.admin.props.template.html',
	controller: function(FanZoneService, $rootScope, $state) {
		FanZoneService.getPropsUsedOfApproved('FALSE').then( (response) => {
			this.props = response.data;
		}, () => {
			this.props = null;
		});
	}
});

angular.module('fanzone.admin').component('myPropUsedAdminDisplay', {
	templateUrl: '/part/fanzone/admin/fanzone.admin.approve.template.html',
	controller: function(FanZoneService, $stateParams, $rootScope, $state) {
		this.propId = $stateParams.id;
		FanZoneService.getPropUsed(this.propId).then( (response) => {
			this.prop = response.data;
			var date = new Date();
			var offset = - (date.getTimezoneOffset()/60);
			this.prop.date = new Date(this.prop.date[0], this.prop.date[1] - 1, this.prop.date[2], this.prop.date[3] + offset, this.prop.date[4]);
		}, () => {
			this.prop = null;
		});
		
		this.approve = (app) => {
			FanZoneService.approvePropUsed(this.propId, app).then( (response) => {
				$state.go('fanzoneAdmin.approveUsedProps');
			}, (response) => {
				this.status = response.status;
			});
		};
	}
});

angular.module('fanzone.admin').component('myPropNewForm', {
	templateUrl: '/part/fanzone/admin/fanzone.admin.prop.form.template.html',
	controller: function(FanZoneService, PlaceService, Upload) {
		this.placeType = 'CINEMA';
		this.edit = false;
		
		this.setPlaceId = (placeId) => {
			if(!this.prop)
				this.prop = new Object();
			this.prop.placeId = placeId;
		};
		
		this.placeChange = () => {
			PlaceService.getAllOfType(this.placeType).then( (response) => {
				this.places = response.data;
			}, () => {
				this.places = null;
			});
		};
		
		this.placeChange();
		
		this.send = () => {
			this.prop.placeId = this.selectedPlace.description.id;
			FanZoneService.addPropNew(this.prop).then( (response) => {
						this.status = 'Added succesfully!';
						if(this.file != null) {
							Upload.upload({
						        url: '/api/fanzone/upload',
						        fields: {'propId': response.data.id, 'propType': 'NEW'}, // additional data to send
						        file: this.file
						    });
						}
					},
					(response) => {
						this.status = response.status;
					});
		};
	}
});

angular.module('fanzone.admin').component('myPropNewEdit', {
	templateUrl: '/part/fanzone/admin/fanzone.admin.prop.form.template.html',
	controller: function(FanZoneService, PlaceService, $stateParams, $state, Upload) {
		this.edit = true;
		
		this.placeChange = () => {
			PlaceService.getAllOfType(this.placeType).then( (response) => {
				this.places = response.data;
			}, () => {
				this.places = null;
			});
		};
		
		FanZoneService.getPropNew($stateParams.id).then( (response) => {
			this.prop = new Object();
			this.prop.name = response.data.name;
			this.prop.description = response.data.description;
			this.setAsSelected = response.data.place;
			this.placeType = response.data.place.type;
			this.placeChange();
		}, () => {
			$state.go('home');
		});
		
		this.send = () => {
			if(this.selectedPlace.originalObject)
				this.prop.placeId = this.selectedPlace.originalObject.id;
			else
				this.prop.placeId = this.selectedPlace.description.id;
			FanZoneService.editPropNew($stateParams.id, this.prop).then( (response) => {
						this.status = 'Added succesfully!';
						if(this.file != null) {
							Upload.upload({
						        url: '/api/fanzone/upload',
						        fields: {'propId': response.data.id, 'propType': 'NEW'}, // additional data to send
						        file: this.file
						    });
						}
					},
					(response) => {
						this.status = response.status;
					});
		};
	}
});






