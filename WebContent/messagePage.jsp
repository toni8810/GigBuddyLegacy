<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="cssFiles/navigationBar.css" />
<link rel="stylesheet" type="text/css" href="cssFiles/footer.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<style type="text/css">
	#message {
		width: 40%;
		margin-left: auto;
		margin-right: auto;
		margin-top: 30px;
		padding: 20px;
		text-align: center;
		border: outset 7px #E0E0E0;
		border-radius: 20px;
		background-color: #202020;
		font-family: "Comic Sans MS", cursive, sans-serif;
		color: #FFFF99;
	}
</style>
<script type="text/javascript">
var countdownCounter = 10;
var countdownIntervalID;
function countdown() {
	document.getElementById("countdown").innerHTML = countdownCounter;
	console.log('In interval: '+countdownCounter);
	countdownCounter--;
	if (countdownCounter == 0) {
		clearInterval(countdownIntervalID);
		window.location.href = "/members/profile.jsp";
	}
}
</script>
</head>
<body>
	<jsp:include page="htmlFiles/navigationBar.html" />
	<h1 id="message">${message}</h1>
	<jsp:include page="htmlFiles/footer.jsp" >
		<jsp:param value="images/twitterIcon.png" name="twitterURL"/>
		<jsp:param value="images/facebookIcon.jpe" name="facebookURL"/>
	</jsp:include>
	<script type="text/javascript">
		var message = document.getElementById("message");
		console.log('Message: '+message);
		if (message.innerHTML.indexOf("Congratulations! You have sucesfully registered!") > -1) {
			console.log('Successful sign up message!');
			message.innerHTML = message.innerHTML +"<br />You will be logged in <span id='countdown'></span>";
			console.log('Setting interval');
			intervalID = setInterval(countdown, 1000);
		}
	</script>
</body>
</html>