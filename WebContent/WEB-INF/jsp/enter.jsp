<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html>
<head>
<style>
html { 
  background: url(images/skull.jpg) no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover; 
}

#time{
     position:absolute;
     bottom:0;
     right:0;
     font-size: 700%;
     color:#FFFFFF;
}

#enter{
     position:absolute;
     bottom:0;
     left:0;
}

button {
    background-color: Transparent;
    background-repeat:no-repeat;
    border: none;
    cursor:pointer;
    overflow: hidden;
    outline:none;
}

#enterBtn{
	font-size: 700%;
	color:#FFFFFF;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/util.js"></script>
<script>
$(document).ready(function(){
	updateClock("time");
});
</script>


</head>
<body>
<div id="time"></div>
<div id="enter"><button type="button" id="enterBtn" onclick="location.href = '/springapp/mainMenu.html';">Enter</button></div>


</body>
</html>