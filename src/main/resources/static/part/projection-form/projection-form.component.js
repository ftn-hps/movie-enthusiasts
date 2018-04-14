'use strict';

angular.module('projectionForm')
	.component('myProjectionForm',{
		templateUrl: '/part/projection-form/projection-form.template.html',
		controller: function($stateParams, ProjectionService){
			
			this.projectionId = $stateParams.id;
			
			ProjectionService.getOne(this.projectionId)
			.then( (response) => {
				this.projection = response.data;
			}, () => {
				this.projection = null;
			});
			
			this.send = () => {
				ProjectionService.edit(this.placeId,this.place)
				.then(
						(response) =>{
							this.status = response.status;
						},
						() =>{
							this.status = 'Editing failed';
						}
				);
			};
		}
	});