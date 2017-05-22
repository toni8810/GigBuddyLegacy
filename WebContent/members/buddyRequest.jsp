<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Post Buddy Request</title>
<link rel="stylesheet" type="text/css" href="cssFiles/header.css" />
<link rel="stylesheet" type="text/css" href="../cssFiles/footer.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<style type="text/css">
#welcomeMessage {
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
#contactForm {
	background-image: URL('../images/contactFormBackground.png');
	width: 500px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 100px;
	font-size: 20px;
	color: #E0E0E0;
	overflow: hidden;
	border-top-left-radius: 20px;
	border-top-right-radius: 20px;
}
#contactForm table tr:nth-child(2) td, #contactForm table tr:nth-child(4) td{
	padding-bottom: 20px;
}
.textBox {
	border-radius: 5px;
	font-size: 15px;
	padding: 10px;
  	border: solid 1px #fff;
  	box-shadow: inset 1px 1px 2px 0 #707070;
  	transition: box-shadow 0.3s;
}
textarea {
	width: 400px;
}
table {
	margin-left: 20px;
	margin-top: 20px;
}
#submitButton {
	margin-top: 20px;
	background-color: #1DA2CF;
	font-size: 20px;
	width: 100%;
	height: 40px;
	color: #E0E0E0;
}
#submitButton:hover {
	color: white;
	cursor: pointer;
}
<!--Radio buttons starts here -->
#contactForm table tr td input[type=radio] {
	display: none;
}
input[type=radio].css-checkbox {
	position:absolute;
	z-index:-1000;
	left:-1000px;
	overflow: hidden;
	clip: rect(0 0 0 0);
	height:1px;
	width:1px;
	margin:-1px;
	padding:0;
	border:0;
}
input[type=radio].css-checkbox + label.css-label {
	padding-left:33px;
	height:28px; 
	display:inline-block;
	line-height:28px;
	background-repeat:no-repeat;
	background-position: 0 0;
	font-size:20px;
	vertical-align:middle;
	cursor:pointer;
}
input[type=radio].css-checkbox:checked + label.css-label {
	background-position: 0 -28px;
}
label.css-label {
	background-image:url(http://csscheckbox.com/checkboxes/u/csscheckbox_e0b730d6530ee756808b298cc2c8ed12.png);
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}
<!-- Dropdown starts here -->
.container {
  	margin: 80px auto;
  	width: 400px;
}

.container > .dropdown {
  	margin: 0 20px;
  	vertical-align: top;
}

.dropdown {
  	display: none;
  	position: relative;
  	right: 20px;
  	overflow: hidden;
 	height: 28px;
  	width: 100px;
  	background: #f2f2f2;
  	border: 1px solid;
  	border-color: white #f7f7f7 whitesmoke;
  	border-radius: 3px;
  	background-image: -webkit-linear-gradient(top, transparent, rgba(0, 0, 0, 0.06));
  	background-image: -moz-linear-gradient(top, transparent, rgba(0, 0, 0, 0.06));
  	background-image: -o-linear-gradient(top, transparent, rgba(0, 0, 0, 0.06));
  	background-image: linear-gradient(to bottom, transparent, rgba(0, 0, 0, 0.06));
  	-webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.08);
  	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.08);
	}

.dropdown:before, .dropdown:after {
  	content: '';
  	position: absolute;
  	z-index: 2;
  	top: 9px;
  	right: 10px;
  	width: 0;
  	height: 0;
  	border: 4px dashed;
  	border-color: #888888 transparent;
  	pointer-events: none;
}

.dropdown:before {
  	border-bottom-style: solid;
  	border-top: none;
}

.dropdown:after {
  	margin-top: 7px;
  	border-top-style: solid;
  	border-bottom: none;
}
.dropdown-select {
  	position: relative;
  	width: 130%;
  	margin: 0;
  	padding: 6px 8px 6px 10px;
  	height: 28px;
  	line-height: 14px;
  	font-size: 13px;
  	color: #62717a;
  	text-shadow: 0 1px white;
  	background: #f2f2f2; /* Fallback for IE 8 */
  	background: rgba(0, 0, 0, 0) !important; /* "transparent" doesn't work with Opera */
  	border: 0;
  	border-radius: 0;
  	-webkit-appearance: none;
}

	.dropdown-select:focus {
  		z-index: 3;
  		width: 100%;
  		color: #394349;
  		outline: 2px solid #49aff2;
  		outline: 2px solid -webkit-focus-ring-color;
  		outline-offset: -2px;
	}

	.dropdown-select > option {
  		margin: 3px;
  		padding: 6px 8px;
  		text-shadow: none;
  		background: #f2f2f2;
  		border-radius: 3px;
  		cursor: pointer;
	}

	/* Fix for IE 8 putting the arrows behind the select element. */

	.lt-ie9 .dropdown {
  		z-index: 1;
	}

	.lt-ie9 .dropdown-select {
  		z-index: -1;
	}

	.lt-ie9 .dropdown-select:focus {
  		z-index: 3;
	}

	/* Dirty fix for Firefox adding padding where it shouldn't. */

	@-moz-document url-prefix() {
  		.dropdown-select {
    	padding-left: 6px;
  		}
	}

	.dropdown-dark {
  		background: #444;
  		border-color: #111111 #0a0a0a black;
  		background-image: -webkit-linear-gradient(top, transparent, rgba(0, 0, 0, 0.4));
  		background-image: -moz-linear-gradient(top, transparent, rgba(0, 0, 0, 0.4));
  		background-image: -o-linear-gradient(top, transparent, rgba(0, 0, 0, 0.4));
  		background-image: linear-gradient(to bottom, transparent, rgba(0, 0, 0, 0.4));
  		-webkit-box-shadow: inset 0 1px rgba(255, 255, 255, 0.1), 0 1px 1px rgba(0, 0, 0, 0.2);
  		box-shadow: inset 0 1px rgba(255, 255, 255, 0.1), 0 1px 1px rgba(0, 0, 0, 0.2);
	}

	.dropdown-dark:before {
  		border-bottom-color: #aaa;
	}

	.dropdown-dark:after {
  		border-top-color: #aaa;
	}

	.dropdown-dark .dropdown-select {
  		color: #aaa;
  		text-shadow: 0 1px black;
  		background: #444;  /* Fallback for IE 8 */
	}

	.dropdown-dark .dropdown-select:focus {
  		color: #ccc;
	}

	.dropdown-dark .dropdown-select > option {
  		background: #444;
  		text-shadow: 0 1px rgba(0, 0, 0, 0.4);
	}
	#characterCount {
		margin-top: 0;
		margin-left: 300px;
	}
	.hidden {
		display:none;
	}
</style>
<script type="text/javascript">
	function showNumOfPeople() {
		document.getElementsByClassName("dropdown")[0].style.display = "inline-block";
		document.getElementsByTagName("td")[1].style.display = "block";
	}
	function hideNumOfPeople() {
		document.getElementsByClassName("dropdown")[0].style.display = "none";
		document.getElementsByTagName("td")[1].style.display = "none";
	}
	function lshowNumOfPeople() {
		document.getElementsByClassName("dropdown")[1].style.display = "inline-block";
		document.getElementsByTagName("td")[5].style.display = "block";
	}
	function lhideNumOfPeople() {
		document.getElementsByClassName("dropdown")[1].style.display = "none";
		document.getElementsByTagName("td")[5].style.display = "none";
	}
	function characterCounter(textArea) {
		var text = textArea.value;
		document.getElementById("characterCount").innerHTML = "Character Count: "+text.length;
	}
	function formValidator() {
		//store the request in database
		var radios = document.getElementsByClassName("css-checkbox");
		var radioChecked = false;
		var text = document.getElementsByTagName("textarea")[0].value;
		
		for (var i=0; i<2; i++) {
			if (radios[i].checked == true) radioChecked = true;
		}
		if (radioChecked == false) {
			alert("You have not clicked on any radio button");
			return false;
		}
		radioChecked = false;
		for (var i=2; i<4; i++) {
			if (radios[i].checked == true) radioChecked = true;
		}
		if (radioChecked == false) {
			alert("You have not clicked on any radio button");
			return false;
		}
		if (text.length > 240) {
			alert("You can type a maximum of 240 characters");
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
<h1 id="welcomeMessage">${param.title} <br /> ${param.location} <br /> ${param.date} <br /> ${param.time}</h1>
<form action="saveBuddyRequest.do" method="post" id="contactForm" onsubmit="return formValidator()">
		<table>
			<tr>
				<td>*You are a(n)</td>
				<td style="display:none">*Number of people</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="inOrGroup" value="Individual" id="indiRadioB" class="css-checkbox" onclick="hideNumOfPeople()" />
					<label for="indiRadioB" class="css-label">Individual</label>
					<input type="radio" name="inOrGroup" value="Group" id="groupRadioB" class="css-checkbox" onclick="showNumOfPeople()" />
					<label for="groupRadioB" class="css-label">Group</label>
				</td>
				<td>
					<div class="container">
  						<div class="dropdown dropdown-dark">
    						<select name="numOfPeople" class="dropdown-select">
      							<option value="2">2</option>
      							<option value="3">3</option>
      							<option value="4">4</option>
      							<option value="4+">4+</option>
    						</select>
  						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>*You are looking for a(n)</td>
				<td style="display:none">*Number of people</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="linOrGroup" value="Individual" id="lindiRadioB" class="css-checkbox" onclick="lhideNumOfPeople()" />
					<label for="lindiRadioB" class="css-label">Individual</label>
					<input type="radio" name="linOrGroup" value="Group" id="lgroupRadioB" class="css-checkbox" onclick="lshowNumOfPeople()" />
					<label for="lgroupRadioB" class="css-label">Group</label>
				</td>
				<td>
					<div class="container">
  						<div class="dropdown dropdown-dark">
    						<select name="lNumOfPeople" class="dropdown-select">
      							<option value="2">2</option>
      							<option value="3">3</option>
      							<option value="3+">3+</option>
    						</select>
  						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					A Few words about your request:
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea rows="5" cols="50" name="ad" class="textBox" onkeyup="characterCounter(this)"></textarea>
				</td>
			</tr>
		</table>
		<p id="characterCount">Character Count: 0</p>
		<input type="text" name="eventTitle" value="${param.title}" class="hidden" />
		<input type="text" name="eventLocation" value="${param.location}" class="hidden" />
		<input type="text" name="eventDate" value="${param.date}" class="hidden" />
		<input type="text" name="eventTime" value="${param.time}" class="hidden" />
		<input type="submit" value="Send It!" id="submitButton" />
	</form>
<jsp:include page="../htmlFiles/footer.jsp" >
	<jsp:param value="../images/twitterIcon.png" name="twitterURL"/>
	<jsp:param value="../images/facebookIcon.jpe" name="facebookURL"/>
</jsp:include>
</body>
</html>