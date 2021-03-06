'use strict';

angular.module('changeRole').component('myChangeRole',{
		templateUrl: '/part/change-role/change-role.template.html',
		controller: function(UserService, ScaleService, UserAuthService, $rootScope, $state){
			if($rootScope.user == null || $rootScope.user.userType != 'SYSADMIN')
				$state.go('home');
			
			UserService.getAll().then( (response) => {
				this.users = response.data;
			}, () => {
				this.users = null;
			});
			
			ScaleService.getScale().then( (response) => {
				this.scale = response.data;
			}, () => {
				this.scale = null;
			});

			this.send = () => {
				UserAuthService.changeRole({'id': this.selectedUser.description.id, 'userType': this.selectedUser.description.userType}).then( () => {
					this.status = 'Changed succesfully!';
				}, (response) => {
					this.status = response.status;
				})
			};
			
			this.sendScale = () => {
				if(this.scale.gold <= this.scale.silver) {
					this.scaleStatus = 'Gold medal must be greater than silver!'
					return;
				}
				ScaleService.setScale(this.scale).then( () => {
					this.scaleStatus = "Scale set succesfully";
				}, (response) => {
					this.scaleStatus = response.status;
				});
			};
		}
	});