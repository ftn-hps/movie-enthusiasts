'use strict';

angular.module('projectionForm')
	.component('myProjectionForm',{
		templateUrl: '/part/projection-form/projection-form.template.html',
		controller: function($stateParams, ProjectionService, PlaceService, $window, HallService,DateTimeService){
			
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
				
//				this.newDateTime.date = this.date.getFullYear();
//				if(this.date.getMonth()+1 < 10)
//					this.newDateTime.date += '-0'+(this.date.getMonth()+1)
//				else 
//					this.newDateTime.date += '-'+(this.date.getMonth()+1)
//				if(this.date.getDate() < 10)
//					this.newDateTime.date += '-0'+(this.date.getDate())
//				else 
//					this.newDateTime.date += '-'+(this.date.getDate())
//					
//				this.newDateTime.time = "";
//				if(this.time.getHours() < 10)
//					this.newDateTime.time = '0'+(this.time.getHours())
//				else 
//					this.newDateTime.time = (this.time.getHours())
//					
//				if(this.time.getMinutes() < 10)
//					this.newDateTime.time += ':0'+(this.time.getMinutes())
//				else 
//					this.newDateTime.time += ':'+(this.time.getMinutes())
				
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
				//alert("sent");
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
					PlaceService.getOne(this.placeId)
					.then( (response) => {
						this.projection.place = response.data;
						
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