'use strict';

angular.module('profile.friendAdd')
	.component('myFriendAdd', {
		templateUrl: '/part/profile/friend-add/friend-add.template.html',
		controller: function(FriendshipService) {
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
