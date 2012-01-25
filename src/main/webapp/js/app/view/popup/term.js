Ext.define('Hcv.view.popup.Term', {
	extend: 'Ext.window.Window',
	alias : 'widget.termpopup',

	title : 'Definition',
	layout: 'fit',
	autoShow: true,
	width: 400,
	height: 250,
	plain: true,

	initComponent:function()
	{
		this.items=[
			{
				xtype: 'tabpanel',
				activeTab: 0,
				//deferredRender: false,
				border: false,
				items:
				[
					{
						title: 'term',
						html: 'definition',
						autoScroll: true
					}
				]
			}
		];
	
		this.buttons=
		[
			{
				text: 'Close',
				scope: this,
				handler: function(){this.hide();}
			}
		];
		
		this.callParent(arguments);
	}
});
