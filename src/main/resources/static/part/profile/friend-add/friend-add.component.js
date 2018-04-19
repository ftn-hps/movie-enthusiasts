'use strict';

angular.module('profile.friendAdd')
	.component('myFriendAdd', {
		templateUrl: '/part/profile/friend-add/friend-add.template.html',
		controller: function(FriendshipService, UserService) {
			UserService.getAll()
				.then( (response) => {
					this.users = response.data;
				});

			this.send = () => {
				FriendshipService.add(this.search)
					.then( () => {
						this.status = 'Friend request sent';
					}, () => {
						this.status = 'Error';
					});
			};
		}
	});
