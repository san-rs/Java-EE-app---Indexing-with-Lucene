<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<title>Index Page</title>
</head>
<body>
	<h1>Welcome to Assignment 3</h1>
	<h2>By Suraj Ravishankar</h2><br>
	<div class="container">
  		<h2>Search Bar</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">	<form action="/aw_assg3/rest/search" method="post">
    			Enter your Query: <input type="text" name="query" size="20">
    			<input type="submit" value="Search"></input>
			</form>
			<br><br><br>
		</div>
  		</div>
	</div>
	
	<div class="container">
  		<h2>Given Input Results</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">	<form action="/aw_assg3/rest/input" method="get">
			<button type="submit"><h4>CLICK TO SEE GIVEN INPUT</h4></button>
			</form>
		</div>
  		</div>
	</div>
	
	<div class="container">
  		<h2>How is indexing being done?</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">
    			<h4>I am indexing the Wikibooks content using the Lucene library.<br>I am getting the text files of the Wikibooks pages using a python crawler. The text files containing Field(s) are indexed to IndexWriter which analyzes the text files using the Analyzer and then creates/open/edit indexes as required and store/update them in a Directory.<br> IndexWriter is used to update or create indexes. It is not used to read indexes.
    			<br>Indexing process is one of the core functionality provided by Lucene. IndexWriter is the most important and core component of the indexing process.<br>When lucene indexes a document it breaks it down into a number of terms. It then stores the terms in an index file where each term is associated with the documents that contain it. You could think of it as a bit like a hashtable. <br>Terms are generated using an analyzer which stems each word to its root form. The most popular stemming algorithm for the english language is the Porter stemming algorithm.
			<br>When a query is issued it is processed through the same analyzer that was used to build the index and then used to look up the matching term(s) in the index. That provides a list of documents that match the query.<br>
			The wikibooks crawled content is in the input folder and the oracle tutorials are in the oracle tutorials folder. <br> Stemming is done by the analyzer using the Porter stemming algorithm.<br>
			I am calling the Lucene library to index the files and store it in a RAM Directory. I am using Singleton Design Pattern which ensures that indexing is not done again and again.<br>
			I am then querying the input query and getting the results in an ArrayList which I am then displaying in JSP. I am using RESTful practices using Jersey (JAX-RS).
			</h4>
		</div>
  		</div>
	</div>
	
	<div class="container">
  		<h2>Given Input Results</h2>
  		<div class="panel panel-default">
    		<div class="panel-heading">	<h4>My special UI feature is a search box at the top of the page.<br>It can be used to search any custom queries other than the input queries given by the professor.</h4>
  		</div>
	</div>
	</div>
</body>
</html>