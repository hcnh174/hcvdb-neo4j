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
	
	init: function() {
		this.control({
			'navpanel': {
				itemclick: function(view, record, item, index, e) {
					alert(record.get('text'));
				}
			}
		});
	}
});

