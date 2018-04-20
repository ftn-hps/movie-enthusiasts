'use strict';

angular.module('userAuth.login')
	.component('myLogin', {
		templateUrl: '/part/user-auth/login/login.template.html',
		controller: function(UserAuthService, $rootScope, $state) {
			this.send = () => {
				UserAuthService.logIn(this.user).then(
					(response) => {
						$rootScope.user = response.data;
						$state.go('home');
					},
					() => {
						this.status = 'Wrong email/password.';
					});
			};
		}
	});
