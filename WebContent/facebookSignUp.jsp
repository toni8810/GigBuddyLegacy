<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facebook Sign Up</title>
<script src="scripts/jquery-1.12.1.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="cssFiles/navigationBar.css" />
<link rel="stylesheet" type="text/css" href="cssFiles/footer.css" />
<link rel="stylesheet" type="text/css" href="cssFiles/loadAnimation.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<style type="text/css">
	#status {
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
	#countdown {
		color: red;
	}
	#popUpRegistrationBoxContainer {
		position: fixed;
		z-index: 400;
		text-align: center;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;	
		width: 100%;
		background-color: rgba(182, 204, 250, 0.7);
	}
	#signUpButton {
		position: absolute;
		top: 48%;
		right: 54%;
		transform:scale(2.94);
		-webkit-transform:scale(2.94);
		transform-origin:0 0;
		-webkit-transform-origin:0 0;
	}
	#footer {
		margin-top: 450px;
		width: 100%;
		height: 50px;
		background-color: #A0A0A0;
		overflow: hidden;
	}
	@media only screen and (max-device-width: 900px) {
	#footer {
		margin-top: 1500px;
		width: 100%;
		height: 50px;
		background-color: #A0A0A0;
		overflow: hidden;
	}
}
</style>
<script type="text/javascript">
	function logWriter(textParam,locationNeeded) {
		$.ajax({
			type:'POST',
			async: false,
			data: {text: textParam, location: locationNeeded},
			url: 'writeIntoLogFile.do',
			success: function(result) {
			}
		});	
	}
</script>
</head>
<body>
<script>
	var currentAlbum = 0;
	var numberOfAlbums;
	var intervalID;
	var response;
	var profileImageId;
	var numberOfImagesDownloaded = 0;
	var countdownCounter = 10;
	var countdownIntervalID;
	var fbApiKey = "${initParam['apiKey']}";
	var currentUrl = window.location.href; 
	if (currentUrl.indexOf("error_code=200") > 0) {
		logWriter("Permission denied! Redirecting to homepage", false);
		window.location.href = "/";
	}
	else logWriter("Page opened on "+ new Date(),true);
  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
    	logWriter("Facebook connected",false);
      // Logged into your app and Facebook.
      $('#signUpButton').css('display','none');
      testAPI();
    } else if (response.status === 'not_authorized') {
      // The person is logged into Facebook, but not your app.
      var path = "https://www.facebook.com/v2.8/dialog/oauth?client_id="+fbApiKey+"&response_type=token&scope=public_profile,email,user_likes,user_photos&redirect_uri=" + window.location;
      logWriter("Not authorized! Redirecting to "+path+"",false);
      window.location.href = path;
      $('#bowlG').css('display','none');
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
    } else {
      // The person is not logged into Facebook, so we're not sure if
      // they are logged into this app or not.
      logWriter("Not logged into facebook",false);
      window.location.href = "/";
      $('#bowlG').css('display','none');
      document.getElementById('status').innerHTML = 'Please log ' +
        'into Facebook.';
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
  logWriter("Initilizing FB API!",false);
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

  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });
/**/
  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function testAPI() {
	logWriter("Start fething information from facebook",false);
	$('#bowlG').css('display','block');
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', {fields: 'id,name,email,music,albums,picture'}, function(response) {
    	this.response = response;
    	console.log('Successful login for: ' + response.name);
    	console.log('Members band likes: ' + JSON.stringify(response.music));
    	logWriter(response.name+" sucessfully fetched",false);
      	if (!response.email) response.email = (response.id).toString();
      	//document.getElementById('status').innerHTML =
        //'Thanks for logging in, ' + JSON.stringify(response.albums.data[0]) + '!<br><h1>'+response.email+' '+response.id+'</h1>';
        logWriter("Start writing things into database",false);
  	$.ajax({
		type:'POST',
		data: {name: response.name, email: response.email, id: response.id, bands: JSON.stringify(response.music)},
		url: 'facebookSignUp.do',
		success: function(result) {
			logWriter(response.name+" "+response.email+" "+response.id+" and bands stored",false);
			if (result.indexOf("Congratulations") > -1) {
				$.ajax({
					type:'POST',
					data: {email: response.email},
					url: 'welcomeEmail.do',
					success: function(result) {
					}
				}); 
				if (response.albums != null) {
					logWriter("number of albums user has: "+response.albums.data.length+"",false);
					console.log('Changing status div to "Getting Images""');
					$('#status').html('Getting Images');
					$('#popUpRegistrationBoxContainer').css('display','block');
					$('#bowlG').css('display','block');
					profileImageId = response.picture.data.url;
					profileImageId = profileImageId.substring(profileImageId.indexOf('_')+1);
					profileImageId = profileImageId.substring(0,profileImageId.indexOf('_'));
		    		numberOfAlbums = response.albums.data.length;
		    		if (numberOfAlbums == 1) {
		    			getImage();
		    		}
		    		else {
		    			getImage();
			        	intervalID = setInterval(getImage, 4000);
		    		}
		    		
				}
				else {
					$('#status').html(result);
					$('#popUpRegistrationBoxContainer').css('display','none');
					$('#bowlG').css('display','none');
				}
			}
			else {
				$('#status').html(result);
				$('#popUpRegistrationBoxContainer').css('display','none');
				$('#bowlG').css('display','none');
			}
		}
	}); 
    }); 
  }
function getImage() {
	var deferred = $.Deferred();
	FB.api('/'+response.albums.data[currentAlbum].id+'/photos', function(responseAlbums) {
		deferred.resolve(responseAlbums.data);
	});
	deferred.done(function(albumJson) {
					  	var i = 0;
						while (i<albumJson.length) {
							numberOfImagesDownloaded++;
							if (numberOfImagesDownloaded < 30) {
								FB.api('/'+albumJson[i].id+'?fields=images', function(responseImg) {
									$.ajax({
										type:'POST',
										data: {url: responseImg.images[0].source, email: response.email, isProfileImage: responseImg.id == profileImageId},
										url: 'downloadImage.do',
										success: function(result) {
											
										}
									});
								});
							}
							else {
								console.log("number of images downloaded is more than 20 "+numberOfImagesDownloaded);
								currentAlbum = numberOfAlbums-1;
							}
							i++;
						}
					});
	currentAlbum++;
	if (numberOfAlbums == currentAlbum) {
		window.clearInterval(intervalID);
		console.log('Clearing interval and change status div as '+numberOfImagesDownloaded+' have been downloaded');
		$('#status').html('Congratulations '+response.name+'!<br>You have sucessfully signed up! Your username is '+response.email+' and your password is '+response.email+'<br>You will get logged in in <span id="countdown"></span> seconds');
		$('#popUpRegistrationBoxContainer').css('display','none');
		$('#bowlG').css('display','none');
		//starting countdown
		countdownIntervalID = setInterval(countdown, 1000);
	}
	  
}
function countdown() {
	$('#countdown').html(countdownCounter);
	countdownCounter--;
	//If 5 seconds left check if profile has been found
	if (countdownCounter == 5) setProfileImage();
	if (countdownCounter == 0) {
		clearInterval(countdownIntervalID);
		window.location.href = "/members/profile.jsp";
	}
}
//This method checks if profile image url has been updated in the database
//If it has, it does not do anything
//If it has not, it downloads profile picture and set path in the database
function setProfileImage() {
	$.ajax({
		type:'POST',
		data: {email: response.email, id: response.id},
		url: 'setProfileImageIfNotSet.do',
		success: function(result) {
			console.log("Set Profile picture servlet has finished");
		}
	});
}
</script>

<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->
<div id="popUpRegistrationBoxContainer">
	<div id="bowlG">
		<div id="bowl_ringG">
			<div class="ball_holderG">
				<div class="ballG"></div>
			</div>
		</div>
	</div>
	<fb:login-button scope="public_profile,email,user_likes,user_photos" onlogin="checkLoginState();" size="xlarge" id="signUpButton">Sign Up</fb:login-button>
</div> 
<jsp:include page="htmlFiles/navigationBar.html" />
<div id="status">
	
</div>
<jsp:include page="htmlFiles/footer.jsp" >
	<jsp:param value="images/twitterIcon.png" name="twitterURL"/>
	<jsp:param value="images/facebookIcon.jpe" name="facebookURL"/>
</jsp:include>
</body>
</html>