<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="left" fragment="true"%>
<%@attribute name="head" fragment="true"%>

<!DOCTYPE html>
<html>
<head>
<title>Fishing for Likes</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">

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