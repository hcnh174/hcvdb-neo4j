Ext.define('Hcv.store.Pages', {
	extend: 'Ext.data.Store',
	model: 'Hcv.model.Page',
	//autoLoad: true,
	
	proxy:
	{
		type: 'direct',
		directFn: hcvDirect.getPage
//		reader: {
//			root: 'records',
//			totalProperty: 'total'
//		}
	}
	
//	proxy: {
//		type: 'ajax',
//		url: '../../js/data/page.json',
//		reader: {
//			type: 'json',
//			root: 'root',
//			successProperty: 'success'
//		}
//	}
	
});
