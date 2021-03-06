//http://www.sencha.com/forum/showthread.php?175777-Infinite-scroll-requests-more-data-after-all-records-have-been-returned
Ext.define('Hcv.view.sequence.List' ,{
	extend: 'Ext.grid.Panel',
	alias: 'widget.sequencelist',
	
	title: 'Sequences',
	store: 'Sequences',
	//autoScroll: true,
	//layout: 'fit',
	//width : 700,
	height: 450,
	//verticalScrollerType : 'paginggridscroller',
//	loadMask : false,
//	disableSelection : true,
//	invalidateScrollerOnRefresh : false,
//	viewConfig : {
//		trackOver : false
//	},
	
	plugins: [{
		ptype: 'rowexpander',
		rowBodyTpl : [
		    '<p style="background-color: lightblue">',
			'<b>Accession:</b> {accession}<br>',
			'<b>Sequence:</b> {sequence}',
			'</p>'
		]
	}],
	
	initComponent: function() {
		var self=this;
		this.columns = [
			//{xtype: 'rownumberer', width: 25, sortable: true},
			{header: 'Accession',  dataIndex: 'accession',  width: 120},
			{header: 'Tags',  dataIndex: 'tags',  width: 50},
			{header: 'GI',  dataIndex: 'gi',  width: 50},
			{header: 'Length',  dataIndex: 'length',  width: 50},
			{header: 'Taxon',  dataIndex: 'taxon_id',  width: 50},
			{header: 'Refs',  dataIndex: 'ref_ids',  width: 50},
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
			
//		this.on('render', function(){
//			self.store.prefetch({
//		        start: 0,
//		        limit: 100,
//		        callback: function() {
//		            self.store.guaranteeRange(0, 20);		       
//		        }
//		    });
//		});	
	}
});
