Ext.define('Hcv.store.Refs', {
	extend: 'Ext.data.Store',
	model: 'Hcv.model.Ref',
	autoLoad: true,
	remoteSort: true,
	pageSize: 200,
	buffered: true,
	sorters:
	[
		{
			property: 'authors',
			direction: 'ASC'
		},
		{
			property: 'year',
			direction: 'ASC'
		}
	],
		
	proxy:
	{
		type: 'direct',
		directFn: hcvDirect.getRefs
//		reader: {
//			root: 'records',
//			totalProperty: 'total'
//		}
	}
});
