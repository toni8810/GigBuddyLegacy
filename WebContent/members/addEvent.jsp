<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add New Event</title>
<link rel="stylesheet" type="text/css" href="cssFiles/header.css" />
<link rel="stylesheet" href="cssFiles/calendar.css" />
<link rel="stylesheet" type="text/css" href="../cssFiles/footer.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<script type="text/javascript" src="../scripts/jquery-1.12.1.min.js"></script>
<script type="text/javascript" src="../scripts/jquery-ui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#datepicker').datepicker({
				inline: false,
				showOtherMonths: true,
				dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
				minDate: 0,
				dateFormat: 'dd/mm/yy',
				showAnim: 'fadeIn',
				duration: 'slow'
			});
		});
	</script>
<style type="text/css">
	#eventForm {
		background-image: URL('../images/contactFormBackground.png');
		width: 550px;
		margin-left: auto;
		margin-right: auto;
		margin-top: 100px;
		margin-bottom: 550px;
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
		width: 230px;
	}
	textarea {
		width: 440px;
	}
	table {
		margin-left: 20px;
		margin-top: 20px;
	}
	#submitButton {
		margin-left: 170px;
		margin-top: 30px;
		margin-bottom: 20px;
		background-color: #1DA2CF;
		border-radius: 5px;
		font-size: 20px;
		width: 200px;
		height: 40px;
		color: #E0E0E0;
	}
	#submitButton:hover {
		color: white;
		cursor: pointer;
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
  		display: inline-block;
  		position: relative;
  		bottom: 20px;
  		right: 20px;
  		overflow: hidden;
  		height: 38px;
  		width: 200px;
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
  		height: 38px;
  		line-height: 14px;
  		font-size: 18px;
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
</style>
<script type="text/javascript">
	function validateForm() {
		var date = document.getElementsByName("date")[0].value;
		var title = document.getElementsByName("title")[0].value;
		var locationString = document.getElementsByName("location")[0].value;
		
		if (title.search("[^\x00-\x7F]+") >= 0) {
			alert("Whooo!! Please use only English characters in your title!");
			return false;
		}
		if (locationString.search("[^\x00-\x7F]+") >= 0) {
			alert("Whooo!! Please use only English characters in your location");
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
	<form action="storeEvent.do" onsubmit="return validateForm()" id="eventForm">
		<table>
			<tr>
				<td>*Title</td>
				<td>*Location</td>
			</tr>
			<tr>
				<td><input type="text" name="title" class="textBox" required="required" /></td>
				<td><input type="text" name="location" class="textBox" required="required" /></td>
			</tr>
			<tr>
				<td>*Date</td>
				<td>*Time</td>
			</tr>
			<tr>
				<td>
					 <input id="datepicker" type="text" name="date" class="textBox" required="required", readonly>
					 </input>
				</td>
				<td>
					<div class="container">
  						<div class="dropdown dropdown-dark">
    						<select name="two" class="dropdown-select">
      							<option value="1:00pm">1:00pm</option>
      							<option value="2:00pm">2:00pm</option>
      							<option value="3:00pm">3:00pm</option>
      							<option value="4:00pm">4:00pm</option>
      							<option value="5:00pm">5:00pm</option>
      							<option value="6:00pm" selected="selected" >6:00pm</option>
      							<option value="7:00pm">7:00pm</option>
      							<option value="8:00pm">8:00pm</option>
      							<option value="9:00pm">9:00pm</option>
      							<option value="10:00pm">10:00pm</option>
      							<option value="11:00pm">11:00pm</option>
      							<option value="12:00pm">12:00pm</option>
    						</select>
  						</div>
					</div>
  				</td>
			</tr>
		</table> 
		<input type="submit" value="Submit Event" id="submitButton" />
	</form>
	<jsp:include page="../htmlFiles/footer.jsp" >
		<jsp:param value="../images/twitterIcon.png" name="twitterURL"/>
		<jsp:param value="../images/facebookIcon.jpe" name="facebookURL"/>
	</jsp:include>
</body>
</html>