<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="manage" uri="CustomTagLibrary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Manage submitted requests and events</title>
<link rel="stylesheet" type="text/css" href="cssFiles/header.css" />
<link rel="stylesheet" type="text/css" href="../cssFiles/footer.css" />
<link rel="shortcut icon" href="/favicon.ico" />
<script src="../scripts/jquery-1.12.1.min.js"></script>
<style type="text/css">
	h1 {
		width: 35%;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50px;
		color: #c9d0d4;
		font-family: 'Helvetica Neue', sans-serif;
		font-size: 46px;
		font-weight: 100;
		line-height: 50px;
		letter-spacing: 1px;  
		border-bottom: double #555;
	}
	.table31 {
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
	
	.button{
		background-image: -webkit-linear-gradient(top, #f4f1ee, #fff);
		background-image: linear-gradient(top, #f4f1ee, #fff);
		border-radius: 50%;
		box-shadow: 0px 8px 10px 0px rgba(0, 0, 0, .3), inset 0px 4px 1px 1px white, inset 0px -3px 1px 1px rgba(204,198,197,.5);
		float:left;
		height: 50px;
		position: relative;
		width: 50px;			
		-webkit-transition: all .1s linear;
		transition: all .1s linear;
	}
	.button:after{
		color:#e9e6e4;
		content: "";
		display: block;
		font-size: 30px;
		height: 30px;
		text-decoration: none;
		text-shadow: 0px -1px 1px #bdb5b4, 1px 1px 1px white;
		position: absolute;
		width: 30px;
	}
	.cross:after{
		content: "✖";
		left: 12px;
		top: 5px;
	}
	.button:hover{
		background-image: -webkit-linear-gradient(top, #fff, #f4f1ee);
		background-image: linear-gradient(top, #fff, #f4f1ee);
		color:#0088cc;
		cursor: pointer;
	}
	.cross:hover:after{
		color:#eb2f2f;
		text-shadow:0px 0px 6px #eb2f2f;
	}
	.button:active{
		background-image: -webkit-linear-gradient(top, #efedec, #f7f4f4);
		background-image: linear-gradient(top, #efedec, #f7f4f4);
		box-shadow: 0 3px 5px 0 rgba(0,0,0,.4), inset 0px -3px 1px 1px rgba(204,198,197,.5);
	}
	.button:active:after{
		color:#dbd2d2;
		text-shadow: 0px -1px 1px #bdb5b4, 0px 1px 1px white;
	}
	
	.hidden {
		display: none;
	}
</style>
<script type="text/javascript">
	function deleteBuddyRequest(td) {
		var tr = td.parentElement.parentElement;
		var id = tr.childNodes[tr.childNodes.length-1].innerHTML;
		tr.style.display = "none"
		$.ajax({
			type:'POST',
			data: {idInput: id},
			url: 'deleteBuddyRequest.do'
		});
	}
	function deleteEvent(td) {
		var tr = td.parentElement.parentElement;
		var childs = tr.childNodes;
		var title = childs[0].innerHTML;
		var location = childs[1].innerHTML;
		var date = childs[2].innerHTML;
		var time = childs[3].innerHTML;
		tr.style.display = "none"
		$.ajax({
			type:'POST',
			data: {titleInput: title, locationInput: location, dateInput: date, timeInput: time},
			url: 'deleteEvent.do'
		});
	}
</script>
</head>
<body>
<jsp:include page="htmlFiles/header.jsp">
	<jsp:param value="${pageContext.request.userPrincipal.name}" name="username" />
</jsp:include>
<manage:getUserEventsAndRequests username="${pageContext.request.userPrincipal.name}" />
<jsp:include page="../htmlFiles/footer.jsp" >
	<jsp:param value="../images/twitterIcon.png" name="twitterURL"/>
	<jsp:param value="../images/facebookIcon.jpe" name="facebookURL"/>
</jsp:include>
</body>
</html>