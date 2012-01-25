Ext.define('Hcv.controller.Sequences', {
	extend: 'Ext.app.Controller',
	stores: ['Sequences'],	
	models: ['Sequence'],
	views:
	[
		'sequence.List'
	],
	
	init: function() {
		this.control({
			'viewport > sequencelist': {
				itemdblclick: this.editSequence
			},
			'useredit button[action=save]': {
				click: this.updateSequence
			}
		});
	},

	editSequence: function(grid, record) {
		console.log('Double clicked on ' + record.get('accession'));
		var view = Ext.widget('useredit');
		view.down('form').loadRecord(record);
	},
	
	updateSequence: function(button) {
		console.log('clicked the Save button');
		var win	= button.up('window'),
			form   = win.down('form'),
			record = form.getRecord(),
			values = form.getValues();
			
		record.set(values);
		win.close();
		this.getUsersStore().sync();
	}
});

