Ext.define('Hcv.model.Ref', {
	extend: 'Ext.data.Model',
	idProperty: 'id',
	fields:
	[
	 	{name: 'id', type: 'int'},
	 	{name: 'type'},
	 	{name: 'authors'},
	 	{name: 'year'},
	 	{name: 'title'},
	 	{name: 'journal'},
	 	{name: 'volume'},
	 	{name: 'pages'},
	 	{name: 'publisher'},
	 	{name: 'city'},
	 	{name: 'abstrct'}
	]	
});
