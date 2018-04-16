'use strict';

angular.module('profile.friendList')
	.component('myFriendList', {
		templateUrl: '/part/profile/friend-list/friend-list.template.html',
		controller: function(FriendshipService) {
			FriendshipService.getAll()
				.then( (response) => {
					this.friendships = response.data;
				}, () => {
					this.friendships = null;
				});

			this.accept = (friendship) => {
				FriendshipService.accept(friendship.id)
					.then( () => {
						let index = this.friendships.indexOf(friendship);
						this.friendships[index].pending = false;
					});
			};
			this.remove = (friendship) => {
				FriendshipService.remove(friendship.id)
					.then( () => {
						let index = this.friendships.indexOf(friendship);
						this.friendships.splice(index, 1);
					});
			};

			this.order = null;
			this.isReverse = true;
			this.orderBy = (order) => {
				this.isReverse = (this.order === order) ? !this.isReverse : false;
				this.order = order;
			};
		}
	});
