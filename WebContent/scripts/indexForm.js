function openPopUp() {
	document.getElementById("popUpRegistrationBoxContainer").style.display = "block";
}
function closePopUp(img) {
	var closeThis = img.parentElement;
	closeThis.style.display = "none";
	setCookie("messageCookie", "", 0);
}
function validateRegistrationForm() {
	var username = document.getElementsByName("u")[0];
	var password = document.getElementsByName("p")[0];
	if (username.value.length > 30) {
		alert("Your email address can be maximum 30 characters long!!");
		username.style.border = "1px solid red";
		username.value = "";
		username.focus();
		return false;
	}
	else if (password.value.length > 12) {
		alert("Password can be maximum 12 characters long!!");
		password.style.border = "1px solid red";
		password.value = "";
		password.focus();
		return false;
	}
	else if (grecaptcha.getResponse().length === 0) {
		document.getElementsByClassName("g-recaptcha")[0].style.border = "2px solid red";
		alert("Whoops! You have not completed the captcha");
		return false;
	}
	document.getElementById("waitPopUpContainer").style.display = "block";
	return true;
}
function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays*24*60*60*1000));
	var expires = "expires="+ d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires;
}
function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i = 0; i <ca.length; i++) {
		var c = ca[i];
	    while (c.charAt(0)==' ') {
	    	c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	    	return c.substring(name.length,c.length);
	    }
	}
	return "";
}
//decides if the user has been on the site before or not using cookies
function setRegistrationPopup() {
	if (getCookie("beenHere") == "") {
		setCookie("beenHere", "yes", 30);
		openPopUp();
	}
	else {
		setCookie("beenHere", "yes", 30);
	}
}
function setMessagePopup() {
	if (getCookie("messageCookie").length > 0) {
		document.getElementsByClassName("message")[1].innerHTML = getCookie("messageCookie");
		document.getElementById("messagePopUpContainer").style.display = "flex";
	}
}
function logInIfJustSignedUp(username) {
	if (username) {
		console.log(username);
		//If username contains "
		if (username.indexOf('"') >= 0) {
			//remove all "
			username = username.replace(/\"/g, "");
		}
		var loginForm = document.getElementsByTagName("form")[0];
		loginForm.j_username.value = username;
		$.ajax({
			type:'GET',
			data: {email: username},
			url: '/getUserLoginDetails.do',
			success: function(result) {
				loginForm.j_password.value = result.password;
				loginForm.submit();
			}
		}); 
		
	}
}