<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="At GigBuddy, you can meet new friends to go to concerts with and find upcoming events. Have a query? Give us a shout & we will try to respond asap!" name="description" />
<title>Contact GigBuddy</title>
<link rel="stylesheet" type="text/css" href="cssFiles/navigationBar.css" />
<link rel="stylesheet" type="text/css" href="cssFiles/footer.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<script src="scripts/Contact-Us.js" type="text/javascript"></script>
<style type="text/css">
	form {
		background-image: URL('images/contactFormBackground.png');
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

</head>
<body>
	<jsp:include page="htmlFiles/navigationBar.html" />
	<form action="sendMessage.do" method="get" onsubmit="return contactFormValidator()">
		<table>
			<tr>
				<td>*Name:</td>
				<td>*Email:</td>
			</tr>
			<tr>
				<td><input type="text" name="name" class="textBox" required="required" /></td>
				<td><input type="text" name="email" class="textBox" required="required" /></td>
			</tr>
			<tr>
				<td colspan="2">Message:</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea rows="4" cols="50" name="message" class="textBox" required="required"></textarea>
				</td>
			</tr>
		</table>
		<input type="submit" value="Send It!" id="submitButton" />
	</form>
	<jsp:include page="htmlFiles/footer.jsp" >
		<jsp:param value="images/twitterIcon.png" name="twitterURL"/>
		<jsp:param value="images/facebookIcon.jpe" name="facebookURL"/>
	</jsp:include>
</body>
</html>