'use strict';

angular.module('header')
	.component('myHeader', {
		templateUrl: '/part/header/header.template.html',
		controller: function(UserAuthService, $rootScope) {
			this.logOut = () => {
				UserAuthService.logOut().then( () => {
					$rootScope.user = null;
				});
			};
		}
	});
