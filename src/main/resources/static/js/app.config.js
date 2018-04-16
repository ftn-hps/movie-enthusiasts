'use strict';

angular.module('movie-enthusiasts')
	.config(function($stateProvider, $urlRouterProvider) {
		$stateProvider
			.state({
				name: 'home',
				url: '/',
				component: 'myHome',
				redirectTo: {
					state: 'home.places',
					params: {placeType: 'theater'}
				}
			})
			.state({
				name: 'home.places',
				url: '^/places/{placeType:theater|cinema}',
				component: 'myPlaceList'
			})
			.state({
				name: 'home.placeDetail',
				url: '^/places/{id:\\d+}',
				template: '<h1>Theater/Cinema Detail</h1>'
			})
			.state({
				name: 'home.placeDetailAdmin',
				url: '^/placesAdmin/{id:\\d+}',
				component: 'myPlaceEdit'
			})
			.state({
				name: 'home.projectionForm',
				url: '^/projectionForm/:idPlace?idProjection',
				component: 'myProjectionForm'
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
				component: 'myProfile'
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
			.when('', '/')
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
