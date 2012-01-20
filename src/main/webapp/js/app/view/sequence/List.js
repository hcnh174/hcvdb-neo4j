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
			'<p><b>Accession:</b> {accession}</p><br>',
			'<p><b>Sequence:</b> {sequence}</p>'
		]
	}],
	
	initComponent: function() {
		this.columns = [
			{xtype: 'rownumberer', width: 50, sortable: true},
			{header: 'Accession',  dataIndex: 'accession',  width: 120},
			{header: 'Sequence', dataIndex: 'sequence', flex: 1}
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
