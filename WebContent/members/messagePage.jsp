<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>${title}</title>
<link rel="stylesheet" type="text/css" href="cssFiles/header.css" />
<link rel="stylesheet" type="text/css" href="../cssFiles/footer.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<style type="text/css">
	h1 {
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
</style>
</head>
<body>
	<jsp:include page="htmlFiles/header.jsp">
		<jsp:param value="${pageContext.request.userPrincipal.name}" name="username" />
	</jsp:include>
	<h1>${message}</h1>
	<jsp:include page="../htmlFiles/footer.jsp" >
		<jsp:param value="../images/twitterIcon.png" name="twitterURL"/>
		<jsp:param value="../images/facebookIcon.jpe" name="facebookURL"/>
	</jsp:include>
</body>
</html>