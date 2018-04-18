'use strict';

angular.module('propsused').component('myPropsUsed', {
	templateUrl: '/part/fanzone/propsused/propsused.template.html',
	controller: function( $rootScope, $state) {
		if($rootScope.user == null) 
			$state.go('home');
	}
});