'use strict';

angular.module('changeRole').component('myChangeRole',{
		templateUrl: '/part/change-role/change-role.template.html',
		controller: function(UserService, UserAuthService, $rootScope, $state){
			if($rootScope.user == null || $rootScope.user.userType != 'SYSADMIN')
				$state.go('home');
			
			UserService.getAll().then( (response) => {
				this.users = response.data;
			}, () => {
				this.users = null;
			});

			this.send = () => {
				UserAuthService.changeRole({'id': this.selectedUser.description.id, 'userType': this.selectedUser.description.userType}).then( () => {
					this.status = 'Changed succesfully!';
				}, (response) => {
					this.status = response.status;
				})
			};
		}
	});