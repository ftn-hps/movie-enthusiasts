'use strict';

angular.module('profile.userEdit')
	.component('myUserEdit', {
		templateUrl: '/part/profile/user-edit/user-edit.template.html',
		controller: function(UserAuthService) {
			UserAuthService.getUser()
				.then( (response) => {
					this.user = response.data;
				}, () => {
					this.user = null;
				});

			this.send = () => {
				UserAuthService.editUser(this.user)
					.then( (response) => {
						this.user = response.data;
						this.status = 'User updated successfully';
					}, () => {
						this.status = 'Error';
					});
			};
		}
	});
