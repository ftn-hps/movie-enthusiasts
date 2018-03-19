'use strict';

angular.module('header')
	.component('myHeader', {
		templateUrl: '/part/header/header.template.html',
		controller: function($rootScope) {
			$rootScope.isLoggedIn = false;
		}
	});
