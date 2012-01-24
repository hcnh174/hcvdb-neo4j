<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org">
<head>
<link rel="stylesheet" type="text/css" href="http://cdn.sencha.io/ext-4.0.7-gpl/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="wro/hcv.css"/>
	
<script src="http://cdn.sencha.io/ext-4.0.7-gpl/ext-all-debug.js"></script>
<script type="text/javascript" src="wro/jsphylosvg.js"></script>
<script>
/*<![CDATA[*/
Ext.onReady(function(){
	loadTree();
});
/*]]>*/
</script>		
</head>
<body>
	<div id="svgCanvas"> </div>
</body>
</html>		
