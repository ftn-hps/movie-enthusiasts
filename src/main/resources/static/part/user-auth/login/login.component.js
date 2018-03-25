'use strict';

angular.module('userAuth.login')
	.component('myLogin', {
		templateUrl: '/part/user-auth/login/login.template.html',
		controller: function() {
			this.send = () => {
				this.user.sent = 'Sent to server';
			};
		}
	});
