Ext.define('Hcv.view.nav.Eastpanel' ,{
	extend: 'Ext.panel.Panel',
	alias : 'widget.eastpanel',
	title: 'Accordion Layout',
    layout:'accordion',
    defaults: {
        bodyStyle: 'padding:15px'
    },
    layoutConfig: {
        titleCollapse: false,
        animate: true
    },
    items: [{
        title: 'Panel 1',
        html: 'Panel content!'
    },{
        title: 'Panel 2',
        html: 'Panel content!'
    },{
        title: 'Panel 3',
        html: 'Panel content!'
    }],
});
