Ext.define('Hcv.store.Sequences', {
	extend: 'Ext.data.Store',
	model: 'Hcv.model.Sequence',
	//autoLoad: true,
	remoteSort: true,
	pageSize: 50,
	//buffered: true,
	autoDestroy : true,
	autoLoad : {
		start : 0,
		limit : 50
	},
	
	sorters:
	[
		{
			property: 'accession',
			direction: 'ASC'
		}
	],
		
	proxy:
	{
		type: 'direct',
		directFn: hcvDirect.getSequencesWithPaging,
//		api : {
//			read : person4Action.loadWithPaging,
//			create : person4Action.create,
//			update : person4Action.update,
//			destroy : person4Action.destroy
//		},
		reader: {
			root: 'records',
			totalProperty: 'total'
		}
	}
});
