Ext.define('Hcv.store.Users', {
    extend: 'Ext.data.Store',
    model: 'Hcv.model.User',
    autoLoad: true,
    
    /*
    data: [
        {name: 'Ed',    email: 'ed@sencha.com'},
        {name: 'Tommy', email: 'tommy@sencha.com'}
    ]
*/
    
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
