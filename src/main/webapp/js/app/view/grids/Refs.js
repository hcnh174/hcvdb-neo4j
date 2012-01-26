Ext.define('Hcv.view.grids.Refs' ,{
	extend: 'Ext.grid.Panel',
	alias: 'widget.refs',
	
	title: 'References',
	store: 'Refs',

//	frame:true,
//	autoHeight: true,
//	autoWidth: true,
//	collapsible: false,
//	pagesize: 20,
	
//	plugins: [{
//		ptype: 'rowexpander',
//		rowBodyTpl : [			
//			'<p style="background-color:white;padding: 5px;">',
//			'<div class="references">{citation}</div>',
//			'<br/>',
//			'<div style="padding:2px;">{abstrct}</div>',
//			'</p>'
//		]
//	}],

	initComponent: function() {
		this.columns = [
			{header: "Name", width: 125, sortable: true, dataIndex: 'name'},
			{header: "Title", width: 230, sortable: true, dataIndex: 'title'},
			{header: "Year", width: 60, sortable: true, dataIndex: 'year'},
			{header: "PubMed", width: 60, sortable: true, dataIndex: 'id', align: 'right', renderer: this.renderPmid}
			//{header: "Sequences", width: 50, sortable: true, dataIndex: 'numsequences', align: 'right', renderer: this.renderCount}
		];
//		this.dockedItems=[
//		{
//			xtype: 'pagingtoolbar',
//			store: 'Refs',
//			dock: 'top',
//			displayInfo: true
//		}];
		this.callParent(arguments);
	},

	renderPmid:function(value, p, record)
	{
		var href='http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?db=pubmed&cmd=Retrieve&dopt=AbstractPlus&list_uids={0}&query_hl=22&itool=pubmed_docsum';
		return String.format('<span class="pmid"><a href="'+href+'" target="_blank">{0}</a></span>',value);
	}
	
	//initComponent:function()
	//{
//		var self=this;
//		var reader = new Ext.data.JsonReader(
//		{
//			root: 'references',
//			totalProperty: 'totalCount',
//			idProperty: 'id',
//			fields:
//			[
//				{name: 'id', type: 'int'},
//				{name: 'identifier'},
//				{name: 'name'},
//				{name: 'year'},
//				{name: 'title'},
//				{name: 'citation'},
//				{name: 'abstrct'},
//				{name: 'pmid'},
//				{name: 'numsequences', type: 'int'}
//			]
//		});

//		var store=new Ext.data.Store(
//		{
//			url: vardb.webapp+'/ajax/references.json',
//			reader: reader,
//			remoteSort: true,
//			sortInfo: {field: 'authors,year', direction: 'ASC,ASC'}
//		});

//		var sm=new Ext.grid.CheckboxSelectionModel({
//			sortable: true,
//			width: 20
//		});
	
//		var expander = new Ext.ux.grid.RowExpander({
//			tpl : new Ext.XTemplate(
//				'<p style="background-color:white;padding: 5px;">',
//				'<div class="references">{citation}</div>',
//				'<br/>',
//				'<div style="padding:2px;">{abstrct}</div>',
//				'</p>')
//		});
//		
		

//		var config=
//		{
//			sm: sm,
//			store: store,
//			viewConfig: {forceFit:true},
//			plugins: expander,
//			columns:
//			[
//				expander,
//				sm,
//				{header: "Name", width: 125, sortable: true, dataIndex: 'name'},
//				{header: "Title", width: 230, sortable: true, dataIndex: 'title'},
//				{header: "Year", width: 60, sortable: true, dataIndex: 'year'},
//				{header: "PubMed", width: 60, sortable: true, dataIndex: 'pmid', align: 'right', renderer: this.renderPmid},
//				{header: "Sequences", width: 50, sortable: true, dataIndex: 'numsequences', align: 'right', renderer: this.renderCount}
//			],
//			tbar: new Ext.PagingToolbar(
//			{
//				pageSize: this.pagesize,
//				store: store,
//				displayInfo: true,
//				displayMsg: '{0} - {1} of {2}',
//				emptyMsg: 'None',
//				items:
//				[
//					'-',this.createSelectMenu(),
//					'-',this.createCartMenu('REF')
//				]
//			})
//		};
//		Ext.apply(this, Ext.apply(this.initialConfig, config));
//		Ext.ux.vardb.Refs.superclass.initComponent.apply(this, arguments);
//		
//		store.load({params: {start: 0, limit: this.pagesize}});
//	},

//
//	renderCount:function(value, p, record)
//	{
//		if (value<1)
//			{return '';}
//		return String.format('<a href="'+vardb.webapp+'/search/sequences.html?query=ref={1}">{0}</a>',
//			value, record.data.identifier);
//	}
});

