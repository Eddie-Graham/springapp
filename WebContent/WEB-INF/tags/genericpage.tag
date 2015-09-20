<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="headerBar" fragment="true"%>
<%@attribute name="leftBar" fragment="true"%>
<%@attribute name="head" fragment="true"%>

<!DOCTYPE html>

<html>

<head>
<title>Fishing for Likes</title>

<link rel="stylesheet" type="text/css" href="css/genericpage.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- <script src="http://malsup.github.com/jquery.form.js"></script> -->
<script src="js/genericpage.js"></script>

<jsp:invoke fragment="head" />

</head>

<body>
	<div id="headerBar">
		<div id=title>Fishing for Likes <div id="time"></div></div>

		<jsp:invoke fragment="headerBar" />
	</div>
	<div id="body">
		<jsp:doBody />
	</div>
	<div id="leftBar">
		<jsp:invoke fragment="leftBar" />
	</div>
</body>

</html>