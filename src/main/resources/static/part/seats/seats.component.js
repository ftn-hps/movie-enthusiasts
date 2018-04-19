'use strict';

angular.module('seats')
	.component('mySeats', {
		templateUrl: '/part/seats/seats.template.html',
		bindings: {
			seats: '<',
			columns: '<',
			onChange: '&'
		},
		controller: function() {
			this.change = (index) => {
				switch(this.seats.charAt(index)) {
				case 'o':
					this.seats = this.seats.substr(0, index) + 'a' + this.seats.substr(index + 1);
					break;
				case 'a':
					this.seats = this.seats.substr(0, index) + 'o' + this.seats.substr(index + 1);
					break;
				}
				let output = [];
				let i = 0;
				for(let seat of this.seats.split('')) {
					if(seat === 'a')
						output.push(i);
					i++;
				}
				this.onChange({output: output});
			};
		}
	});
