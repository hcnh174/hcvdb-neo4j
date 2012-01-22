Ext.define('Hcv.model.Tag', {
	extend: 'Ext.data.Model',
	idProperty: 'identifier',
	fields:
	[
	 	{name: 'identifier'},
	 	{name: 'type'},
	 	{name: 'name'},	 	
	 	{name: 'description'}
	]	
});