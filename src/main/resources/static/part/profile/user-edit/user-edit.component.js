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
				if(this.oldPassword) {
					if(this.oldPassword == this.user.password) {
						this.status = 'Choose different password!';
						return;
					}
					if(this.user.password != this.user.passwordAgain) {
						this.status = 'Passwords don\'t match';
						return;
					}
				}
				UserAuthService.editUser({'user': this.user, 'oldPassword': this.oldPassword})
					.then( (response) => {
						this.user = response.data;
						this.status = 'User updated successfully';
					}, () => {
						this.status = 'Error';
					});
			};
		}
	});
