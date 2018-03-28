'use strict';

angular.module('movie-enthusiasts')
	.config(function($stateProvider, $urlRouterProvider) {
		$stateProvider
			.state({
				name: 'home',
				url: '/',
				component: 'myHome'
			})
			.state({
				name: 'home.theaters',
				url: '^/theaters',
				template: '<h1>Theaters</h1>'
			})
			.state({
				name: 'home.cinemas',
				url: '^/cinemas',
				template: '<h1>Cinemas</h1>'
			})
			.state({
				name: 'home.reservations',
				url: '^/reservations',
				template: '<h1>Reservations</h1>'
			})
			.state({
				name: 'home.history',
				url: '^/history',
				template: '<h1>Attendance History</h1>'
			})
			.state({
				name: 'userAuth',
				url: '/user-auth',
				component: 'myUserAuth'
			})
			.state({
				name: 'profile',
				url: '/profile',
				template: '<h1>User Profile</h1>'
			})
			.state({
				name: 'fan-zone',
				url: '/fan-zone',
				template: '<h1>Fan Zone</h1>'
			})
			.state({
				name: 'error',
				url: '/error',
				template: '<h1>Error 404</h1>'
			});

		$urlRouterProvider
			.when('', '/theaters')
			.when('/', '/theaters')
			.otherwise('/error');
	})
	.run(function($rootScope, UserAuthService) {
		UserAuthService.getUser().then(
			(response) => {
				$rootScope.user = response.data;
			},
			() => {
				$rootScope.user = null;
			});
	});
