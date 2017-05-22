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