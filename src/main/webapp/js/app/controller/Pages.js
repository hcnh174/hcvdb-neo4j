Ext.define('Hcv.controller.Pages', {
	extend: 'Ext.app.Controller',
	stores: ['Pages'],	
	models: ['Page'],
	views:
	[
		'nav.Toolbar',
		'nav.Page',
		'nav.Navpanel'
	],
	refs:
	[
	],
	
	init: function() {
		this.control({
			'navpanel': {
				itemclick: function(view, record, item, index, e) {
					alert(record.get('text'));
				}
			},			
			'navbar menuitem[action=refs]': {
				click: function()
				{
					var view = Ext.widget('refs');
					var tabpanel = Ext.ComponentQuery.query('viewport hcvpanel tabpanel')[0];
					tabpanel.add(view);
					tabpanel.setActiveTab(view);
				}
			}
		});
	}
});

