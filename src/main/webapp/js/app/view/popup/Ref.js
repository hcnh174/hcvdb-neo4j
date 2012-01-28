Ext.define('Hcv.view.popup.Ref', {
	//extend: 'Ext.window.Window',
	extend: 'Hcv.view.popup.AbstractPopup',
	alias : 'widget.refpopup',

	title : 'Definition',
//	layout: 'fit',
//	autoShow: true,
//	width: 600,
//	height: 360,
//	plain: true,
//	closable: true,
//	resizable: true,

	initComponent:function()
	{
		this.title='Reference: '+this.data.name;
		
		var tabs=[
			this.createPropertyTab(this.data),
			this.createAbstractTab(this.data),
			this.createCitationTab(this.data)
			//this.createSequenceTab(this.data)
		];		
		this.items=this.createTabPanel(tabs);
//	
//		this.buttons=
//		[
//			{
//				text: 'Close',
//				scope: this,
//				handler: function(){this.hide();}
//			}
//		];
//		
		this.callParent(arguments);
	},
		
	createPropertyTab:function(data)
	{
		var fields=[];
		this.addField(fields,data,'Identifier','identifier');
		this.addField(fields,data,'Name','name');
		this.addField(fields,data,'Type','type');
		this.addField(fields,data,'Pubmed ID','pmid',this.renderPmid);
		this.addField(fields,data,'Authors','authors');
		this.addField(fields,data,'Year','year');
		this.addField(fields,data,'Title','title');
		this.addField(fields,data,'Journal','journal');
		this.addField(fields,data,'Volume','volume');
		this.addField(fields,data,'Pages','pages');
		this.addField(fields,data,'Publisher','publisher');
		this.addField(fields,data,'City','city');
		this.addField(fields,data,'Abbreviation','abbreviation');
		return this.createPropertyGrid(fields,data);
	},
	
	renderPmid:function(value, p, r)
	{
		return String.format('<a href="http://www.ncbi.nlm.nih.gov/entrez/query.fcgi?db=pubmed&cmd=Retrieve&dopt=AbstractPlus&list_uids={0}&query_hl=22&itool=pubmed_docsum" target="_blank">{0}</a>',value);
	},
	
	createAbstractTab:function(reference)
	{
		var tab = {
			title: 'Abstract',
			html: reference.abstrct,
			autoScroll: true
		}
		return tab;
	},
	
	createCitationTab:function(reference)
	{
		var tab = {
			title: 'Citation',
			html :reference.citation,
			autoScroll: true
		};
		return tab;
	}
	
	/*
	createSequenceTab:function(data)
	{
		var grid=new Ext.ux.vardb.SequenceGrid(
		{
			resource: {type: 'REF', id: data.id},
			filter: 'ref='+data.identifier,
			pagesize: 10
		});
		return grid;
	},
	*/
//	
//	////////////////////////////////////////////////////////////////////////
//	
//	createTabPanel: function(tabs)
//	{
//		var tabpanel={
//			xtype: 'tabpanel',
//			activeTab: 0,
//			autoScroll: true,
//			defaults: {layout:'fit'},
//			//border: false,
//			items: tabs
//		};
//		return tabpanel;
//	},
//	
//	addField:function(fields,data,header,dataIndex,renderer)
//	{
//		if (!data[dataIndex] || data[dataIndex]==='')
//			{return;}
//		var field={dataIndex: dataIndex, header: header};
//		if (renderer)
//			{field.renderer=renderer;}
//		fields.push(field);
//	},
//
//	createPropertyGrid:function(fields,data)
//	{
//		var grid=new Ext.grid.property.Grid({
//			title: 'Properties',
//			autoHeight: true,
//			disableSelection: true,
//			stripeRows: true,
//			editable: false,
//			nameWidth: 100,
//			fields: fields,
//			source: data
//		});
//		return grid;
//	}
});
