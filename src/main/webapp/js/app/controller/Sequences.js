Ext.define('Hcv.controller.Sequences', {
	extend: 'Ext.app.Controller',
	stores: ['Sequences'],    
	models: ['Sequence'],
	views:
	[
		'sequence.List'
	],
	
	init: function() {
		/*
		this.control({
			'useredit button[action=save]': {
				click: this.updatePage
			}
		});
		*/
	}

	/*
	updatePage: function()
	{
		console.log('trying to update page');
		var panel=Ext.comp('mainContainer');
		this.clearExtjsComponent(panel);
		var view = Ext.widget('page');
		panel.add(view);
		panel.doLayout();
	},

	clearExtjsComponent: function(cmp) {
        var f;
        while(f = cmp.items.first()){
            cmp.remove(f, true);
        }
    }
    */
});

