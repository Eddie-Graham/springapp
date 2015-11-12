<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="head">
	
		<link rel="stylesheet" type="text/css" href="css/home.css">
		<link rel="stylesheet" type="text/css" href="css/posts.css">
		<script src="js/home.js"></script> 
		<script src="js/posts.js"></script> 
		
    </jsp:attribute>
    
	<jsp:body>
	
	 	<div id="postSubmitDiv" class="pure-u-1">
	 		<form class="pure-form">
    			<input id="postText" type="text" class="pure-input-rounded">
    			<button type="button" onclick="submitPost()"
					class="pure-button pure-button-primary">Submit</button>
			</form>
	 	</div>
	 	
	 	<div id="postsDiv" class="pure-u-1">
	 	
	 		<div id="left" class="pure-u-1 pure-u-lg-3-4">
	 		
	 		<div id="filter">
	 			<div class="pure-menu pure-menu-horizontal">
	 				Sort by:  &nbsp;       
    				<ul class="pure-menu-list">
        				<li class="pure-menu-item pure-menu-has-children pure-menu-allow-hover">
            				<a id="menuLink1" class="pure-button pure-menu-link">Timestamp</a>
            				<ul class="pure-menu-children">
                				
               					<li class="pure-menu-item"><button class="pure-button dropdownBtns" onclick="refreshPostsByTimestamp()">Timestamp</button></li>
               					<li class="pure-menu-item"><button class="pure-button dropdownBtns" onclick="refreshPostsByTotal()">Total</button></li>
               					<li class="pure-menu-item"><button class="pure-button dropdownBtns" onclick="refreshPostsByLikes()">Likes</button></li>
               					<li class="pure-menu-item"><button class="pure-button dropdownBtns" onclick="refreshPostsByDislikes()">Dislikes</button></li>
               					
           					</ul>
        				</li>
    				</ul>
				</div>
			</div>
			
			<div id="postsList" class="pure-u-1 pure-u-lg-3-4"></div> 
	 		
	 		</div>
	 		
	 		<div id="right" class="pure-u-lg-1-4"></div>
	 	</div>

    </jsp:body>

</t:genericpage>