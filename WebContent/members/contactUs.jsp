<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Contact Us</title>
<link rel="stylesheet" type="text/css" href="cssFiles/header.css" />
<link rel="stylesheet" type="text/css" href="../cssFiles/footer.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<style type="text/css">
	#contactForm {
		background-image: URL('../images/contactFormBackground.png');
		width: 510px;
		margin-left: auto;
		margin-right: auto;
		margin-top: 100px;
		margin-bottom: 630px;
		font-size: 20px;
		color: #E0E0E0;
		overflow: hidden;
	}
	.textBox {
		border-radius: 5px;
		margin-bottom: 40px;
		font-size: 15px;
		padding: 10px;
  		border: solid 1px #fff;
  		box-shadow: inset 1px 1px 2px 0 #707070;
  		transition: box-shadow 0.3s;
	}
	table tr + tr td input {
		width: 200px;
	}
	textarea {
		width: 440px;
	}
	table {
		margin-left: 20px;
		margin-top: 20px;
	}
	#submitButton {
		margin-left: 20px;
		margin-bottom: 20px;
		background-color: #1DA2CF;
		border-radius: 5px;
		font-size: 20px;
		width: 140px;
		height: 40px;
		color: #E0E0E0;
	}
	#submitButton:hover {
		color: white;
		cursor: pointer;
	}
</style>
<script type="text/javascript">
	function contactFormValidator() {
		var name = document.getElementsByName("name")[0].value;
		var email = document.getElementsByName("email")[0].value;
		var message = document.getElementsByName("message")[0].value;
		if (name.length > 60) {
			alert("Whoops!! Your name is too long!!");
			return false;
		}
		if ((email.length > 30) || (email.indexOf("@") < 0)) {
			alert("Whoops!! That is an invalid email address");
			return false;
		}
		if (message.length > 240) {
			alert("Whoops!! The maximum number of characters in your message is 240");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<jsp:include page="htmlFiles/header.jsp">
		<jsp:param value="${pageContext.request.userPrincipal.name}" name="username" />
	</jsp:include>
	<form action="/sendMessage.do" method="get" id="contactForm" onsubmit="return contactFormValidator()">
		<table>
			<tr>
				<td>*Name:</td>
				<td>*Email:</td>
			</tr>
			<tr>
				<td><input type="text" name="name" class="textBox" /></td>
				<td><input type="text" name="email" class="textBox" /></td>
			</tr>
			<tr>
				<td colspan="2">Message:</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea rows="4" cols="50" name="message" class="textBox"></textarea>
				</td>
			</tr>
		</table>
		<input type="submit" value="Send It!" id="submitButton" />
	</form>
	<jsp:include page="../htmlFiles/footer.jsp" >
		<jsp:param value="../images/twitterIcon.png" name="twitterURL"/>
		<jsp:param value="../images/facebookIcon.jpe" name="facebookURL"/>
	</jsp:include>
</body>
</html>