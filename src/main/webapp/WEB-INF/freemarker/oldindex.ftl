<!DOCTYPE html>

<html>

<head>
<title>TITLE</title>
<meta charset="utf-8"/>
<script type="text/javascript" src="api-debug.js"></script>
</head>

<body>
<h1>Homepage</h1>

<#--
totalPages: ${page.totalPages}<br/>
size: ${page.size}<br/>

<ul>
<#list sequences as sequence>
	<li>${sequence.accession}: ${sequence.sequence}</li>
</#list>
</ul>
-->

<form action="load.html" method="post">
<input type="submit"/>
</form>
Thymeleaf test
</body>
</html>