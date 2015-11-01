// Note: This example requires that you consent to location sharing when
// prompted by your browser. If you see the error "The Geolocation service
// failed.", it means you probably did not give permission for the browser to
// locate you.

function initMap() {

	var map = new google.maps.Map(document.getElementById('map'), {
		center : {
			lat : -34.397,
			lng : 150.644
		},
		zoom : 6
	});

	var infoWindow = new google.maps.InfoWindow({
		map : map
	});

	// Try HTML5 geolocation.
	if (navigator.geolocation) {
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
						
					
				}
			});
			
			//alert(position.coords.latitude);

			//infoWindow.setPosition(pos);
			//infoWindow.setContent('Location found.');
			map.setCenter(pos);
		}, function() {
			handleLocationError(true, infoWindow, map.getCenter());
		});
	} else {
		// Browser doesn't support Geolocation
		handleLocationError(false, infoWindow, map.getCenter());
	}

	setMarkers(map);
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	infoWindow.setPosition(pos);
	infoWindow
			.setContent(browserHasGeolocation ? 'Error: The Geolocation service failed.'
					: 'Error: Your browser doesn\'t support geolocation.');
}

function setMarkers(map) {
	// Adds markers to the map.

	$.ajax({
		type : "GET",
		url : "getUsers.html",

		success : function(response) {

			// response is a json list of users
			var jsonData = JSON.parse(response);

			for (var i = 0; i < jsonData.length; i++) {
				
				var user = jsonData[i];

				// Marker sizes are expressed as a Size of X,Y where the origin of the image
				// (0,0) is located in the top left of the image.

				// Origins, anchor positions and coordinates of the marker increase in the X
				// direction to the right and in the Y direction down.
				var image = {
					url : '/springapp/images/background.jpg',
					// This marker is 20 pixels wide by 32 pixels high.
					scaledSize : new google.maps.Size(50, 50),
					// The origin for this image is (0, 0).
					origin : new google.maps.Point(0, 0),
					// The anchor for this image is the base of the flagpole at (0, 32).
					anchor : new google.maps.Point(0, 50)
				};
				
				// Shapes define the clickable region of the icon. The type defines an HTML
				// <area> element 'poly' which traces out a polygon as a series of X,Y points.
				// The final coordinate closes the poly by connecting to the first coordinate.
				var shape = {
					coords : [ 0, 0, 50, 50 ],
					type : 'rect'
				};

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
			}
		}
	});
}