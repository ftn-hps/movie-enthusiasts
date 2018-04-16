'use strict';

angular.module('propsnew').component('myPropsNew', {
	templateUrl: '/part/fanzone/propsnew/propsnew.template.html',
	controller: function(FanZoneService) {
		FanZoneService.getPropsNew().then( (response) => {
			this.props = response.data;
		}, () => {
			this.props = null;
		});
	}
});

