Ext.define('Hcv.view.Viewport', {
	extend: 'Ext.container.Viewport',
	layout: 'border',
	items:
	[
		{
			xtype: 'container',
			region: 'center',
			id: 'centerContainer',
			layout: 'fit',
			items:
			[
				{
					xtype: 'page',
					pagetitle: 'Welcome',
					pagetext: 'This is a database'
				}
			]
		},
		{
			region: 'north',
			autoHeight: true,
			border: false,
			//margins: '0 0 5 0',
			layout: 'fit',
			items:
			[
				{
					html: '<table border="0"><tr>'+
					'<td><img src="../../images/logo.png" width="100" height="100"/></td>'+
					'<td><h1 class="x-panel-header">Hiroshima Hepatitis C Virus Database</h1></td>'+
					'</tr></table>'
				},
				{
					xtype: 'navbar',
					height: 25
				}
			]
		},
		{
			region: 'south',
			//title: 'South Panel',
			collapsible: false,
			html: '<a href="http://home.hiroshima-u.ac.jp/naika1/">Hiroshima University</a>',
			split: false,
			height: 25
		},
		{
			region: 'west',
			collapsible: false,
			// title: 'Navigation',
			width: 150,
			html: 'Tree'
		},
		{
			region: 'east',
			collapsible: true,
			title: 'Navigation',
			width: 150,
			html: 'Links'
		}
	]
});
