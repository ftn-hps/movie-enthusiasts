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
	controller: function(FanZoneService, PlaceService) {
		this.placeType = 'CINEMA';
		this.placeChange = () => {
			PlaceService.getAllOfType(this.placeType).then( (response) => {
				this.places = response.data;
			}, () => {
				this.places = null;
			});
		};
		this.placeChange();
		
		this.send = () => {
			FanZoneService.addPropNew(this.prop).then(
					() => {
						this.status = 'Added succesfully!';
					},
					(response) => {
						this.status = response.status;
					});
		};
	}
});






