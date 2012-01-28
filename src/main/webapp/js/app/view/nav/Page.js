Ext.define('Hcv.view.nav.Page' ,{
	extend: 'Ext.view.View',
	alias: 'widget.page',
	layout: 'fit',
	store: 'Pages',
	title: 'Page',	
	tabConfig:
	{
		closable: true
	},
	
	initComponent: function() {

		this.itemTpl=new Ext.XTemplate(
			'<tpl for=".">',
				'<h1>{title}</h1>',
				'<p>{text}</p>',
			'</tpl>'
		);

		this.callParent(arguments);
		
		this.store.load({
			id: this.id,
			scope: this,
			callback: this.callback
		});
	}
	
//	loadPage: function(pageid, callback)
//	{
//		this.store.load({
//			id: pageid,
//			scope: this,
//			callback: callback
//		});
//	}
});
