Ext.define('Hcv.store.Tags', {
	extend: 'Ext.data.Store',
	model: 'Hcv.model.Tag',
	//autoLoad: true,
	remoteSort: true,
	pageSize: 200,
	buffered: true,
	sorters:
	[
		{
			property: 'name',
			direction: 'ASC'
		}
	],
		
	proxy:
	{
		type: 'direct',
		directFn: hcvDirect.getTags,
		reader: {
			root: 'records',
			totalProperty: 'total'
		}
	}
});
