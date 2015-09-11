<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="left" fragment="true"%>
<%@attribute name="head" fragment="true"%>

<!DOCTYPE html>
<html>
<head>
<title>Fishing for Likes</title>

<style>
#headerBar {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	height: 20%;
	background: #1975D1;
}

#leftBar {
	position: absolute;
	left: 0;
	bottom: 0;
	top: 20%;
	width: 20%;
	background: #B2D1F0;
}

#body {
	position: absolute;
	left: 20%;
	right: 0;
	top: 20%;
	bottom: 0;
	background: #FFFFFF;
}

#title {
	position: absolute; 
	color: #FFFFFF;
	right: 35%;
	top: 5%;
	font-size: 250%;
}

#time{
	position: absolute; 
	color: #FFFFFF;
	right: 0;
	top: 5%;
	font-size: 150%;
}

</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/util.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>

<jsp:invoke fragment="head" />
</head>
<body>
	<div id="headerBar">
		<div id=title>Fishing for Likes</div>
		<div id="time"></div>
		<jsp:invoke fragment="header" />
	</div>
	<div id="body">
		<jsp:doBody />
	</div>
	<div id="leftBar">
		<jsp:invoke fragment="left" />
	</div>
</body>
</html>