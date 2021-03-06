<%@tag description="Overall Page template" pageEncoding="UTF-8" %>

<%@attribute name="head" fragment="true" %>
<%@ include file="/WEB-INF/jsp/common_components/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Fishing for Likes</title>

    <link rel="stylesheet" type="text/css" href="css/genericpage.css">

    <!-- pure.css -->
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <!-- Collapsible -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <%-- Angular --%>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

    <jsp:invoke fragment="head"/>

</head>
<body>

<div id="layout">
    <!-- Menu toggle -->
    <a href="#menu" id="menuLink" class="menu-link">
        <!-- Hamburger icon -->
        <span></span>
    </a>

    <div id="menu">
        <div class="pure-menu">
            <a class="pure-menu-heading" href="home.html">Fish !
                <img id="fish" src="/springapp/images/fish.png" alt="fish">
            </a>
            <a id="time" class="pure-menu-heading" href="home.html"></a>

            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="home.html" class="pure-menu-link">Home</a></li>
                <li class="pure-menu-item"><a href="myprofile.html?id=${userSesh.id}" class="pure-menu-link">My
                    Profile</a></li>
                <li class="pure-menu-item"><a href="messagecentre.html" class="pure-menu-link">Message Centre</a></li>
                <li class="pure-menu-item"><a href="usermap.html" class="pure-menu-link">User Map</a></li>
                <li class="pure-menu-item"><a href="about.html" class="pure-menu-link">About</a></li>
                <li class="pure-menu-item"><a href="#" class="pure-menu-link">Contact</a></li>

                <c:if test="${userSesh.authority == 'ROLE_ADMIN'}">
                    <li class="pure-menu-item"><a href="admin.html" class="pure-menu-link">Admin</a></li>
                </c:if>

                <li class="pure-menu-item"><a href="logout" class="pure-menu-link">Logout</a></li>
            </ul>
        </div>


    </div>

    <div id="main">

        <jsp:doBody/>

    </div>
</div>

<script src="js/genericpage.js"></script>
<script src="js/common_components/clock.js"></script>

</body>
</html>