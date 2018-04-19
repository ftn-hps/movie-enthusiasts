'use strict';

angular.module('projectionForm')
	.component('myProjectionForm',{
		templateUrl: '/part/projection-form/projection-form.template.html',
		controller: function($stateParams, ProjectionService, PlaceService, $window, HallService,DateTimeService,Upload){
			
			this.projectionId = $stateParams.idProjection;
			this.placeId = $stateParams.idPlace;
			
			ProjectionService.getOne(this.projectionId)
			.then( (response) => {
				this.projection = response.data;
			}, () => {
				this.projection = null;
			});
			
			if( this.placeId > 0)
			{
				HallService.getHallByPlaceId(this.placeId)
				.then(
				(response) =>{
					this.halls = response.data;
				},
				() =>{
					this.halls = null;
				}
				);
					
				
			}
			this.sendDateTime = () => {
				if(dateTimeForm.$invalid)
					return ;
				this.newDateTime.projection = this.projection;
				
				this.date.setHours(this.time.getHours());
				this.date.setMinutes(this.time.getMinutes());
				
				this.newDateTime.timeStamp = this.date.getTime()/1000;
				
				
				this.newDateTime.reservationLayout = 'o'.repeat(this.newDateTime.hall.rows * this.newDateTime.hall.columns);
				DateTimeService.add(this.newDateTime)
				.then(
						(response) =>{
							this.status1 = response.status;
						},
						() =>{
							this.status1 = 'Adding failed';
						}
				);
			};
			
			this.send = () => {
				if(this.projectionId = this.projectionId > 0)
				{
					ProjectionService.edit(this.projection.id,this.projection)
					.then(
							(response) =>{
								this.status = response.status;
							},
							() =>{
								this.status = 'Editing failed';
							}
					);
				}
				else 
				{
					Upload.upload({
			            url: '/api/upload',
			            data: {file: this.file, 'username': 'mama'}
			        }).then(function (resp) {
			            console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
			        }, function (resp) {
			            console.log('Error status: ' + resp.status);
			        }, function (evt) {
			            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
			            console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
			        });
					
					PlaceService.getOne(this.placeId)
					.then( (response) => {
						this.projection.place = response.data;
						if(this.file == undefined)
							this.projection.imagePath = "/img/placeholder.png";
						else 
							this.projection.imagePath = "/img/"+this.file.$ngfName;
						
						ProjectionService.add(this.projection)
						.then(
							(response) =>
							{
								this.status = response.status;
								$window.location.href = '#!/projectionForm/'+response.data.place.id+'?idProjection='+response.data.id;
							},
							() =>
							{
								this.status = 'Creadting failed(add Method)';
							}
						);
						
					}, 
					() => {
							this.projection.place = null;
							this.status = 'Creadting failed(place not found)';
						}
					);
				}
			};
		}
	});