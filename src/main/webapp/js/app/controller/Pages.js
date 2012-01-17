Ext.define('Hcv.controller.Pages', {
	extend: 'Ext.app.Controller',
	//stores: ['Users'],    
	//models: ['User'],
	views:
	[
		'nav.Toolbar'
	],
	
	init: function() {
		this.control({
//			'useredit button[action=save]': {
//				click: this.updateUser
//			}
		});
	}
});
