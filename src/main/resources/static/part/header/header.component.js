'use strict';

angular.module('header')
	.component('myHeader', {
		templateUrl: '/part/header/header.template.html',
		controller: function(UserAuthService, $rootScope, $state) {
			this.logOut = () => {
				UserAuthService.logOut().then( () => {
					$rootScope.user = null;
					$state.go('home');
				});
			};
		}
	});
