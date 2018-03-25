'use strict';

angular.module('userAuth.registration')
	.component('myRegistration', {
		templateUrl: '/part/user-auth/registration/registration.template.html',
		controller: function() {
			this.send = () => {
				this.user.sent = 'Sent to server';
			};
		}
	});
