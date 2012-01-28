Ext.define('Hcv.view.HcvPanel' ,{
	extend: 'Ext.panel.Panel',
	alias : 'widget.hcvpanel',
	title: 'Hepatitis C virus',
	items:
	[
		{
			xtype: 'navbar'
		},
		{
			xtype: 'tabpanel',
			activeTab: 0,
			items:
			[
				{
					xtype: 'sequencelist'
				}
				/*
				{
					xtype: 'container',
					layout: 'border',
					title: 'Page',
					items:
					[
						{
							xtype: 'navpanel',
							region: 'west',
							collapsible: false,
							title: 'Navigation',
							width: 150
						}
					]
				}
				*/
			]
		}
	]
});