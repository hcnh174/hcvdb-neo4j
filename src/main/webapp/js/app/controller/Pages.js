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
					this.addTab(view);
				}
			},
			'navbar menuitem[action=announcements]': {
				click: function()
				{
					var view = Ext.widget('announcements');
					this.addTab(view);
				}
			},
			'navbar menuitem[action=page]': {
				click: function()
				{
					var view = Ext.widget('page',{pageid: 'welcome'});
					var self=this;
					view.loadPage(1, function(){
						self.addTab(view);
					});
				}
			}
		});
	},
	
	addTab: function(tab)
	{
		this.getHcvtabpanel().add(tab);
		this.getHcvtabpanel().setActiveTab(tab);
	}
});

