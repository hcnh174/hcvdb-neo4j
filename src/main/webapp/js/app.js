Ext.application({
	name: 'Hcv',
	appFolder: 'app',
	autoCreateViewport: true,
	controllers:
	[
		'Pages',
		'Users',
		'Sequences',
		'Tags'
	],
	launch: function()
	{
		//Ext.QuickTips.init();
	}
});
