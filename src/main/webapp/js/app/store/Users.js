Ext.define('Hcv.store.Users', {
	extend: 'Ext.data.Store',
	model: 'Hcv.model.User',
	autoLoad: true,

	proxy: {
		type: 'ajax',
		url: '../../js/data/users.json',
		reader: {
			type: 'json',
			root: 'root',
			successProperty: 'success'
		}
	}
	
});
