<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Claim T-Shirt</title>
<link rel="stylesheet" type="text/css" href="cssFiles/navigationBar.css" />
<link rel="stylesheet" type="text/css" href="cssFiles/footer.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<link rel="stylesheet" href="cssFiles/radioButton.css"/>
<style type="text/css">

	#claimForm {
		background-image: URL('images/contactFormBackground.png');
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
	#claimForm > table tr + tr td input {
		width: 230px;
	}
	form > input[type=text] {
		display: none;
	}
	textarea {
		width: 440px;
	}
	#claimForm > table {
		margin-left: 20px;
		margin-top: 20px;
	}
	#submitButton {
		margin-left: 170px;
		margin-top: 70px;
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
  		right: 20px;
  		overflow: hidden;
  		height: 28px;
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

</style>
</head>
<body>
<jsp:include page="/htmlFiles/navigationBar.html" />
<form action="confirmClaim.do" id="claimForm" method="post">
	<table>
		<tr>
			<td>*Name</td>
			<td>*Address</td>
		</tr>
		<tr>
			<td><input type="text" name="name" class="textBox" required="required" /></td>
			<td><input type="text" name="address" class="textBox" required="required" /></td>
		</tr>
		<tr>
			<td>*Male/Female</td>
			<td>*Size</td>
		</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td>
								<input type="radio" name="gender" id="maleRadio" class="css-checkbox" value="Male" />
								<label for="maleRadio" class="css-label radGroup1">Male</label>
							</td>
							<td>
								<input type="radio" name="gender" id="femaleRadio" class="css-checkbox" checked="checked" value="Female"/>
								<label for="femaleRadio" class="css-label radGroup1">Female</label>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<div class="container">
  						<div class="dropdown dropdown-dark">
    						<select name="size" class="dropdown-select">
      							<option value="S">S</option>
      							<option value="M" selected="selected" >M</option>
      							<option value="L">L</option>
      							<option value="XL" >XL</option>
    						</select>
  						</div>
					</div>
  				</td>
			</tr>
		</table>
		<input type="text" name="userId" value="${userId}" style="display:none" />
		<input type="submit" value="Claim T-Shirt" id="submitButton" />
	</form>
	<jsp:include page="/htmlFiles/footer.jsp" >
		<jsp:param value="images/twitterIcon.png" name="twitterURL"/>
		<jsp:param value="images/facebookIcon.jpe" name="facebookURL"/>
	</jsp:include>
</body>
</html>