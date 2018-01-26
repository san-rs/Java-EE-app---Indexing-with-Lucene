<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<title>Given Input Results</title>
</head>
<body>
	<div class="container">
  	<h1>Search Bar</h1>
  	<div class="panel panel-default">
    		<div class="panel-heading">
    			<form action="/aw_assg3/rest/search" method="post">
    				Enter your Query: <input type="text" name="query" size="20">
    				<input type="submit" value="Search"></input>
			</form>
			<br><br>
		</div>
		</div>
	</div>
	
	<div class="container">
  		<h1>Given Input Results</h1>
  		<c:forEach items="${result}" var="r" varStatus="status">
  		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h4>${querystr[status.index]}</h4>
    			</div>
    			<div class="panel-body"><c:forEach items="${r}" var="r2">
 				<a href="${r2}">${r2}<br></a>
 				</c:forEach>
 				<br><br>
 			</div>
	  	</div>
  		</c:forEach>
	</div>
</body>
</html>