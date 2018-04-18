'use strict';

angular.module('attendanceHistory')
	.component('myAttendanceHistory',{
		templateUrl: '/part/attendance-history/attendance-history.template.html',
		controller: function($stateParams, ReservationService){
			
			ReservationService.history()
			.then(
					(response) =>{
						this.reservations = response.data;
					},
					() =>{
						this.reservations = null;
					}
					);
			this.getDate = (timeStamp) =>{
				var date = new Date(timeStamp);
				return date.toLocaleString();
			}
		}
	});