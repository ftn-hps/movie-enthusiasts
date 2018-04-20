angular.module('chart')
	.component('myChart',{
		templateUrl: '/part/chart/chart.template.html',
		controller: function($stateParams,PlaceService){
				this.placeId = $stateParams.id; 
				this.monthTime = $stateParams.monthTime;
				if(this.monthTime == 0){ 
					PlaceService.getChartData(this.placeId)
					.then(
							(response) =>{
								this.chartData = response.data;
								
								this.labels = this.chartData.lables;
								this.series = ['Series A', 'Series B'];
								this.data =  this.chartData.data;
							},
							() =>{
								this.labels = ["July", "August","September","October","November","December"];
								this.series = ['Series A', 'Series B'];
								this.data = [
								  [65, 59, 40, 41, 56, 55, 40, 3, 5 ,0 ,1, 11],
								  [28, 48, 40, 19, 46, 27, 50, 3, 1, 8, 0, 5]
								];;
							}
					);
				}
				else 
				{
					PlaceService.getChartDataMonthTime(this.placeId)
					.then(
							(response) =>{
								this.chartData = response.data;
								
								this.labels = this.chartData.lables;
								this.series = ['Series A', 'Series B'];
								this.data =  this.chartData.data;
							},
							() =>{
								this.labels = ["July", "August","September","October","November","December"];
								this.series = ['Series A', 'Series B'];
								this.data = [
								  [65, 59, 40, 41, 56, 55, 40, 3, 5 ,0 ,1, 11],
								  [28, 48, 40, 19, 46, 27, 50, 3, 1, 8, 0, 5]
								];;
							}
					);
				}
				this.labels = ["July", "August","September","October","November","December"];
				this.series = ['Series A', 'Series B'];
				this.data = [
				  [65, 59, 40, 41, 56, 55, 40, 3, 5 ,0 ,1, 11],
				  [28, 48, 40, 19, 46, 27, 50, 3, 1, 8, 0, 5]
				];
				this.onClick = function (points, evt) {
					console.log(points, evt);
				};
				this.datasetOverride = [{ yAxisID: 'y-axis-1' }, { yAxisID: 'y-axis-2' }];
				this.options = {
				scales: {
					yAxes: [
				      {
				        id: 'y-axis-1',
				        type: 'linear',
				        display: true,
				        position: 'left'
				      },
				      {
				    	id: 'y-axis-2',
				        type: 'linear',
				        display: true,
				        position: 'right'
				      }
				    ]
				  }
				};
			
		}
	});
