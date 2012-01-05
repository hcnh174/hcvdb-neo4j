<h1>Homepage</h1>

totalPages: ${page.totalPages}<br/>
size: ${page.size}<br/>

<ul>
<#list sequences as sequence>
	<li>${sequence.name}: ${sequence.sequence}</li>
</#list>
</ul>

<form action="load.html" method="post">
<input type="submit"/>
</form>