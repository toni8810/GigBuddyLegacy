<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ELFunctions" uri="CustomTagLibrary" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Messages</title>
<link rel="stylesheet" type="text/css" href="cssFiles/header.css" />
<link rel="stylesheet" type="text/css" href="../cssFiles/footer.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<script src="../scripts/jquery-1.12.1.min.js"></script>
<style type="text/css">
#cssmenu2 {
	display: block;
	float: left;
  	padding: 0;
 	margin: 0;
  	border: 0;
  	line-height: 1;
}
#cssmenu2 ul,
#cssmenu2 ul li,
#cssmenu2 ul ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
#cssmenu2 ul {
  position: relative;
  z-index: 597;
  float: left;
}
#cssmenu2 ul li {
  float: left;
  min-height: 1px;
  line-height: 1em;
  vertical-align: middle;
  position: relative;
}
#cssmenu2 ul li.hover,
#cssmenu2 ul li:hover {
  position: relative;
  z-index: 599;
  cursor: default;
}
#cssmenu2 ul ul {
  visibility: hidden;
  position: absolute;
  top: 100%;
  left: 0px;
  z-index: 598;
  width: 100%;
}
#cssmenu2 ul ul li {
  float: none;
}
#cssmenu2 ul ul ul {
  top: -2px;
  right: 0;
}
#cssmenu2 ul li:hover > ul {
  visibility: visible;
}
#cssmenu2 ul ul {
  top: 1px;
  left: 99%;
}
#cssmenu2 ul li {
  float: none;
}
#cssmenu2 ul ul {
  margin-top: 1px;
}
#cssmenu2 ul ul li {
  font-weight: normal;
}
/* Custom CSS Styles */
#cssmenu2 {
  margin-top: 50px;
  width: 200px;
  background: #333333;
  font-family: 'Oxygen Mono', Tahoma, Arial, sans-serif;
  zoom: 1;
  font-size: 12px;
}
#cssmenu2:before {
  content: '';
  display: block;
}
#cssmenu2:after {
  content: '';
  display: table;
  clear: both;
}
#cssmenu2 a {
  display: block;
  padding: 15px 20px;
  color: #ffffff;
  text-decoration: none;
  text-transform: uppercase;
}
#cssmenu2 > ul {
  width: 200px;
}
#cssmenu2 ul ul {
  width: 200px;
}
#cssmenu2 > ul > li > a {
  border-right: 4px solid #1b9bff;
  color: #ffffff;
}
#cssmenu2 > ul > li > a:hover {
  color: #ffffff;
}
#cssmenu2 > ul > li.active a {
  background: #1b9bff;
}
#cssmenu2 > ul > li a:hover,
#cssmenu2 > ul > li:hover a {
  background: #1b9bff;
  cursor: pointer;
}
#cssmenu2 li {
  position: relative;
}
#cssmenu2 ul ul li:hover > a {
  background: #4eb1ff;
  color: #ffffff;
}
#cssmenu2.align-right > ul > li > a {
  border-left: 4px solid #1b9bff;
  border-right: none;
}
#cssmenu2.align-right {
  float: right;
}
#cssmenu2.align-right li {
  text-align: right;
}
#cssmenu2.align-right ul ul {
  visibility: hidden;
  position: absolute;
  top: 0;
  left: -100%;
  z-index: 598;
  width: 100%;
}
#cssmenu2.align-right ul ul li.first {
  -webkit-border-radius: 3px 0 0 0;
  -moz-border-radius: 3px 0 0 0;
  border-radius: 3px 0 0 0;
}
#cssmenu2.align-right ul ul li.last {
  -webkit-border-radius: 0 0 0 3px;
  -moz-border-radius: 0 0 0 3px;
  border-radius: 0 0 0 3px;
}
#cssmenu2.align-right ul ul {
  -webkit-border-radius: 3px 0 0 3px;
  -moz-border-radius: 3px 0 0 3px;
  border-radius: 3px 0 0 3px;
}
#messaging {
	width: 500px;
	height: 1000px;
	margin: auto;
	overflow: auto;
}
#ballHolder {
	visibility: hidden;
	position: relative;
    width: 370px;
    height: 79px;
    padding-top: 11px;
    top: 95px;
    left: 50px;
    background: #161616 url(../images/pattern_40.gif) top left repeat;
    border: 2px solid rgba(0,183,229,0.9);
    border-radius: 5px;
}
.ball {
	background-color: rgba(0,0,0,0);
	border: 5px solid rgba(0,183,229,0.9);
	opacity: .9;
	border-top: 5px solid rgba(0,0,0,0);
	border-left: 5px solid rgba(0,0,0,0);
	border-radius: 50px;
	box-shadow: 0 0 35px #2187e7;
	width: 50px;
	height: 50px;
	margin: 0 auto;
	-moz-animation: spin .5s infinite linear;
	-webkit-animation: spin .5s infinite linear;
}
.ball1 {
	background-color: rgba(0,0,0,0);
	border:5px solid rgba(0,183,229,0.9);
	opacity:.9;
	border-top:5px solid rgba(0,0,0,0);
	border-left:5px solid rgba(0,0,0,0);
	border-radius:50px;
	box-shadow: 0 0 15px #2187e7; 
	width:30px;
	height:30px;
	margin:0 auto;
	position:relative;
	top:-50px;
	-moz-animation:spinoff .5s infinite linear;
	-webkit-animation:spinoff .5s infinite linear;
}
@-moz-keyframes spin {
	0% {
		-moz-transform: rotate(0deg);
	}
	100% {
		-moz-transform: rotate(360deg);
	};
}

@-moz-keyframes spinoff {
	0% {
		-moz-transform: rotate(0deg);
	}
	100% {
		-moz-transform: rotate(-360deg);
	};
}
@-webkit-keyframes spin {
	0% {
		-webkit-transform: rotate(0deg);
	}
	100% {
		-webkit-transform: rotate(360deg);
	};
}

@-webkit-keyframes spinoff {
	0% {
		-webkit-transform: rotate(0deg);
	}
	100% {
		-webkit-transform: rotate(-360deg);
	};
}
#messaging h1 {
	width: 400px;
	margin-left: auto;
	margin-right: auto;
	padding: 15px;
	text-align: center;
	border: outset 7px #E0E0E0;
	border-radius: 20px;
	background-color: #202020;
	font-family: "Comic Sans MS", cursive, sans-serif;
	font-size: 20px;
	color: #FFFF99;
}
.textBox {
	margin-left: 50px;
	border-radius: 5px;
	margin-bottom: 0;
	font-size: 15px;
	padding: 10px;
  	border: solid 1px #fff;
  	box-shadow: inset 1px 1px 2px 0 #707070;
  	transition: box-shadow 0.3s;
}
.nn-alert {
	margin-left: 50px;
  	margin-top: 0;
  	background:rgba(51, 59, 70, 0.5);
  	border:1px solid #111;
  	border-radius:3px;
  	box-shadow:inset 0px 30px 0px rgba(255,255,255,0.1),
    	inset 0 1px 0 rgba(255,255,255,0.4),
    	inset 0 10px 10px rgba(255,255,255,0.2),
    	inset 0 -5px 10px rgba(0,0,0,0.2),
    	0 3px 3px rgba(0,0,0,0.3);
  	width:375px;
  	height:auto;
  	color:white;
  	font-size:18px;
  	text-shadow:0 1px 1px rgba(0,0,0,0.8);
  	transition:all .5s ease;
  	backface-visibility:hidden;
}
.nn-alert strong:nth-child(2) {
	float: right;
}
.nn-alert strong:nth-child(1) {
	float: left;
	margin-left: 5px;
}
.nn-alert > p {
	clear: both;
	margin-top: 30px;
	margin-left: 5px;
	word-wrap: break-word;
}
</style>
<script type="text/javascript">
var username = "";
function showMessages(li) {
	var name = li.firstElementChild.firstElementChild.innerHTML;
	name = name.trim();
	var usernameArray = "${messageSendersName}";
	usernameArray = usernameArray.replace("[", "");
	usernameArray = usernameArray.replace("]", "");
	usernameArray = usernameArray.split(",");
	for (var i=0; i<usernameArray.length; i++) {
		if (usernameArray[i].trim() == name) {
			username = usernameArray[i+1];
			break;
		}
	}
	$.ajax({
		type:'POST',
		data: {usernameInput: username},
		url: 'showMessagesAJAXs.do',
		success: function(result) {
			$('#messaging').html(result);
		}
	});
	
}
function sendMessage(message) {
	document.getElementsByClassName("textBox")[0].onkeypress = function(e) {
		if (e.keyCode == 13) {
			document.getElementById("ballHolder").style.visibility = "visible";
			var reqId = document.getElementById("messaging").firstElementChild.id;
			$.ajax({
				type:'POST',
				data: {receiverUsername: username, messageInput: message.value,reqIdInput: reqId},
				url: 'respondToMessage.do',
				success: function(result) {
					$('#messaging').html(result);
				}
			});
		}
	}
	$(document).ajaxStop(function () {
		document.getElementById("ballHolder").style.visibility = "hidden";
	  });
	
}
</script>
</head>
<body>
<jsp:include page="htmlFiles/header.jsp">
	<jsp:param value="${pageContext.request.userPrincipal.name}" name="username" />
</jsp:include>
<div id='cssmenu2'>
	<ul>
		<c:forEach items="${messageSendersName}" var="names">
    		 <li onclick="showMessages(this)">
    		 	<a><span>${ELFunctions:substring(names,0,",")}</span></a>
    		 </li>
		</c:forEach>
   		<li class='last'><a><span></span></a></li>
	</ul>
</div>
<div id="messaging">
	<div class="nn-alert">
    	<strong>Developer</strong>
    	<strong>${ELFunctions:getDate()}</strong>
    	<p>Click on one the names to see their messages</p>
	</div>
</div>
<jsp:include page="../htmlFiles/footer.jsp" >
	<jsp:param value="../images/twitterIcon.png" name="twitterURL"/>
	<jsp:param value="../images/facebookIcon.jpe" name="facebookURL"/>
</jsp:include>
</body>
</html>