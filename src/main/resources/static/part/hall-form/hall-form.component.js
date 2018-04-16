'use strict';

angular.module('hallForm')
	.component('myHallForm',{
		templateUrl: '/part/hall-form/hall-form.template.html',
		controller: function($stateParams, PlaceService, HallService){
			
			String.prototype.replaceAt=function(index, replacement) {
			    return this.substr(0, index) + replacement+ this.substr(index + replacement.length);
			}
			
			this.hall ={
					"layout": "oooo",
					"rows": 2,
					"columns": 2,
					"place":{}
			};
			this.hall.place.id = $stateParams.placeId;
			this.rowsArrey =[0, 1];
			this.columnsArray = [0, 1];
			this.hall.layout = "oooo"
			this.toggleSeat = (i,j) =>{
				this.current = this.hall.layout[i*this.hall.columns + j];
				if(this.current == 'o')
					this.hall.layout = this.hall.layout.replaceAt(i*this.hall.columns + j,'x');
				else if(this.current == 'x')
					this.hall.layout = this.hall.layout.replaceAt(i*this.hall.columns + j,'v');
				else if(this.current == 'v')
					this.hall.layout = this.hall.layout.replaceAt(i*this.hall.columns + j,'o');
			};
			
			this.changedR_C = () =>{
				this.hall.layout = 'o'.repeat(this.hall.rows * this.hall.columns)
				this.rowsArrey = Array.apply(null, {length: this.hall.rows}).map(Number.call, Number);
				this.columnsArray = Array.apply(null, {length: this.hall.columns}).map(Number.call, Number);
			}
			
			this.send = () => {
				
				HallService.add(this.hall)
				.then(
						(response) =>{
							this.status = response.status;
						},
						() =>{
							this.status = 'Adding failed';
						}
				);
				
			};
		}
	});