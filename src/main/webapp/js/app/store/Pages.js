Ext.define('Hcv.store.Pages', {
    extend: 'Ext.data.Store',
    model: 'Hcv.model.Page',
    autoLoad: true,
    
    /*
    data: [
        {name: 'Ed',    email: 'ed@sencha.com'},
        {name: 'Tommy', email: 'tommy@sencha.com'}
    ]
*/
    
	proxy: {
	    type: 'ajax',
	    url: '../../js/data/page.json',
	    reader: {
	        type: 'json',
	        root: 'root',
	        successProperty: 'success'
	    }
	}
	
});
