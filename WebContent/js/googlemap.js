// Note: This example requires that you consent to location sharing when
// prompted by your browser. If you see the error "The Geolocation service
// failed.", it means you probably did not give permission for the browser to
// locate you.

function initMap() {
	
	var HTML5 = false;

	var map = new google.maps.Map(document.getElementById('map'), {
		center : {
			// Glasgow, UK
			lat : 55.864237,
			lng : -4.251806
		},
		zoom : 10
	});

//	var infoWindow = new google.maps.InfoWindow({
//		map : map
//	});

	// Try HTML5 geolocation.
	if (navigator.geolocation) {
		
		HTML5 = true;
		
		navigator.geolocation.getCurrentPosition(function(position) {
			var pos = {
				lat : position.coords.latitude,
				lng : position.coords.longitude
			};
			
			$.ajax({
				type : "POST",
				url : "postLatLong.html",
				data: {"latitude": position.coords.latitude,
					"longitude": position.coords.longitude},
					
				success : function(response) {
						
					setMarkers(map);
					map.setCenter(pos);
				}
			});
			
			//alert(position.coords.latitude);

			//infoWindow.setPosition(pos);
			//infoWindow.setContent('Location found.');
			
		}, function() {
			
			// set markers geo position denied
			setMarkers(map);
		});
	} 
	
//	else {
//		// Browser doesn't support Geolocation
//		handleLocationError(false, infoWindow, map.getCenter());
//	}

	if(!HTML5)
		setMarkers(map);
}

//function handleLocationError(browserHasGeolocation, infoWindow, pos) {
//	infoWindow.setPosition(pos);
//	infoWindow
//			.setContent(browserHasGeolocation ? 'Error: The Geolocation service failed.'
//					: 'Error: Your browser doesn\'t support geolocation.');
//}

function setMarkers(map) {
	// Adds markers to the map.

	$.ajax({
		type : "GET",
		url : "getUsers.html",

		success : function(response) {

			// response is a json list of users
			var users = JSON.parse(response);

			for (var i = 0; i < users.length; i++) {
				
				var user = users[i];

				makeMarker(map, user);
			}
		}
	});
}

function makeMarker(map, user){
	
	if(user.hasProfilePic)
		var imagePath = 'profile_images/' + user.id + '.png';
	else
		var imagePath = 'images/profile_default.png';
	
	var contentString = '<div id="content">' +
	'<a href="viewuser.html?id=' + user.id + '"><img id="profilePic" style="width: 150px; max-height: 200px;" src="' + imagePath + '" alt="Profile pic"></a>' +
	'<div style="text-align: center; line-height: 30px; font-size: 20px;"><a href="viewuser.html?id=' + user.id + '" class="links"><strong>' + user.username + '</strong></a></div>' +
	'</div>';
		
	var infowindow = new google.maps.InfoWindow({
		content: contentString
	});
	
	var marker = new google.maps.Marker({
		position : {
			lat : user.latitude,
			lng : user.longitude
		},
		map : map,
		//icon : image,
		//shape : shape,
		title : user.username
	});
	
	marker.addListener('click', function() {
	    infowindow.open(map, marker);
	  });
	
}