Ext.define('Hcv.view.sequence.List' ,{
	extend: 'Ext.grid.Panel',
	alias: 'widget.sequencelist',
	
	title: 'Sequences',
	store: 'Sequences',
	
	verticalScrollerType: 'paginggridscroller',
	loadMask: true,
	disableSelection: true,
	invalidateScrollerOnRefresh: false,
	viewConfig: {
		trackOver: false
	},
	
	plugins: [{
		ptype: 'rowexpander',
		rowBodyTpl : [
			'<b>Accession:</b> {accession}<br>',
			'<b>Sequence:</b> {sequence}'
		]
	}],
	
	initComponent: function() {
		this.columns = [
			{xtype: 'rownumberer', width: 25, sortable: true},
			{header: 'Accession',  dataIndex: 'accession',  width: 120},
			{header: 'Tags',  dataIndex: 'tags',  width: 50},
			{header: 'GI',  dataIndex: 'gi',  width: 50},
			{header: 'Length',  dataIndex: 'length',  width: 50},
			{header: 'Taxon',  dataIndex: 'taxon',  width: 50},
			{header: 'Sequence', dataIndex: 'sequence', flex: 1},
			{header: 'Description', dataIndex: 'description', flex: 1}
		];
		this.dockedItems=[
		{
			xtype: 'pagingtoolbar',
			store: 'Sequences',
			dock: 'top',
			displayInfo: true
		}];
		this.callParent(arguments);
	}
});
