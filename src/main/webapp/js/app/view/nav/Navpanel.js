//Ext.define('Hcv.view.nav.Navpanel' ,{
//	extend: 'Ext.Component',
//	alias : 'widget.navpanel',
//	layout: 'fit',
//
//	initComponent: function() {
//		
//
//		//this.itemTpl=new Ext.XTemplate(
//		this.html='<ul>'+
//		        '<li>link1</li>'+
//		        '<li>link2</li>'+
//		        '<li>link3</li>'+
//		        '</ul>';
//		this.callParent(arguments);
//	}
//});

Ext.define('Hcv.view.nav.Navpanel' ,{
	extend: 'Ext.tree.Panel',
	alias : 'widget.navpanel',
    root: {
        text: 'Root',
        expanded: true,
        children: [
            {
                text: 'Child 1',
                leaf: true
            },
            {
                text: 'Child 2',
                leaf: true
            },
            {
                text: 'Child 3',
                expanded: true,
                children: [
                    {
                        text: 'Grandchild',
                        leaf: true
                    }
                ]
            }
        ]
    }
});
