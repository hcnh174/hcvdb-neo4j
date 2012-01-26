Ext.define('Hcv.controller.Pages', {
	extend: 'Ext.app.Controller',
	stores: ['Pages','Refs'],
	models: ['Page','Ref'],
	views:
	[
		'nav.Toolbar',
		'nav.Page',
		'nav.Navpanel'
	],
	refs:
	[
		{
		    ref: 'hcvtabpanel',
		    selector: 'viewport hcvpanel tabpanel'
		}
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
					//var tabpanel = Ext.ComponentQuery.query('viewport hcvpanel tabpanel')[0];
					this.getHcvtabpanel().add(view);
					this.getHcvtabpanel().setActiveTab(view);
				}
			},
			'navbar menuitem[action=announcements]': {
				click: function()
				{
					var view = Ext.widget('announcements');
					this.getHcvtabpanel().add(view);
					this.getHcvtabpanel().setActiveTab(view);
				}
			}			
		});
	}
});

