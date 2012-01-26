Ext.define('Hcv.model.Sequence', {
	extend: 'Ext.data.Model',
	idProperty: 'accession',
	fields:
	[
		{name: 'accession'},
		{name: 'length', type: 'int'},
		{name: 'gi', type: 'int'},
		{name: 'taxon_id', type: 'int'},
		{name: 'description'},
		{name: 'sequence'}
	]
});
