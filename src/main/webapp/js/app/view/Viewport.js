Ext.define('Hcv.view.Viewport', {
	extend: 'Ext.container.Viewport',
	layout: 'border', //fit
	items:
	[
		{
			xtype: 'userlist',
			region: 'center'
		},
		/*
		{
			region: 'north',
			html: '<table border="0"><tr>'+
				'<td><img src="../../images/logo.png" width="100" height="100"/></td>'+
				'<td><h1 class="x-panel-header">Hiroshima Hepatitis C Virus Database</h1></td>'+
				'</tr></table>',
			autoHeight: true,
			border: false,
			margins: '0 0 5 0'
		},
		*/
		{
			region: 'north',
			autoHeight: true,
			border: false,
			margins: '0 0 5 0',
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
			 		xtype: 'toolbar',
			 		width: 1000,
			 	    items: [
			 	        {
			 	            text: 'Button'
			 	        },
			 	        {
			 	            xtype: 'splitbutton',
			 	            text : 'Split Button'
			 	        },
			 	        '->',
			 	        {
			 	            xtype    : 'textfield',
			 	            name     : 'field1',
			 	            emptyText: 'enter search term'
			 	        }
			 	      ]
			 	}
			]
		},
		{
			region: 'south',
	        //title: 'South Panel',
	        collapsible: false,
	        html: '<a href="http://home.hiroshima-u.ac.jp/naika1/">Hiroshima University</a>',
	        split: false,
	        height: 25,
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
