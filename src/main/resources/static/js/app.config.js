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
				component: 'myPlaceDetail'
			})
			.state({
				name: 'home.reserve',
				url: '^/places/{placeId:\\d+}/reserve/{projectionId:\\d+}',
				component: 'myReservation'
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
				name: 'home.hallForm',
				url: '^/hallForm/{placeId:\\d+}',
				component: 'myHallForm'
			})
			.state({
				name: 'home.reservations',
				url: '^/reservations',
				template: '<h1>Reservations</h1>'
			})
			.state({
				name: 'home.history',
				url: '^/history',
				component: 'myAttendanceHistory'
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
				name: 'propsnew',
				url: '/fanzone/propsnew',
				component: 'myPropsNew'
			})
			.state({
				name: 'propsnew.placeList',
				url: '/{placeType:theater|cinema}',
				component: 'myPlacesDisplay'
			})
			.state({
				name: 'propsnew.place',
				url: '/{placeType:theater|cinema}/{placeId:\\d+}',
				component: 'myPropsNewDisplay'
			})
			.state({
				name: 'propsnew.prop',
				url: '/{id:\\d+}',
				component: 'myPropNewDisplay'
			})
			.state({
				name: 'propsused',
				url: '/fanzone/propsused',
				component: 'myPropsUsed'
			})
			.state({
				name: 'propsused.prop',
				url: '/{id:\\d+}',
				component: 'myPropUsedDisplay'
			})
			.state({
				name: 'propsused.add',
				url: '/add',
				component: 'myPropUsedForm'
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
