<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="Find music gigs, parties, concerts and gig friends who would like to go to a concert with you. GigBuddy will help you find Upcoming Concerts and locations." name="description" />
<title>GigBuddy - Get to know more about Events and Concert friends</title>
<link rel="stylesheet" type="text/css" href="cssFiles/footer.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<script src="scripts/jquery-1.12.1.min.js" type="text/javascript"></script>
<script src="scripts/events.js" type="text/javascript"></script>
<style type="text/css">
	#searchBoxContainer {
		width: 950px;
		height: 81px;
		margin-left: auto;
		margin-right: auto;
		margin-top: 150px;
		padding: 0;
	}
	#searchBox {
		float: left;
		width: 680px;
		height: 60px;
		background: -moz-linear-gradient(90deg, rgba(89,89,89,1) 0%, rgba(89,89,89,1) 25%, rgba(248,248,248,1) 48%, rgba(255,255,255,1) 49%, rgba(64,64,64,1) 75%, rgba(64,64,64,1) 99%); /* ff3.6+ */background: -webkit-gradient(linear, left top, left bottom, color-stop(1%, rgba(64,64,64,1)), color-stop(25%, rgba(64,64,64,1)), color-stop(51%, rgba(255,255,255,1)), color-stop(52%, rgba(248,248,248,1)), color-stop(75%, rgba(89,89,89,1)), color-stop(100%, rgba(89,89,89,1))); /* safari4+,chrome */
   		background: -webkit-linear-gradient(90deg, rgba(89,89,89,1) 0%, rgba(89,89,89,1) 25%, rgba(248,248,248,1) 48%, rgba(255,255,255,1) 49%, rgba(64,64,64,1) 75%, rgba(64,64,64,1) 99%); /* safari5.1+,chrome10+ */
    	background: -o-linear-gradient(90deg, rgba(89,89,89,1) 0%, rgba(89,89,89,1) 25%, rgba(248,248,248,1) 48%, rgba(255,255,255,1) 49%, rgba(64,64,64,1) 75%, rgba(64,64,64,1) 99%); /* opera 11.10+ */
    	background: -ms-linear-gradient(90deg, rgba(89,89,89,1) 0%, rgba(89,89,89,1) 25%, rgba(248,248,248,1) 48%, rgba(255,255,255,1) 49%, rgba(64,64,64,1) 75%, rgba(64,64,64,1) 99%); /* ie10+ */
    	background: linear-gradient(0deg, rgba(89,89,89,1) 0%, rgba(89,89,89,1) 25%, rgba(248,248,248,1) 48%, rgba(255,255,255,1) 49%, rgba(64,64,64,1) 75%, rgba(64,64,64,1) 99%); /* w3c */
    	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#404040', endColorstr='#595959',GradientType=0 ); /* ie6-9 */
    	border-top-left-radius:25px;
		border-bottom-left-radius:25px;
    	padding-top: 20px;
    	margin: 0;
	}
	.searchBoxElement {
		display: inline;
		margin-left: 20px;
		font-size: 35px;
		color: #A4AF07;
	}
	input {
		border-radius: 25px;
		height: 30px;
		padding-top: 5px;
		font-size: 30px;
		width: 150px;
	}
	button {
		float: right;
		background-color: #FF8000;
		color: #C0C0C0;
		border-top-right-radius:25px;
		border-bottom-right-radius:25px;
		font-size: 65px;
		margin: 0; 
	}
	button:hover {
		color: white;
		cursor: pointer;
	}
	#list8 {
		width: 942px;
		margin-left: auto;
		margin-right: auto;
	 }
	#list8 ul {
		list-style:none;
		margin-top: 0;
		margin-left: auto;
		margin-right: auto;
		width: 335px;
		padding:0;
		height: 400px;
		overflow: auto;
	  }
	#list8 ul li { 
		font-family:Georgia,serif,Times;
		font-size:18px;
		margin: 0;
	}
	#list8 ul li a { 
		display:block; width:300px; height:28px; background-color:#333; border-left:5px solid #222; border-right:5px solid #222; padding-left:10px;
  		text-decoration:none; color:#bfe1f1; 
  	}
	#list8 ul li a:hover { 
		 -moz-transform:rotate(-5deg); -moz-box-shadow:10px 10px 20px #000000;
  		-webkit-transform:rotate(-5deg); -webkit-box-shadow:10px 10px 20px #000000;
  		transform:rotate(-3deg); box-shadow:10px 10px 20px #000000;
  		cursor: pointer;
  	}
  	.table31 {
  		display:none;
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
	.white {
		color: white;
		background-color: black;
	} 
	.buddyRequest:hover, .moreThanOneReq:hover, .apply:hover {
		cursor: pointer;
		background: -moz-linear-gradient(left,  rgba(0,0,0,0.65) 22%, rgba(0,0,0,0.35) 39%, rgba(0,0,0,0) 59%, rgba(0,0,0,0) 70%); /* FF3.6-15 */
		background: -webkit-linear-gradient(left,  rgba(0,0,0,0.65) 22%,rgba(0,0,0,0.35) 39%,rgba(0,0,0,0) 59%,rgba(0,0,0,0) 70%); /* Chrome10-25,Safari5.1-6 */
		background: linear-gradient(to right,  rgba(0,0,0,0.65) 22%,rgba(0,0,0,0.35) 39%,rgba(0,0,0,0) 59%,rgba(0,0,0,0) 70%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
		filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#a6000000', endColorstr='#00000000',GradientType=1 ); /* IE6-9 */
	}
	.buddyRequest form input[type=text] {
		display:none;
	}
	.buddyRequest form input[type=submit] {
		width: auto;
		background:none!important;
     	border:none; 
     	padding:0!important;
     	font: inherit; 
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
		top: 25px;
		left: 30px;	
		width: 95%;
		height: 250px;
		background-color: rgba(182, 204, 250, 0.9);
		padding-top: 170px;
		padding-bottom: 170px;
		z-index: 200;
		text-align: center;
	}
	.profilePic {
		width: 100px;
	}
#applyForm {
	display:none;
	position: relative;
	bottom: 400px;
	background-image: URL('images/contactFormBackground.png');
	width: 500px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 100px;
	font-size: 20px;
	color: #E0E0E0;
	overflow: hidden;
	border-top-left-radius: 20px;
	border-top-right-radius: 20px;
	text-align: left;
}
#applyForm table tr:nth-child(2) td, #applyForm table tr:nth-child(4) td{
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
#applyForm > table {
	margin-left: 20px;
	margin-top: 20px;
}
#submitButton {
	border-radius: 0;
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
#applyForm table tr td input[type=radio] {
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
		display: none;
	} 
	@media only screen and (max-device-width: 900px) {
		#searchBoxContainer {
			height: 40px;
			margin-top: 100px;
			width: 770px;
		}
		#searchBoxContainer button {
			font-size: 60px;
		}
		#searchBox {
			width: 73%;
			height: 64px;
    		padding-top: 15px;
    		padding-left: 0px;
		}
		.searchBoxElement {
			margin-left: 9px;
			font-size: 34px;
		}
		input {
			height: 40px;
			padding-top: 3px;
			font-size: 30px;
			width: 140px;
		}
		#list8 {
			width: 942px;
			margin-left: auto;
			margin-right: auto;
	 	}
		#list8 ul {
			list-style:none;
			margin-top: 0;
			margin-left: auto;
			margin-right: auto;
			width: 670px;
			padding:0;
			height: 800px;
			overflow: auto;
	  	}
		#list8 ul li { 
			font-family:Georgia,serif,Times;
			font-size:36px;
			margin: 0;
		}
		#list8 ul li a { 
			display:block;
			width:600px;
			height:56px;
			background-color:#333;
			border-left:5px solid #222;
			border-right:5px solid #222;
			padding-left:10px;
  			text-decoration:none;
  			color:#bfe1f1; 
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
	}
</style>
<link rel="stylesheet" type="text/css" href="cssFiles/navigationBar.css" />

</head>
<body>
	<jsp:include page="htmlFiles/navigationBar.html" />
	<div id="searchBoxContainer">
		<ol id="searchBox">
			<li class="searchBoxElement">Location</li>
			<li class="searchBoxElement"><input type="text" name="location" onkeydown="showLocationList()" onfocus="deleteList()" /></li>
			<li class="searchBoxElement">Event</li>
			<li class="searchBoxElement"><input type="text" name="artist" onfocus="deleteList()" onkeydown="showEventList(this)" /></li>
		</ol>
		<button onclick="showResult()">Search</button>
	</div>
	<div id="list8">
		<ul>
			<li></li>
		</ul>
	</div>
	<div class="table31">
		<table>
    		<tbody>
    			<tr>
    				<td></td>
    			</tr>
  			</tbody>
  		</table>														
	</div>
	<div id="slideShowPopUpContainer">
		<img class="closeButton" src="images/closeButton.png" alt="closeButton" title="close window" onclick="closePopUp(this)" />
		<div class="table31">
			<table>
    			<tbody>
    				<tr>
    					<td></td>
    				</tr>
  				</tbody>
  			</table>														
		</div>
		<form action="members/sendApplication.do" method="post" id="applyForm" onsubmit="return formValidator()">
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
				<td colspan="2" id="personalisedLabel">
					
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea rows="5" cols="50" name="message" class="textBox" onkeyup="characterCounter(this)"></textarea>
				</td>
			</tr>
		</table>
		<p id="characterCount">Character Count: 0</p>
		<input type="text" name="reqId" class="hidden" />
		<input type="submit" value="Send It!" id="submitButton" />
	</form>
	</div>
	<jsp:include page="htmlFiles/footer.jsp" >
		<jsp:param value="images/twitterIcon.png" name="twitterURL"/>
		<jsp:param value="images/facebookIcon.jpe" name="facebookURL"/>
	</jsp:include>

</body>
</html>