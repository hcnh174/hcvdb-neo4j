Ext.define('Hcv.view.nav.Page' ,{
    //extend: 'Ext.panel.Panel',
	extend: 'Ext.view.View',
    alias: 'widget.page',
    layout: 'fit',
    store: 'Pages',
    
    initComponent: function() {
        //this.html='<h1>'+this.pagetitle+'</h1><p>'+this.pagetext+'</p>';
    	
    	this.itemTpl=new Ext.XTemplate(
		    '<tpl for=".">',
		        '<h1>{title} ({id})</h1>',
		        '<p>{text}</p>',
		    '</tpl>'
		);
        this.callParent(arguments);
    }
});
