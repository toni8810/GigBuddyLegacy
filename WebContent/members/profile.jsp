<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="images" uri="CustomTagLibrary"%>
<%@ taglib prefix="info" uri="CustomTagLibrary"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Your Profile</title>
<link rel="stylesheet" type="text/css" href="../cssFiles/footer.css" />
<link rel="stylesheet" type="text/css" href="cssFiles/header.css" />
<link rel="stylesheet" type="text/css" href="cssFiles/bandSearchBox.css" />
<link rel="stylesheet" type="text/css" href="cssFiles/popupBandList.css" />
<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css' />
<link rel="shortcut icon" href="/favicon.ico" />
<script src="../scripts/modernizr.js"></script>
<script src="../scripts/jquery-1.12.1.min.js"></script>
<script type="text/javascript">
	var allImagesGlobal = [];
	function uploadPicture() {
		$('#smallPics').css('display','block');
		$('#uploadImageForm').css('display','block');
		$('#mainImage').css('display','none');
		$('.oneInputBox:eq(0)').css('display','none');
		$('.oneInputBox:eq(1)').css('display','none');
		$('.oneInputBox:eq(2)').css('display','none');
		$('.oneInputBox:eq(3)').css('display','none');
		$('#aboutBox').css('display','none');
		$('#music').css('display','none');
		allImagesGlobal = document.getElementsByClassName("profileImage");
	}
	function showNameBox() {
		$('#smallPics').css('display','none');
		$('#uploadImageForm').css('display','none');
		$('#mainImage').css('display','none');
		$('.oneInputBox:eq(0)').css('display','block');
		$('.oneInputBox:eq(1)').css('display','none');
		$('.oneInputBox:eq(2)').css('display','none');
		$('.oneInputBox:eq(3)').css('display','none');
		$('#aboutBox').css('display','none');
		$('#music').css('display','none');
		$.ajax({
			type:'GET',
			data: {},
			url: 'getName.do',
			success: function(result) {
				$('.textBox:eq(0)').val(result);
			}
		});
	}
	function showAgeBox() {
		$('#smallPics').css('display','none');
		$('#uploadImageForm').css('display','none');
		$('#mainImage').css('display','none');
		$('.oneInputBox:eq(0)').css('display','none');
		$('.oneInputBox:eq(1)').css('display','block');
		$('.oneInputBox:eq(2)').css('display','none');
		$('.oneInputBox:eq(3)').css('display','none');
		$('#aboutBox').css('display','none');
		$('#music').css('display','none');
		$.ajax({
			type:'GET',
			data: {},
			url: 'getAge.do',
			success: function(result) {
				$('.textBox:eq(1)').val(result);
			}
		});
	}
	function showLocationBox() {
		$('#smallPics').css('display','none');
		$('#uploadImageForm').css('display','none');
		$('#mainImage').css('display','none');
		$('.oneInputBox:eq(0)').css('display','none');
		$('.oneInputBox:eq(1)').css('display','none');
		$('.oneInputBox:eq(2)').css('display','block');
		$('.oneInputBox:eq(3)').css('display','none');
		$('#aboutBox').css('display','none');
		$('#music').css('display','none');
		$.ajax({
			type:'GET',
			data: {},
			url: 'getLocation.do',
			success: function(result) {
				$('.textBox:eq(2)').val(result);
			}
		});
	}
	function showAboutBox() {
		$('#smallPics').css('display','none');
		$('#uploadImageForm').css('display','none');
		$('#mainImage').css('display','none');
		$('.oneInputBox:eq(0)').css('display','none');
		$('.oneInputBox:eq(1)').css('display','none');
		$('.oneInputBox:eq(2)').css('display','none');
		$('.oneInputBox:eq(3)').css('display','none');
		$('#aboutBox').css('display','block');
		$('#music').css('display','none');
		$.ajax({
			type:'GET',
			data: {},
			url: 'getAbout.do',
			success: function(result) {
				$('#aboutBox > .textBox').html(result);
			}
		});
	}
	function showPasswordBox() {
		$('#smallPics').css('display','none');
		$('#uploadImageForm').css('display','none');
		$('#mainImage').css('display','none');
		$('.oneInputBox:eq(0)').css('display','none');
		$('.oneInputBox:eq(1)').css('display','none');
		$('.oneInputBox:eq(2)').css('display','none');
		$('.oneInputBox:eq(3)').css('display','block');
		$('#aboutBox').css('display','none');
		$('#music').css('display','none');
	}
	function showMusicBox() {
		$('.oneInputBox:eq(2)').css('display','none');
		$('#smallPics').css('display','none');
		$('#uploadImageForm').css('display','none');
		$('.oneInputBox:eq(0)').css('display','none');
		$('#mainImage').css('display','none');
		$('.oneInputBox:eq(1)').css('display','none');
		$('#aboutBox').css('display','none');
		$('.oneInputBox:eq(3)').css('display','none');
		$('#music').css('display','block');
		//Getting user's favourite bands
		$.ajax({
			type:'GET',
			data: {},
			url: 'getUsersBand.do',
			success: function(result) {
				$('#music table > tbody').html(result);
			}
		});
	}
	function getBandList(typed) {
		$.ajax({
			type:'GET',
			data: {query: typed.value},
			url: 'getBandNames.do',
			success: function(result) {
				$('#list8').html(result);
			}
		});
		$('#list8').css('display','block');
	}
	function writeIntoBandSearchBox(aElemement) {
		$('.cf > input').val(aElemement.innerHTML);
		$('#list8').css('display','none');
	}
	function hideBandList() {
		$('#list8').css('display','none');
	}
	function addBand() {
		var bandName = $('.cf > input').val();
		//selecting the last row and checking if it is full
		if ($('#music table > tbody tr:last-child td').length < 3) {
			//Adding band to the table
			$('#music table > tbody tr:last-child').append('<td onclick="deleteBand(this)">'+bandName+'</td>');
		}
		//The last row is full so append a <tr> as well
		else {
			$('#music table > tbody').append('<tr><td onclick="deleteBand(this)">'+bandName+'</td></tr>')
		}
		
		//Adding band to the database
		$.ajax({
			type:'POST',
			data: {band: bandName},
			url: 'usersBandIntoDatabase.do',
			success: function(result) {
			}
		});
	}
	function deleteBand(liElement) {
		$.ajax({
			type:'POST',
			data: {bandName: liElement.innerHTML},
			url: 'deleteUsersBand.do',
			success: function(result) {
				$(liElement).remove();
			}
		});
	}
	function closePopUp(img) {
		var closeThis = img.parentElement;
		closeThis.style.display = "none";
		if (screen.width <=600) {
			document.getElementById("cssmenu").style.display = "block";
		}
	}
	function showImages(imgSrc) {
		var container = document.getElementById("slideShowPopUpContainer");
		container.style.display = "block";
		var imgDynamic = document.getElementById("dynamicImage");
		imgDynamic.src = imgSrc;
	}
	function showNextImage() {
		var currentImage = document.getElementById("dynamicImage").src;
		for (var i=0; i<allImagesGlobal.length; i++) {
			if (allImagesGlobal[i].src == currentImage) {
				if (i+1 < allImagesGlobal.length)  {
					showImages(allImagesGlobal[i+1].src);
				}
				else showImages(allImagesGlobal[0].src);
			}
		}
	}
	function showPreviousImage() {
		var currentImage = document.getElementById("dynamicImage").src;
		for (var i=0; i<allImagesGlobal.length; i++) {
			if (allImagesGlobal[i].src == currentImage) {
				if (i-1 > -1) showImages(allImagesGlobal[i-1].src);
				else showImages(allImagesGlobal[allImagesGlobal.length-1].src);
			}
		}
	}
	function deleteImage() {
		//Getting the image that is currently shown to the user
		var currentImage = document.getElementById("dynamicImage").src;
		//getting the ol element
		var ol = allImagesGlobal[0].parentElement.parentElement;
		//getting child nodes of ol element
		var olChilds = ol.childNodes;
		//finding the image that is currently shown and delete it
		for (var i=0; i<olChilds.length; i++) {
			if ((olChilds[i].firstChild) && (olChilds[i].firstChild.src == currentImage)) {
				ol.removeChild(olChilds[i]);
				break;
			}
		}
		//getting the new number of images
		allImagesGlobal = document.getElementsByClassName("profileImage");
		//if all the images were deleted show the default image
		if (allImagesGlobal.length == 0) {
			var li = document.createElement("li");
			var img = document.createElement("img");
			img.setAttribute("src", "../images/defaultProfileImage.jpg");
			img.setAttribute("alt", "defaultProfileImage");
			li.appendChild(img);
			ol.appendChild(li);
			document.getElementById("slideShowPopUpContainer").style.display = "none";
		}
		//otherwise show the first image
		else {
			showImages(allImagesGlobal[0].src);
		}
		//getting the src of the image that is to be deleted
		currentImage = currentImage.substring(currentImage.indexOf("=")+1);
		$.ajax({
			type:'POST',
			data: {imageURL: currentImage},
			url: 'deleteImage.do',
			success: function(result) {
				
			}
		});
	}
	
	function setMainImage() {
		//Getting currently shown images' src
		var currentImage = document.getElementById("dynamicImage").src;
		//changing logo image to the selected image
		document.getElementById("logo").src = currentImage;
		//writing new URL into database
		$.ajax({
			type:'POST',
			data: {imageURL: currentImage},
			url: 'setMainImage.do',
			success: function(result) {
				
			}
		});
		alert("Done :)");
	}
	function saveAge() {
		var age = document.getElementsByName("age")[0].value;
		if (age.length > 2) {
			alert("You can not be that old!!");
			return;
		}
		age = parseInt(age);
		if (isNaN(age)) {
			alert("This is not a number");
			return;
		}
		$.ajax({
			type:'POST',
			data: {ageInput: age},
			url: 'updateAge.do',
			success: function(result) {
				
			}
		});
		alert("Done :)")
	}
	function validateName() {
		var name = document.getElementsByName("name")[0].value;
		if (name.length > 50) {
			alert("Your name is too long! It can be maximum 100 characters long!");
			return false;
		}
		return true;
	}
	function saveLocation() {
		var location = document.getElementsByName("location")[0].value;
		if (location.length > 50) {
			alert("The location you have entered is unusually long!!");
			return;
		}
		document.getElementsByClassName("location")[0].innerHTML = location;
		$.ajax({
			type:'POST',
			data: {locationInput: location},
			url: 'updateLocation.do',
			success: function(result) {
				
			}
		});
		alert("Done :)");
	}
	function saveAbout() {
		var text = document.getElementsByTagName("textarea")[0].value;
		if (text.length > 600) {
			alert("You can have a maximum of 600 characters");
			return;
		}
		$.ajax({
			type:'POST',
			data: {textInput: text},
			url: 'updateAbout.do',
			success: function(result) {
				
			}
		});
		alert("Done :)");	
	}
	function savePassword() {
		var newPassword = document.getElementsByName("password")[0].value;
		$.ajax({
			type:'POST',
			data: {password: newPassword},
			url: 'updatePassword.do',
			success: function(result) {
				
			}
		});
		alert("Password updated! :)");
	}
	function characterCounter(textArea) {
		var text = textArea.value;
		document.getElementById("characterCount").innerHTML = "Character Count: "+text.length;
	}
	function uploadValidator(form) {
		if (form.childNodes[1].value.length < 4) {
			alert("You have not seleced any image!");
			return false;
		}
		return true;
	}
</script>
<style type="text/css">
#container {
	display: block;
	margin: 100px auto;
	width: 670px;
	height: 462px;
	background: #f5f5f5;
	border-radius: 5px;
	box-shadow: 0 2px 10px 2px rgba(0,0,0,.3);
}
/* Info */
.info {
	position: absolute;
	z-index: 200;
	color: #222222;
	font-family: 'Lato','Open Sans';
	font-weight: 700;
	display: block;
	text-align: center;
	margin-left: 150px;
	width: 515px;
	text-shadow: 0px 1px 1px rgba(0,0,0,0.7); 
}
.name {
	font-size: 19px;
	letter-spacing: 1px;
}
.name a {
  color: #fff;
}
.location {
	font-size: 14px;
	line-height: 22px;
	letter-spacing: 1px;
}
.location a {
	color: #ffffff;
}

.pin {
	font-family: 'Icons';
	font-size: 24px;
}
/* Menu */

.menu {
	width: 150px;
	height: 462px;
	position: absolute;
	z-index: 100;
	background: #222222;
	border-radius: 5px 0px 0 5px;
	font-family: 'Icons';
	font-size: 20px;
	text-align: center;
}

.menu ul {
	list-style-type: none;
	position: relative;
	display: block;
	top: -20px;
}

.menu ul li {
	display: block;
	width: 150px;
	position: relative;
	right: 40px;
	line-height: 65px;
	color: #d0ced1;
	-webkit-transition: all .3s ease;
  	border-bottom: rgba(70,70,70,.2) 1px solid;
}

.menu li.add {
  line-height: 63px;
}

.add {
  border-radius: 0 0 0 5px;
  border: 0;
}

.menu ul li:hover {
	background: #191919;
	border-radius: 5px 0 0 5px;
	-webkit-transition: all .2s ease;
}

.menu a {
	border: 0;
    color: #d0ced1;
    cursor: pointer;
}
/* Profile Photo */

.profile {
	width: 330px;
	height: 462px;
}

.profile img {
	z-index: 1;
	width: auto;
	height: 100%;
	position: relative;
	left: 150px;
	border-radius: 0 5px 5px 0;
}
@font-face {
  font-weight: normal;
  font-style: normal;
  font-family: 'Icons';
  src: url("http://coletownsend.com/web/content/fonts/Entypo/Entypo/entypo.eot?#iefix") format('embedded-opentype');
  src: local('?'),
  url("http://coletownsend.com/web/content/fonts/Entypo/Entypo/entypo.otf") format("opentype"),
  url("http://coletownsend.com/web/content/fonts/Entypo/Entypo/entypo.woff") format("woff"), 
  url("http://coletownsend.com/web/content/fonts/Entypo/Entypo/entypo.ttf") format("truetype"), 
  url("http://coletownsend.com/web/content/fonts/Entypo/Entypo/entypo.svg") format("svg"); 
}
#smallPics ol li {
	position: relative;
	right: 180px;
	display: inline;
	margin: 0;
}
#smallPics ol li img:hover {
	cursor: pointer;
}
#smallPics ol li img {
	width: 200px;
}
#smallPics {
	display: none;
	position: relative;
	top: 50px;
	left: 150px;
	width: 520px;
	height: 320px;
	overflow:auto;
	white-space: nowrap;
}
.oneInputBox {
	display: none;
	position: relative;
	left: 150px;
	width: 520px;
	height: 462px;
	background-image: URL('../images/contactFormBackground.png');
	font-size: 20px;
	color: #E0E0E0;
}
.oneInputBox div{
	position: relative;
	top: 190px;
	left: 80px;
}
.oneInputBox input[type=submit] {
	position: relative;
	top: 200px;
	left: 180px;
	background-color: #1DA2CF;
	border-radius: 5px;
	font-size: 20px;
	width: 150px;
	height: 40px;
	color: #E0E0E0;
}
.oneInputBox input[type=submit]:hover {
	cursor: pointer;
	color: white;
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
#aboutBox {
	display: none;
	position: relative;
	left: 150px;
	width: 520px;
	height: 462px;
	background-image: URL('../images/contactFormBackground.png');
	font-size: 20px;
	color: #E0E0E0;
}
#aboutBox span {
	position: relative;
	top: 70px;
	left: 120px;
}
#aboutBox textarea {
	position: relative;
	top: 70px;
	left: 15px;
	height: 60%;
}
#aboutBox input {
	position: relative;
	top: 50px;
	background-color: #1DA2CF;
	border-radius: 5px;
	font-size: 20px;
	width: 150px;
	height: 40px;
	color: #E0E0E0;
}
#aboutBox input:hover {
	cursor: pointer;
	color: white;
}
#characterCount {
	position: relative;
	bottom: 0;
	left: 340px;
}
#music {
	display: none;
	position: relative;
	left: 150px;
	width: 520px;
	height: 462px;
	background-image: URL('../images/contactFormBackground.png');
	font-size: 20px;
	color: #E0E0E0;
	overflow: auto;
}
#music table {
	position: absolute;
	top: 150px;
	text-align: left;
	overflow: hidden;
	width: 100%;
	margin: 0 auto;
  	display: table;
  
}
#music table > tbody tr td {
	font-weight: normal;
	font-size: 1em;
  	-webkit-box-shadow: 0 2px 2px -2px #0E1119;
	-moz-box-shadow: 0 2px 2px -2px #0E1119;
	box-shadow: 0 2px 2px -2px #0E1119;
	padding-bottom: 2%;
	padding-top: 2%;
   	padding-left:2%;
   	height: 30px;
}
#music table > tbody tr:nth-child(odd) {
	  background-color: #323C50;
}
#music table > tbody tr:nth-child(even) {
	  background-color: #2C3446;
} 
#music table > tbody tr td:hover:after {
	content: "\2718";
	color: red;
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
#buttonHolder button:first-child {
	float: left;
	background-color: #1DA2CF;
	border-radius: 5px;
	font-size: 20px;
	width: 230px;
	height: 40px;
	color: #E0E0E0;
}
#buttonHolder button:last-child {
	float: right;
	background-color: #1DA2CF;
	border-radius: 5px;
	font-size: 20px;
	width: 230px;
	height: 40px;
	color: #E0E0E0;
}
#buttonHolder button:hover {
	color: white;
	cursor: pointer;
}
#buttonHolder {
	position: absolute;
	bottom: 20px;
	left: 0;
    right: 0;
    margin: auto;
    width: 600px;
	z-index: 400;
}
#uploadImageForm input[type=submit] {
	float: right;
	display: inline;
	background-color: #1DA2CF;
	border-radius: 5px;
	font-size: 20px;
	width: 150px;
	height: 40px;
	color: #E0E0E0;
}
#uploadImageForm input[type=submit]:hover {
	cursor: pointer;
	color: white;
}
#uploadImageForm input[type=file] {
	float: left;
	display: inline;
	background-color: #1DA2CF;
	border-radius: 5px;
	font-size: 20px;
	width: 250px;
	height: 40px;
	color: #E0E0E0;
}
#uploadImageForm {
	display: none;
	position:relative;
	left: 150px;
	top: 100px;
	width: 510px;
}
@media only screen and (max-device-width: 970px)  {
	#container {
		margin: 300px auto;
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
}
/* Icon Tweaks */
</style>
</head>
<body>
<jsp:include page="htmlFiles/header.jsp">
	<jsp:param value="${pageContext.request.userPrincipal.name}" name="username" />
</jsp:include>
<div id="wrapper">
  <div id="container">
    <div class="info">
      <info:getUserLocationAndName username="${pageContext.request.userPrincipal.name}" />
	</div>
	<div class="menu">
		<ul>
          <li><a onclick="uploadPicture()">Pictures</a></li>
          <li><a onclick="showNameBox()">Name/Nickname</a></li>
          <li><a onclick="showAgeBox()">Age</a></li>
          <li><a onclick="showLocationBox()">City</a></li>
          <li><a onclick="showAboutBox()">About</a></li>
          <li><a onclick="showPasswordBox()">Password</a></li>
          <li><a onclick="showMusicBox()">Music</a></li>
		</ul>
	</div>
	<div class="profile">
		<div id="smallPics">
			<ol>
				<images:getImages username="${pageContext.request.userPrincipal.name}" />
			</ol>
		</div>
     	<img src="${sessionScope.mainImage}" alt="profile picture" id="mainImage" />
     	<form id="uploadImageForm" method="post" action="upload.do" enctype="multipart/form-data" onsubmit="return uploadValidator(this)">
     		<input type="file" name="filename" accept="image/jpeg, image/png" />
     		<input type="submit" value="Upload Image" />
     	</form>
     	<form class="oneInputBox" method="post" action="updateName.do" onsubmit="return validateName()">
     		<div>
     			Name/Nickname: 
     			<input type="text" name="name" class="textBox" />
     		</div>
     		<input type="submit" value="Save!" />
     	</form>
     	<div class="oneInputBox">
     		<div>
     			Enter your age: 
     			<input type="text" name="age" class="textBox" />
     		</div>
     		<input type="submit" value="Save!" onclick="saveAge()" />
     	</div>
     	<div class="oneInputBox">
     		<div>
     			Enter your location: 
     			<input type="text" name="location" class="textBox" />
     		</div>
     		<input type="submit" value="Save!" onclick="saveLocation()" />
     	</div>
     	<div id="aboutBox">
     		<span>Write Something about yourself</span>
     		<textarea rows="4" cols="50" class="textBox" onkeyup="characterCounter(this)"></textarea>
     		<input type="submit" value="Save!" onclick="saveAbout()" />
     		<p id="characterCount">Character Count: 0</p>
     	</div>
     	<div class="oneInputBox">
     		<div>
     			Enter your new password: 
     			<input type="password" name="password" class="textBox" />
     		</div>
     		<input type="submit" value="Save!" onclick="savePassword()" />
     	</div>
     	<div id="music" onclick="hideBandList()">
     		<div class="form-wrapper cf">
  				<input type="text" placeholder="Search here..." onkeyup="getBandList(this)" />
	  			<button type="submit" onclick="addBand()">Add</button>
			</div>
			<div id="list8">
			</div>
			<table>
				<tbody>
				
				</tbody>		
			</table>
		</div>
    </div>
  </div>
</div>
<div id="slideShowPopUpContainer">
	<img class="closeButton" src="../images/closeButton.png" alt="closeButton" title="close window" onclick="closePopUp(this)" />
	<img id="forwardArrow" src="../images/forward-arrow.png" alt="forward-arrow" title="show next image" onclick="showNextImage()" />
	<img id="backArrow" src="../images/back-arrow.png" alt="back-arrow" title="show previous image" onclick="showPreviousImage()" />
	<img id="dynamicImage" />
	<div id="buttonHolder">
		<button onclick="deleteImage()">Delete this image</button>
		<button onclick="setMainImage()">Set this image as main</button>
	</div>
</div>
<jsp:include page="../htmlFiles/footer.jsp" >
	<jsp:param value="../images/twitterIcon.png" name="twitterURL"/>
	<jsp:param value="../images/facebookIcon.jpe" name="facebookURL"/>
</jsp:include>
</body>
</html>