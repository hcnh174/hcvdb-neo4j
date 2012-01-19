Ext.define('Hcv.view.nav.Page' ,{
	extend: 'Ext.view.View',
    alias: 'widget.page',
    layout: 'fit',
    store: 'Pages',
    //id: 'centerPanel',
    
    initComponent: function() {

    	this.itemTpl=new Ext.XTemplate(
		    '<tpl for=".">',
		        '<h1>{title} ({id})</h1>',
		        '<p>{text}</p>',
		    '</tpl>'
		);
        this.callParent(arguments);
    },
    
    loadPage: function(pageid)
    {
		this.store.load({
			id: pageid,
			scope   : this,
		    callback: function(records, operation, success) {
		        console.log(records);
		        var container=Ext.getCmp('centerContainer');
				var panel=Ext.getCmp('centerPanel');
		        container.remove(panel,true);
		        container.add(this);
		        container.doLayout();
		    }
		});
    }
});
