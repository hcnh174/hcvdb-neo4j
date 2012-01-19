Ext.define('Hcv.view.nav.Page' ,{
	extend: 'Ext.view.View',
    alias: 'widget.page',
    layout: 'fit',
    store: 'Pages',
    
    initComponent: function() {

    	this.itemTpl=new Ext.XTemplate(
		    '<tpl for=".">',
		        '<h1>{title} ({id})</h1>',
		        '<p>{text}</p>',
		    '</tpl>'
		);
        this.callParent(arguments);
    }
});
