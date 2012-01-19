Ext.application({
	name: 'Hcv',
	appFolder: 'app',
	autoCreateViewport: true,
	controllers:
	[
		'Pages',
		'Users',
		'Sequences'
	],
	launch: function()
	{
		//Ext.direct.Manager.addProvider(Ext.app.REMOTING_API);
		//Ext.QuickTips.init();
	}
});
