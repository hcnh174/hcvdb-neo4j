Ext.define('Hcv.view.Viewport', {
	extend: 'Ext.container.Viewport',
	layout: 'border',	
	items:
	[
		{
			region: 'north',
			autoHeight: true,
			border: false,
			layout: 'fit',
			items: {xtype: 'header'}
		},
		{
			xtype: 'tabpanel',
			region: 'center',
			activeTab: 0,
			plain: true,
			tabBar: {style: 'background-color: white'},
			items:
			[
				{xtype: 'hcvpanel'},
				{xtype: 'hbvpanel'}
			]
		}
	]
});
