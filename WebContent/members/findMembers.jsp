<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Find Members</title>
<link rel="stylesheet" type="text/css" href="cssFiles/header.css" />
<link rel="stylesheet" type="text/css" href="../cssFiles/footer.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<script src="../scripts/jquery-1.12.1.min.js"></script>
<script src="/scripts/Find-Members.js" type="text/javascript"></script>
<style type="text/css" >
@import url(http://fonts.googleapis.com/css?family=Cabin:400);

.webdesigntuts-workshop {
    background-color: rgba(0, 0, 15, 0.8);
    background: rgba(0, 0, 15, 0.8);
    color: rgba(0, 0, 15, 0.8);
	height: 1200px;
	margin: auto;
	text-align: center;
	width: 90%;
	overflow: auto;
}
.webdesigntuts-workshop:before {
	background: #444;
	background: -webkit-linear-gradient(left, #151515, #444, #151515);
	background: -moz-linear-gradient(left, #151515, #444, #151515);
	background: -o-linear-gradient(left, #151515, #444, #151515);
	background: -ms-linear-gradient(left, #151515, #444, #151515);
	background: linear-gradient(left, #151515, #444, #151515);
	top: 192px;
}
.webdesigntuts-workshop:after {
	background: #000;
	background: -webkit-linear-gradient(left, #151515, #000, #151515);	
	background: -moz-linear-gradient(left, #151515, #000, #151515);	
	background: -o-linear-gradient(left, #151515, #000, #151515);	
	background: -ms-linear-gradient(left, #151515, #000, #151515);	
	background: linear-gradient(left, #151515, #000, #151515);	
	top: 191px;
}
.webdesigntuts-workshop > div:first-of-type {
	background: #111;
	background: -webkit-linear-gradient(#1b1b1b, #111);
	background: -moz-linear-gradient(#1b1b1b, #111);
	background: -o-linear-gradient(#1b1b1b, #111);
	background: -ms-linear-gradient(#1b1b1b, #111);
	background: linear-gradient(#1b1b1b, #111);
	border: 1px solid #000;
	border-radius: 5px;
	box-shadow: inset 0 0 0 1px #272727;
	display: inline-block;
	font-size: 0px;
	margin: 0 auto 0;
	padding: 20px;
	position: relative;
	z-index: 1;
}
.webdesigntuts-workshop input {
	background: #222;
	background: -webkit-linear-gradient(#333, #222);	
	background: -moz-linear-gradient(#333, #222);	
	background: -o-linear-gradient(#333, #222);	
	background: -ms-linear-gradient(#333, #222);	
	background: linear-gradient(#333, #222);	
	border: 1px solid #444;
	border-radius: 5px 0 0 5px;
	box-shadow: 0 2px 0 #000;
	color: #888;
	display: block;
	float: left;
	font-family: 'Cabin', helvetica, arial, sans-serif;
	font-size: 13px;
	font-weight: 400;
	height: 40px;
	margin: 0;
	padding: 0 10px;
	text-shadow: 0 -1px 0 #000;
	width: 200px;
}
.ie .webdesigntuts-workshop input {
	line-height: 40px;
}

.webdesigntuts-workshop input::-webkit-input-placeholder {
   color: #888;
}

.webdesigntuts-workshop input:-moz-placeholder {
   color: #888;
}
.webdesigntuts-workshop input:focus {
	-webkit-animation: glow 800ms ease-out infinite alternate;
	-moz-animation: glow 800ms ease-out infinite alternate;
	-o-animation: glow 800ms ease-out infinite alternate;
	-ms-animation: glow 800ms ease-out infinite alternate;
	animation: glow 800ms ease-out infinite alternate;
	background: #222922;
	background: -webkit-linear-gradient(#333933, #222922);
	background: -moz-linear-gradient(#333933, #222922);
	background: -o-linear-gradient(#333933, #222922);
	background: -ms-linear-gradient(#333933, #222922);
	background: linear-gradient(#333933, #222922);
	border-color: #393;
	box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
	color: #efe;
	outline: none;
}
.webdesigntuts-workshop input:focus::-webkit-input-placeholder { 
	color: #efe;
}

.webdesigntuts-workshop input:focus:-moz-placeholder {
	color: #efe;
}
.webdesigntuts-workshop button {
	background: #222;
	background: -webkit-linear-gradient(#333, #222);
	background: -moz-linear-gradient(#333, #222);
	background: -o-linear-gradient(#333, #222);
	background: -ms-linear-gradient(#333, #222);
	background: linear-gradient(#333, #222);
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	-o-box-sizing: content-box;
	-ms-box-sizing: content-box;
	box-sizing: content-box;
	border: 1px solid #444;
	border-left-color: #000;
	border-radius: 0 5px 5px 0;
	box-shadow: 0 2px 0 #000;
	color: #fff;
	display: block;
	float: left;
	font-family: 'Cabin', helvetica, arial, sans-serif;
	font-size: 13px;
	font-weight: 400;
	height: 40px;
	line-height: 40px;
	margin: 0;
	padding: 0;
	position: relative;
	text-shadow: 0 -1px 0 #000;
	width: 80px;
}
.webdesigntuts-workshop button:hover,
.webdesigntuts-workshop button:focus {
	background: #292929;
	background: -webkit-linear-gradient(#393939, #292929);	
	background: -moz-linear-gradient(#393939, #292929);	
	background: -o-linear-gradient(#393939, #292929);	
	background: -ms-linear-gradient(#393939, #292929);	
	background: linear-gradient(#393939, #292929);
	color: #5f5;
	outline: none;
}
.webdesigntuts-workshop button:active {
	background: #292929;
	background: -webkit-linear-gradient(#393939, #292929);
	background: -moz-linear-gradient(#393939, #292929);
	background: -o-linear-gradient(#393939, #292929);
	background: -ms-linear-gradient(#393939, #292929);
	background: linear-gradient(#393939, #292929);
	box-shadow: 0 1px 0 #000, inset 1px 0 1px #222;
	top: 1px;
}
@-webkit-keyframes glow {
    0% {
		border-color: #393;
		box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
    }	
    100% {
		border-color: #6f6;
		box-shadow: 0 0 20px rgba(0,255,0,.6), inset 0 0 10px rgba(0,255,0,.4), 0 2px 0 #000;
    }
}
@-moz-keyframes glow {
    0% {
		border-color: #393;
		box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
    }	
    100% {
		border-color: #6f6;
		box-shadow: 0 0 20px rgba(0,255,0,.6), inset 0 0 10px rgba(0,255,0,.4), 0 2px 0 #000;
    }
}
@-o-keyframes glow {
    0% {
		border-color: #393;
		box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
    }	
    100% {
		border-color: #6f6;
		box-shadow: 0 0 20px rgba(0,255,0,.6), inset 0 0 10px rgba(0,255,0,.4), 0 2px 0 #000;
    }
}
@-ms-keyframes glow {
    0% {
		border-color: #393;
		box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
    }	
    100% {
		border-color: #6f6;
		box-shadow: 0 0 20px rgba(0,255,0,.6), inset 0 0 10px rgba(0,255,0,.4), 0 2px 0 #000;
    }
}
@keyframes glow {
    0% {
		border-color: #393;
		box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
    }	
    100% {
		border-color: #6f6;
		box-shadow: 0 0 20px rgba(0,255,0,.6), inset 0 0 10px rgba(0,255,0,.4), 0 2px 0 #000
		}
}
.table31 {
	display: none;
	margin:0px;
	padding:0px;
	width:100%;
	box-shadow: 10px 10px 5px #888888;
	border:1px solid #000000;
	-moz-border-radius-bottomleft:14px;
	-webkit-border-bottom-left-radius:14px;
	border-bottom-left-radius:14px;
	-moz-border-radius-bottomright:14px;
	-webkit-border-bottom-right-radius:14px;
	border-bottom-right-radius:14px;
	-moz-border-radius-topright:14px;
	-webkit-border-top-right-radius:14px;
	border-top-right-radius:14px;
	-moz-border-radius-topleft:14px;
	-webkit-border-top-left-radius:14px;
	border-top-left-radius:14px;
}
.table31 table{
	width:100%;
	height:100%;
	margin:0px;
	padding:0px;
}
.table31 tr:last-child td:last-child {
	-moz-border-radius-bottomright:14px;
	-webkit-border-bottom-right-radius:14px;
	border-bottom-right-radius:14px;
}
.table31 table tr:first-child td:first-child {
	-moz-border-radius-topleft:14px;
	-webkit-border-top-left-radius:14px;
	border-top-left-radius:14px;
}
.table31 table tr:first-child td:last-child {
	-moz-border-radius-topright:14px;
	-webkit-border-top-right-radius:14px;
	border-top-right-radius:14px;
}
.table31 tr:last-child td:first-child {
	-moz-border-radius-bottomleft:14px;
	-webkit-border-bottom-left-radius:14px;
	border-bottom-left-radius:14px;
}
.table31 tr:hover td {
	background-color:#ffffff;
}
.table31 td {
	vertical-align:middle;
	background-color:#e5e5e5;
	border:1px solid #000000;
	border-width:0px 1px 1px 0px;
	text-align:left;
	padding:7px;
	font-size:10px;
	font-family:Arial;
	font-weight:normal;
	color:#000000;
}
.table31 td img {
	width: 100px;
}
.table31 tr:last-child td {
	border-width:0px 1px 0px 0px;
}
.table31 tr td:last-child {
	border-width:0px 0px 1px 0px;
}
.table31 tr:last-child td:last-child {
	border-width:0px 0px 0px 0px;
}
.table31 tr:first-child td {
	background:-o-linear-gradient(bottom, #4c4c4c 5%, #000000 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #4c4c4c), color-stop(1, #000000) );	background:-moz-linear-gradient( center top, #4c4c4c 5%, #000000 100% );	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#4c4c4c", endColorstr="#000000");	background: -o-linear-gradient(top,#4c4c4c,000000);
	background-color:#4c4c4c;
	border:0px solid #000000;
	text-align:center;
	border-width:0px 0px 1px 1px;
	font-size:14px;
	font-family:Arial;
	font-weight:bold;
	color:#ffffff;
}
.table31 tr:first-child:hover td {
	background:-o-linear-gradient(bottom, #4c4c4c 5%, #000000 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #4c4c4c), color-stop(1, #000000) );	background:-moz-linear-gradient( center top, #4c4c4c 5%, #000000 100% );	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#4c4c4c", endColorstr="#000000");	background: -o-linear-gradient(top,#4c4c4c,000000);
	background-color:#4c4c4c;
}
.table31 tr:first-child td:first-child {
	border-width:0px 0px 1px 0px;
}
.table31 tr:first-child td:last-child {
	border-width:0px 0px 1px 1px;
} 
.hidden {
	display: none;
}
.profilePic:hover {
	cursor: pointer;
}
.closeButton {
	position: absolute;
	top: 0;
	right: 0;
	z-index: 400; 
}
.closeButton:hover {
	cursor: pointer; 
}
#slideShowPopUpContainer {
	display: none;
	position: fixed;
	top: 5%;
	bottom: 5%;
	left: 5%;
	right: 5%;	
	width: 90%;
	background-color: rgba(182, 204, 250, 0.9);
	padding-top: 170px;
	padding-bottom: 170px;
	z-index: 200;
	text-align: center;
}
#forwardArrow {
	position:absolute;
    top:0;
    bottom:0;
    right: 30px;
    margin:auto;
	z-index: 400; 
}
#forwardArrow:hover {
	cursor: pointer;
}
#backArrow {
	position:absolute;
    top:0;
    bottom:0;
    left: 30px;
    margin:auto;
	z-index: 400; 
}
#backArrow:hover {
	cursor: pointer;
}
#dynamicImage {
	position:absolute;
    top:0;
    bottom:0;
    margin:auto;
    left: 0;
    right: 0;
	z-index: 300;
}
.hidden {
	display: none;
}
@media only screen and (max-device-width: 900px) {
	.webdesigntuts-workshop {
    	background-color: rgba(0, 0, 15, 0.8);
    	background: rgba(0, 0, 15, 0.8);
    	color: rgba(0, 0, 15, 0.8);
		height: 1200px;
		margin-left: 0;
		text-align: center;
		width: 75%;
		overflow: auto;
	}
	.webdesigntuts-workshop input {
		background: #222;
		background: -webkit-linear-gradient(#333, #222);	
		background: -moz-linear-gradient(#333, #222);	
		background: -o-linear-gradient(#333, #222);	
		background: -ms-linear-gradient(#333, #222);	
		background: linear-gradient(#333, #222);	
		border: 1px solid #444;
		border-radius: 5px 0 0 5px;
		box-shadow: 0 2px 0 #000;
		color: #888;
		display: block;
		float: left;
		font-family: 'Cabin', helvetica, arial, sans-serif;
		font-size: 42px;
		font-weight: 400;
		height: 70px;
		margin: 0;
		padding: 0 10px;
		text-shadow: 0 -1px 0 #000;
		width: 300px;
	}
	.webdesigntuts-workshop button {
		background: #222;
		background: -webkit-linear-gradient(#333, #222);
		background: -moz-linear-gradient(#333, #222);
		background: -o-linear-gradient(#333, #222);
		background: -ms-linear-gradient(#333, #222);
		background: linear-gradient(#333, #222);
		-webkit-box-sizing: content-box;
		-moz-box-sizing: content-box;
		-o-box-sizing: content-box;
		-ms-box-sizing: content-box;
		box-sizing: content-box;
		border: 1px solid #444;
		border-left-color: #000;
		border-radius: 0 5px 5px 0;
		box-shadow: 0 2px 0 #000;
		color: #fff;
		display: block;
		float: left;
		font-family: 'Cabin', helvetica, arial, sans-serif;
		font-size: 24px;
		font-weight: 400;
		height: 70px;
		line-height: 40px;
		margin: 0;
		padding: 0;
		position: relative;
		text-shadow: 0 -1px 0 #000;
		width: 140px;
	}
	.table31 td {
		vertical-align:middle;
		background-color:#e5e5e5;
		border:1px solid #000000;
		border-width:0px 1px 1px 0px;
		text-align:left;
		padding:7px;
		font-size:20px;
		font-family:Arial;
		font-weight:normal;
		color:#000000;
	}
	.table31 td img {
		width: 200px;
	}
	#forwardArrow {
		position:absolute;
    	top:0;
    	bottom:0;
    	right: 30px;
    	margin:auto;
		z-index: 400;
		width: 100px; 
	}
	#backArrow {
		position:absolute;
    	top:0;
    	bottom:0;
    	left: 30px;
    	margin:auto;
		z-index: 400; 
		width: 100px;
	}
	#footer {
		margin-top: 250px;
		width: 100%;
		height: 50px;
		background-color: #A0A0A0;
		overflow: hidden;
	}
}
</style>
</head>
<body>
<jsp:include page="htmlFiles/header.jsp">
	<jsp:param value="${pageContext.request.userPrincipal.name}" name="username" />
</jsp:include>
<div class="webdesigntuts-workshop">
	<div>		    
		<input type="text" placeholder="Search..." name="name" />		    	
		<button onclick="getResults()">Search</button>
	</div>
	<div class="table31">
		<table>
    		<tbody>
  			</tbody>
  		</table>														
	</div>
</div>
<div id="slideShowPopUpContainer">
	<img class="closeButton" src="../images/closeButton.png" alt="closeButton" title="close window" onclick="closePopUp(this)" />
	<img id="forwardArrow" src="../images/forward-arrow.png" alt="forward-arrow" title="show next image" onclick="showNextImage()" />
	<img id="backArrow" src="../images/back-arrow.png" alt="back-arrow" title="show previous image" onclick="showPreviousImage()" />
	<img id="dynamicImage" />
</div>
<ol class="hidden">
	
</ol>
<jsp:include page="../htmlFiles/footer.jsp" >
	<jsp:param value="../images/twitterIcon.png" name="twitterURL"/>
	<jsp:param value="../images/facebookIcon.jpe" name="facebookURL"/>
</jsp:include>
</body>
</html>