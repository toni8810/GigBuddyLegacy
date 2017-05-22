<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Looking for a Concert Buddy? | GigBuddy</title>
<meta content="Welcome to Gigbuddy! It is a place to find people to go to concerts with. You can post buddy requests, respond to requests and add events." name="description" />
<meta content="gigbuddies, parties, gigs, events" name="keywords" />
<link rel="shortcut icon" href="/favicon.ico" />
<link rel="canonical" href="http://www.gigbuddy.org/members/index.jsp" />
<script src="/scripts/jquery-1.12.1.min.js" type="text/javascript"></script>
<script src='https://www.google.com/recaptcha/api.js' type="text/javascript"></script>
<script src="/scripts/indexForm.js" type="text/javascript"></script>
<style type="text/css">
	@import url(http://fonts.googleapis.com/css?family=Open+Sans);
	#sideBar {
		margin-top: 20px;
		width: 200px;
		text-align: center;
		margin-left: 50px;
	}
	#events {
		margin-top: 80px;
		border: outset 7px #E0E0E0;
		background-color: black;
		color: white;
	}
	#aboutHolder {
		float: right;
		width: 65%;
	}
	#about {
		width: 500px;
		float: left;
		background-image: url(../images/main-back.jpg);
		background-repeat: repeat-x;
		background-color: #45484C;
		margin-top: 20px;
		margin-bottom: 20px;
		margin-left: auto;
		margin-right:auto;
		padding: 40px;
		position: relative;
		border: 2px ridge #fdf7f7;
		border-left: 2px groove #fdf7f7;
		border-top: 2px groove #fdf7f7;
		font-family: Georgia, "Times New Roman", Times, serif;
		padding-top: 20px;
		border-radius: 98px 98px 98px 98px;
		-moz-border-radius: 98px 98px 98px 98px;
		-webkit-border-radius: 98px 98px 98px 98px;
		
	}
	#header {
		color: #dee7c8;
	}
	#header > h2 {
		font-family: Georgia, "Times New Roman", Times, serif;
		font-size: 40px;
		color: #fdf7f7;
		letter-spacing: -5px;
		clear: both;
		font-weight: normal;
		text-align: center;
		line-height: 60px;
		margin-left: 0px;
		margin-right: 0px;
		margin-top: 20px;
		margin-bottom: 0px;
		padding: 0px;
		font-style: normal;
	}
	.message {
		font-family: Georgia, "Times New Roman", Times, serif;
		font-size: 80px;
		color: #fdf7f7;
		letter-spacing: -5px;
		clear: both;
		font-weight: normal;
		text-align: center;
		line-height: 100px;
		margin: 0px;
		padding: 0px;
		list-style-type: none;
	}
	#article {
		padding: 10px;
		width: 680px;
		display: inline;
		text-align: justify;
		color: #FDF7F7;
		font-size: 14px;
		font-weight: normal;
		letter-spacing: normal;
	}
	#article h2 {
		font-family: Georgia, "Times New Roman", Times, serif;
		font-size: 36px;
		color: #fdf7f7;
		letter-spacing: -1px;
		font-weight: normal;
		text-align: left;
		line-height: 36px;
		margin-top: 10px;
		margin-bottom: 0px;
		margin-left: 0px;
		margin-right: 0px;
		padding: 0px;
	}
	#events > h2 {
		color: #E0E0E0;
		font-size: 40px;
	}
	button {
		margin-top: 20px;
	}
	#popUpRegistrationBox form button {
		margin-top: 0;
	}
	/* NOTE: The styles were added inline because Prefixfree needs access to your styles and they must be inlined if they are on local disk! */
    .btn {
     	display: inline-block;
     	*display: inline;
     	*zoom: 1;
     	padding: 4px 10px 4px;
     	margin-bottom: 10px;
        font-size: 13px;
        line-height: 18px;
        color: #333333;
        text-align: center;
        text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
        vertical-align: middle;
        background-color: #f5f5f5;
        background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
        background-image: -ms-linear-gradient(top, #ffffff, #e6e6e6);
        background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
        background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
        background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
        background-image: linear-gradient(top, #ffffff, #e6e6e6);
        background-repeat: repeat-x;
        filter: progid:dximagetransform.microsoft.gradient(startColorstr=#ffffff, endColorstr=#e6e6e6, GradientType=0);
        border-color: #e6e6e6 #e6e6e6 #e6e6e6;
        border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
        border: 1px solid #e6e6e6; -webkit-border-radius: 4px;
        -moz-border-radius: 4px;
        border-radius: 4px;
        -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        cursor: pointer; *margin-left: .3em;
        }
	.btn:hover, .btn:active, .btn.active, .btn.disabled, .btn[disabled] {
	 	background-color: #e6e6e6;
	 	}
	.btn-large {
	 	padding: 9px 14px;
	 	font-size: 15px;
	 	line-height: normal;
	 	-webkit-border-radius: 5px;
	 	-moz-border-radius: 5px;
	 	border-radius: 5px;
	 	}
	.btn:hover {
	 	color: #333333;
	 	text-decoration: none;
	 	background-color: #e6e6e6;
	 	background-position: 0 -15px;
	 	-webkit-transition: background-position 0.1s linear;
	 	-moz-transition: background-position 0.1s linear;
	 	-ms-transition: background-position 0.1s linear;
	 	-o-transition: background-position 0.1s linear;
	 	transition: background-position 0.1s linear;
	 	}
	.btn-primary, .btn-primary:hover { 
		text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
		color: #ffffff;
		 }
	.btn-primary.active {
	 	color: rgba(255, 255, 255, 0.75); 
	 	}
	.btn-primary {
		 background-color: #4a77d4; 
		 background-image: -moz-linear-gradient(top, #6eb6de, #4a77d4);
		 background-image: -ms-linear-gradient(top, #6eb6de, #4a77d4);
		 background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#6eb6de), to(#4a77d4)); 
		 background-image: -webkit-linear-gradient(top, #6eb6de, #4a77d4);
		 background-image: -o-linear-gradient(top, #6eb6de, #4a77d4);
		 background-image: linear-gradient(top, #6eb6de, #4a77d4);
		 background-repeat: repeat-x; filter: progid:dximagetransform.microsoft.gradient(startColorstr=#6eb6de, endColorstr=#4a77d4, GradientType=0);
		 border: 1px solid #3762bc;
		 text-shadow: 1px 1px 1px rgba(0,0,0,0.4);
		 box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.5);
		 }
	.btn-primary:hover, .btn-primary:active, .btn-primary.active, .btn-primary.disabled, .btn-primary[disabled] {
	 	filter: none;
	 	background-color: #4a77d4;
	 	 }
	.btn-block {
		 width: 100%;
		 display:block;
		  }
	* {
	 -webkit-box-sizing:border-box;
	 -moz-box-sizing:border-box;
	 -ms-box-sizing:border-box;
	 -o-box-sizing:border-box;
	  box-sizing:border-box;
	  }
	.login {
		border: outset 7px #E0E0E0;
		padding: 10px;
		font-family: 'Open Sans', sans-serif;
		background: #092756;
		background: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%);
		background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
		background: -o-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
		background: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
		background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3E1D6D', endColorstr='#092756',GradientType=1 ); 
		width:200px;
		height:380px;
	}
	.login > p {
		color: #fff;
		text-shadow: 0 0 10px rgba(0,0,0,0.3);
		letter-spacing:1px;
		text-align:center;
		font-size: 40px;
		margin-bottom: 30px;
		margin-top: 0;
		 }
 	#facebookLogIn {
 		display:block;
 		margin-right: 50px; 
 		transform:scale(1.35);
		-webkit-transform:scale(1.35);
		transform-origin:0 0;
		-webkit-transform-origin:0 0; 
 	}
 	#facebookLogInInPopup { 
 		position: relative;
 		bottom: 20px;
 		transform:scale(1.15);
		-webkit-transform:scale(1.15);
		transform-origin:0 0;
		-webkit-transform-origin:0 0;
 	}
	input { 
		width: 100%; 
		margin-bottom: 10px; 
		background: rgba(0,0,0,0.3);
		border: none;
		outline: none;
		padding: 10px;
		font-size: 13px;
		color: #fff;
		text-shadow: 1px 1px 1px rgba(0,0,0,0.3);
		border: 1px solid rgba(0,0,0,0.3);
		border-radius: 4px;
		box-shadow: inset 0 -5px 45px rgba(100,100,100,0.2), 0 1px 1px rgba(255,255,255,0.2);
		-webkit-transition: box-shadow .5s ease;
		-moz-transition: box-shadow .5s ease;
		-o-transition: box-shadow .5s ease;
		-ms-transition: box-shadow .5s ease;
		transition: box-shadow .5s ease;
	}
	input:focus {
	 	box-shadow: inset 0 -5px 45px rgba(100,100,100,0.4), 0 1px 1px rgba(255,255,255,0.2);
	}
	#notRegistered {
		font-size: 12px;
		color: #fff;
		text-shadow: 0 0 10px rgba(0,0,0,0.3);
		text-align:center;
	}
	#popUpRegistrationBoxContainer {
		display: none;
		position: fixed;
		top: 5%;
		bottom: 5%;
		left: 5%;
		right: 5%;	
		width: 90%;
		background-color: rgba(182, 204, 250, 0.7);
	}
	#popUpRegistrationBox {
		margin: auto;
 		position: absolute;
  		top: 0;
  		left: 0;
  		bottom: 0;
  		right: 0;
		border: outset 7px #E0E0E0;
		padding: 10px;
		font-family: 'Open Sans', sans-serif;
		background: #092756;
		background: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%);
		background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
		background: -o-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
		background: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
		background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3E1D6D', endColorstr='#092756',GradientType=1 ); 
		width:200px;
		height:380px;
	}
	#popUpRegistrationBox > p {
		color: #fff;
		text-shadow: 0 0 10px rgba(0,0,0,0.3);
		letter-spacing:1px;
		text-align:center;
		font-size: 40px;
		margin-bottom: 30px;
		margin-top: 0;
	}
	.g-recaptcha {
		transform:scale(0.54);
		-webkit-transform:scale(0.54);
		transform-origin:0 0;
		-webkit-transform-origin:0 0;
	}
	#messagePopUpContainer {
		display: none;
		align-items: center;
    	justify-content: center;
		position: fixed;
		top: 5%;
		bottom: 5%;
		left: 5%;
		right: 5%;	
		width: 90%;
		background-color: rgba(182, 204, 250, 0.9);
	}
	#messagePopUpContainer h1 {
 		display:table-cell;
 		vertical-align:middle
	}
	#waitPopUpContainer {
		display: none;
		position: fixed;
		top: 0;
		bottom: 0;	
		width: 100%;
		background-color: rgba(182, 204, 250, 0.7);
		padding-top: 18%;
		padding-bottom: 18%;
	}
	.ball {
		position: absolute;
		top: 40%;
		left: 40%;
		background-color: rgba(0,0,0,0);
		border: 5px solid rgba(0,183,229,0.9);
		opacity: .9;
		border-top: 5px solid rgba(0,0,0,0);
		border-left: 5px solid rgba(0,0,0,0);
		border-radius: 50px;
		box-shadow: 0 0 35px #2187e7;
		width: 200px;
		height: 200px;
		/*margin: 0 auto; */
		-moz-animation: spin .5s infinite linear;
		-webkit-animation: spin .5s infinite linear;
	}
	.ball1 {
		position: absolute;
		top: 43%;
		left: 43%;
		background-color: rgba(0,0,0,0);
		border:5px solid rgba(0,183,229,0.9);
		opacity:.9;
		border-top:5px solid rgba(0,0,0,0);
		border-left:5px solid rgba(0,0,0,0);
		border-radius:50px;
		box-shadow: 0 0 15px #2187e7; 
		width:120px;
		height:120px;
		margin:0 auto;
		/*position:relative;
		top:-160px; */
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
	.closeButton {
		position: absolute;
		top: 0;
		right: 0; 
	}
	.closeButton:hover {
		cursor: pointer; 
	}
	#footer {
		margin-top: 250px;
		width: 100%;
		height: 50px;
		background-color: #A0A0A0;
		overflow: hidden;
	}
	#smallFooter {
		width: 60%;
		height: 30px;
		margin-left: auto;
		margin-right: auto;
		margin-top: 10px;
		background-color: black;
		box-shadow: 0px 0px 15px 8px #FFFF99;
		color: #E0E0E0;
	}
	.left {
		float: left;
		margin-top: 5px;
		margin-left: 10px;
	}
	.right {
		float: right;
		margin-top: 5px;
	}
	.socialNetworkIcons {
		margin-right: 15px;
	}
	body {
		background-image: URL('../images/concert.jpg');
		background-attachment: fixed;
		background-repeat: no-repeat;
		background-size: 100% auto;
		margin: 0;
	}
	#navigationBarHolder {
		margin-top: 50px;
		width: 100%;
		height: 80px;
		background-color: #A0A0A0;
		overflow: hidden;
	}
	#navigationBar {
		width: 80%;
		height: 50px;
		margin-left: auto;
		margin-right: auto;
		background-color: black;
		box-shadow: 0px 0px 15px 8px #FFFF99;
	}
	.menuItem {
		text-decoration: none;
		color: #E0E0E0;
	}
	li {
		display: inline;
		margin-left: 50px;
		font-size: 20px;
	}
	.menuItem:hover {
		color: white;
	}
	#firstItem {
		margin-left: 150px;
		text-decoration: none;
		color: #E0E0E0;
	}
	#firstItem:hover {
		color: white;
	}
	#logo {
		position: absolute;
		top: 20px;
		left: 180px;
	}
	@media only screen and (max-device-width: 980px) {
		#facebookLogInInPopup { 
 			display: block;
 			margin-top: 50px;
 			transform:scale(2.55);
			-webkit-transform:scale(2.55);
			transform-origin:0 0;
			-webkit-transform-origin:0 0;
 		}
		body {
			background-image: URL('../images/concert.jpg');
			background-attachment: fixed;
			background-repeat: no-repeat;
			background-size: 100% 1800px;
			margin: 0;
		} 
		li {
			display: inline;
			margin-left: 20px;
			font-size: 20px;
		}
		#logo {
			position: absolute;
			top: 20px;
			left: 100px;
		}
		#footer {
			margin-top: 750px;
			width: 100%;
			height: 50px;
			background-color: #A0A0A0;
			overflow: hidden;
		}
		#popUpRegistrationBox {
			margin: auto;
 			position: absolute;
  			top: 0;
  			left: 0;
  			bottom: 0;
  			right: 0;
			border: outset 7px #E0E0E0;
			padding: 10px;
			font-family: 'Open Sans', sans-serif;
			background: #092756;
			background: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%);
			background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
			background: -o-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
			background: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
			background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);
			filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3E1D6D', endColorstr='#092756',GradientType=1 ); 
			width:400px;
			height:630px;
		}
		#popUpRegistrationBox > p {
			color: #fff;
			text-shadow: 0 0 10px rgba(0,0,0,0.3);
			letter-spacing:1px;
			text-align:center;
			font-size: 80px;
			margin-bottom: 30px;
			margin-top: 0;
		}
		#popUpRegistrationBox input { 
			width: 100%; 
			margin-bottom: 10px; 
			background: rgba(0,0,0,0.3);
			border: none;
			outline: none;
			padding: 10px;
			font-size: 26px;
			color: #fff;
			text-shadow: 1px 1px 1px rgba(0,0,0,0.3);
			border: 1px solid rgba(0,0,0,0.3);
			border-radius: 4px;
			box-shadow: inset 0 -5px 45px rgba(100,100,100,0.2), 0 1px 1px rgba(255,255,255,0.2);
			-webkit-transition: box-shadow .5s ease;
			-moz-transition: box-shadow .5s ease;
			-o-transition: box-shadow .5s ease;
			-ms-transition: box-shadow .5s ease;
			transition: box-shadow .5s ease;
		}
		.g-recaptcha {
			transform:scale(1.21);
			-webkit-transform:scale(1.21);
			transform-origin:0 0;
			-webkit-transform-origin:0 0;
		}
		#popUpRegistrationBox .btn {
     		display: inline-block;
     		*display: inline;
     		*zoom: 1;
     		padding: 4px 10px 4px;
     		margin-top: 60px;
     		margin-bottom: 10px;
       		font-size: 52px;
        	line-height: 18px;
       	    color: #333333;
        	text-align: center;
        	text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
        	vertical-align: middle;
        	background-color: #f5f5f5;
        	background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
        	background-image: -ms-linear-gradient(top, #ffffff, #e6e6e6);
        	background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
        	background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
        	background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
        	background-image: linear-gradient(top, #ffffff, #e6e6e6);
        	background-repeat: repeat-x;
        	filter: progid:dximagetransform.microsoft.gradient(startColorstr=#ffffff, endColorstr=#e6e6e6, GradientType=0);
        	border-color: #e6e6e6 #e6e6e6 #e6e6e6;
        	border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
        	border: 1px solid #e6e6e6; -webkit-border-radius: 4px;
        	-moz-border-radius: 4px;
        	border-radius: 4px;
        	-webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        	-moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        	box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        	cursor: pointer; *margin-left: .3em;
        }
        #popUpRegistrationBox .btn:hover, .btn:active, .btn.active, .btn.disabled, .btn[disabled] {
	 		background-color: #e6e6e6;
	 	}
		#popUpRegistrationBox .btn-large {
	 		padding: 9px 14px;
	 		font-size: 60px;
	 		line-height: normal;
	 		-webkit-border-radius: 5px;
	 		-moz-border-radius: 5px;
	 		border-radius: 5px;
	 	}
		#popUpRegistrationBox .btn:hover {
	 		color: #333333;
	 		text-decoration: none;
	 		background-color: #e6e6e6;
	 		background-position: 0 -15px;
	 		-webkit-transition: background-position 0.1s linear;
	 		-moz-transition: background-position 0.1s linear;
	 		-ms-transition: background-position 0.1s linear;
	 		-o-transition: background-position 0.1s linear;
	 		transition: background-position 0.1s linear;
	 	}
		#popUpRegistrationBox .btn-primary, .btn-primary:hover { 
			text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
			color: #ffffff;
		 }
		#popUpRegistrationBox .btn-primary.active {
	 		color: rgba(255, 255, 255, 0.75); 
	 	}
		#popUpRegistrationBox .btn-primary {
			 background-color: #4a77d4; 
			 background-image: -moz-linear-gradient(top, #6eb6de, #4a77d4);
			 background-image: -ms-linear-gradient(top, #6eb6de, #4a77d4);
			 background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#6eb6de), to(#4a77d4)); 
			 background-image: -webkit-linear-gradient(top, #6eb6de, #4a77d4);
			 background-image: -o-linear-gradient(top, #6eb6de, #4a77d4);
			 background-image: linear-gradient(top, #6eb6de, #4a77d4);
			 background-repeat: repeat-x; filter: progid:dximagetransform.microsoft.gradient(startColorstr=#6eb6de, endColorstr=#4a77d4, GradientType=0);
			 border: 1px solid #3762bc;
			 text-shadow: 1px 1px 1px rgba(0,0,0,0.4);
			 box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.5);
		 }
		#popUpRegistrationBox .btn-primary:hover, .btn-primary:active, .btn-primary.active, .btn-primary.disabled, .btn-primary[disabled] {
	 		filter: none;
	 		background-color: #4a77d4;
	 	 }
		#popUpRegistrationBox .btn-block {
		 	width: 100%;
		 	display:block;
		  }
		
		
	}
</style>
<!-- Facebook Pixel Code -->
<script>
	var fbApiKey = "${initParam['apiKey']}";
	!function(f,b,e,v,n,t,s){if(f.fbq)return;n=f.fbq=function(){n.callMethod?
	n.callMethod.apply(n,arguments):n.queue.push(arguments)};if(!f._fbq)f._fbq=n;
	n.push=n;n.loaded=!0;n.version='2.0';n.queue=[];t=b.createElement(e);t.async=!0;
	t.src=v;s=b.getElementsByTagName(e)[0];s.parentNode.insertBefore(t,s)}(window,
	document,'script','https://connect.facebook.net/en_US/fbevents.js');

	fbq('init', '1051301638251747');
	fbq('track', "PageView");
	
	 // This is called with the results from from FB.getLoginStatus().
	  function statusChangeCallback(response) {
	    console.log('statusChangeCallback');
	    console.log(response);
	    // The response object is returned with a status field that lets the
	    // app know the current login status of the person.
	    // Full docs on the response object can be found in the documentation
	    // for FB.getLoginStatus().
	    if (response.status === 'connected') {
	      // Logged into your app and Facebook.
	      console.log("Logged into your app and Facebook");
	      testAPI();
	    } else if (response.status === 'not_authorized') {
	      // The person is logged into Facebook, but not your app.
	    	console.log("The person is logged into Facebook, but not your app");
	    } else {
	      // The person is not logged into Facebook, so we're not sure if
	      // they are logged into this app or not.
	      console.log("The person is not logged into Facebook, so we're not sure if they are logged into this app or not");
	    }
	  }
	 
	// This function is called when someone finishes with the Login
	  // Button.  See the onlogin handler attached to it in the sample
	  // code below.
	  function checkLoginState() {
	    FB.getLoginStatus(function(response) {
	      statusChangeCallback(response);
	    });
	  }
	
	  window.fbAsyncInit = function() {
		  console.log("fcInit");
		  FB.init({
			  //Production API id: 1113534595378637
			  //Test API id: 1113690592029704
		    appId      : fbApiKey,
		    cookie     : true,  // enable cookies to allow the server to access 
		                        // the session
		    xfbml      : true,  // parse social plugins on this page
		    version    : 'v2.8' // use graph api version 2.8
		  });

		  // Now that we've initialized the JavaScript SDK, we call 
		  // FB.getLoginStatus().  This function gets the state of the
		  // person visiting this page and can return one of three states to
		  // the callback you provide.  They can be:
		  //
		  // 1. Logged into your app ('connected')
		  // 2. Logged into Facebook, but not your app ('not_authorized')
		  // 3. Not logged into Facebook and can't tell if they are logged into
		  //    your app or not.
		  //
		  // These three cases are handled in the callback function.

		  /*FB.getLoginStatus(function(response) {
		    statusChangeCallback(response);
		  }); */
		/**/
		  };
		  
		// Load the SDK asynchronously
		  (function(d, s, id) {
			console.log("Loading sdk");
		    var js, fjs = d.getElementsByTagName(s)[0];
		    if (d.getElementById(id)) return;
		    js = d.createElement(s); js.id = id;
		    js.src = "//connect.facebook.net/en_US/sdk.js";
		    fjs.parentNode.insertBefore(js, fjs);
		  }(document, 'script', 'facebook-jssdk'));
		
		  function testAPI() {
			    console.log('Welcome!  Fetching your information.... ');
			    FB.api('/me', {fields: 'email'}, function(response) {
			      	if (!response.email) response.email = (response.id).toString();
			      	$.ajax({
					type:'GET',
					data: {email: response.email},
					url: 'getUserLoginDetails.do',
					success: function(result) {
							if (result != null) {
								var loginForm = document.getElementsByTagName("form")[0];
								loginForm.j_username.value = response.email;
								loginForm.j_password.value = result.password;
								loginForm.submit()
							}
							else {
								window.location.href = "/facebookSignUp.jsp";
							}
						}
					}); 
			      	//document.getElementById('status').innerHTML =
			        //'Thanks for logging in, ' + JSON.stringify(response.albums.data[0]) + '!<br><h1>'+response.email+' '+response.id+'</h1>';
			   }); 
			  }
</script>
<noscript>
	<img height="1" width="1" style="display:none" src="https://www.facebook.com/tr?id=1051301638251747&ev=PageView&noscript=1"/>
</noscript>
<!-- End Facebook Pixel Code -->

<script>


  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){

  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),

  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)

  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');




  ga('create', 'UA-79739988-1', 'auto');

  ga('send', 'pageview');




</script>
</head>
<body>
<div id="navigationBarHolder">
	<ol id="navigationBar">
		<li><a id="firstItem">Home</a></li>
		<li><a class="menuItem" href="/events.jsp">Events</a></li>
		<li><a class="menuItem" href="/Contact-Us.jsp">Contact Us</a></li>
		<li><a class="menuItem" href="/Find-Members.jsp">Find Members</a></li>
	</ol>
	<img src="../images/logo.png" alt="logo" width="150" id="logo" />
</div>
<div id="aboutHolder">
<div id="about">
	<div id="header">
		<h1 class="message">Find Your Gig Buddies</h1>
		<h2>Welcome to gigbuddy.org</h2>
	</div>
	<div id="article">
		<h2>What is gigbuddy about?</h2>
		GigBuddy is all about finding friends who you can go to events such as gigs, concerts or parties with.
		Find your gig and post a buddy request.
		Find events or submit events so that it shows up in search result. <br /><br />
		Tired of going to your favorite band`s show on your own? <br />
		Your friends have different taste in music? <br />
		GigBuddy is for the rescue.
		Sign up simply with your email address, edit your profile, upload images, post buddy requests or respond to requests.
		Best of all, none of these costs you a penny.
	</div>
</div>
</div>
<div id="sideBar">
	<div class="login">
		<p><b>Login</b></p>
   		<form method="post" action="members/j_security_check">
    		<input type="text" name="j_username" placeholder="Email" required="required" />
        	<input type="password" name="j_password" placeholder="Password" required="required" />
        	<button type="submit" class="btn btn-primary btn-block btn-large">Let me in.</button>
    	</form>
    	<fb:login-button scope="public_profile,email,user_likes,user_photos" onlogin="checkLoginState();" size="large" id="facebookLogIn" title="Click here if you want to login with your Facebook account or you will be registered with your Facebook account">Login/Sign Up</fb:login-button>
    	<a id="notRegistered" href="#" onclick="openPopUp()"><br />Have not signed up yet? Click here to sign up!</a>
	</div>
	<div id="events">
		<h2>Events</h2>
		<hr />
		<p>Google adsense</p>
		<hr />
		<p>Some event here</p>
		<button>Add event</button>
	</div>
</div>
<div id="popUpRegistrationBoxContainer">
	<img class="closeButton" src="../images/closeButton.png" alt="closeButton" title="close window" onclick="closePopUp(this)" />
	<div id="popUpRegistrationBox">
		<p><b>Register</b></p>
   		<form method="post" action="register.do" onsubmit="return validateRegistrationForm()">
    		<input type="email" name="u" placeholder="Email Address" required="required" />
        	<input type="password" name="p" placeholder="Password" required="required" />
        	<div class="g-recaptcha" data-sitekey="6Lc3hiITAAAAADlRS3QPGS_DD780d4EUH5o4ygWm"></div>
        	<fb:login-button scope="public_profile,email,user_likes,user_photos" onlogin="checkLoginState();" size="xlarge" id="facebookLogInInPopup" title="Click here if you want to login with your Facebook account or you will be registered with your Facebook account">Sign Up</fb:login-button>
        	<button type="submit" class="btn btn-primary btn-block btn-large">Submit</button>
    	</form>
    	
	</div>
</div>
<!--<c:if test="${not empty cookie.messageCookie.value}"> -->
	<div id="messagePopUpContainer">
		<img class="closeButton" src="/images/closeButton.png" alt="closeButton" title="close window" onclick="closePopUp(this)" />
		<h1 class="message">${empty cookie.messageCookie.value}</h1>
	</div>
<!--</c:if> -->
<div id="waitPopUpContainer">
	<div class="ball"></div>
	<div class="ball1"></div>
</div>
<div id="footer">
	<div id="smallFooter">
		<p class="left">©2016 By Tamas Ledecki</p>
		<p class="right">
			<a href="https://twitter.com/gigbuddy_org" target="_blank"><img src="../images/twitterIcon.png" alt="twitterIcon" class="socialNetworkIcons" /></a>
			<a href="https://www.facebook.com/gigbuddy.org" target="_blank"><img src="../images/facebookIcon.jpe" alt="facebookIcon" class="socialNetworkIcons" /></a>
			<a href="https://plus.google.com/117150561612370499374/about" target="_blank"><img src="../images/googlePlusIcon.png" alt="googlePlusIcon" class="socialNetworkIcons" /></a>
		</p>
	</div>
</div>

<script type="text/javascript">
	setRegistrationPopup();
	setMessagePopup();
	logInIfJustSignedUp(getCookie("username"));
	
</script>
</body>
</html>