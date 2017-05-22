<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>GigBuddy</title>
<meta content="Find Gigbuddies Who You Can Go To Gigs Or Parties With On gigbuddy.com" name="description" />
<meta content="gigbuddies, parties, gigs, events" name="keywords" />
<link rel="stylesheet" type="text/css" href="/cssFiles/footer.css" />
<link rel="stylesheet" type="text/css" href="/members/cssFiles/header.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<style>
	#sideBar {
		width: 200px;
		text-align: center;
		margin-left: 100px;
	}
	#events {
		margin-top: 80px;
		border: outset 7px #E0E0E0;
		background-color: black;
		color: white;
	}
	#welcomeMessage {
		width: 30%;
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
	h2 {
		color: #E0E0E0;
		font-size: 40px;
	}
	button {
		margin-top: 20px;
	}
</style>
</head>
<body>
<jsp:include page="/members/htmlFiles/header.jsp">
	<jsp:param value="${pageContext.request.userPrincipal.name}" name="username" />
</jsp:include>
<h1 id="welcomeMessage">Find Your Gig Buddies</h1>
<div id="sideBar">
	<div id="events">
		<h2>Events</h2>
		<hr />
		<p>Getting events here based on the location of the user</p>
		<hr />
		<p>Some event here</p>
		<button>Add event</button>
	</div>
</div>
<jsp:include page="/htmlFiles/footer.jsp" >
	<jsp:param value="/images/twitterIcon.png" name="twitterURL"/>
	<jsp:param value="/images/facebookIcon.jpe" name="facebookURL"/>
</jsp:include>
</body>
</html>