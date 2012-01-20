Ext.define('Hcv.store.Sequences', {
	extend: 'Ext.data.Store',
	model: 'Hcv.model.Sequence',
	autoLoad: true,
	remoteSort: true,
	pageSize: 200,
	buffered: true,
	sorters:
	[
		{
			property: 'accession',
			direction: 'ASC'
		},
		{
			property: 'sequence',
			direction: 'DESC'
		}
	],
		
	proxy:
	{
		type: 'direct',
		directFn: hcvDirect.getSequences,
		reader: {
			root: 'records',
			totalProperty: 'total'
		}
	}
	
//	proxy: {
//		type: 'ajax',
//		url: '../../js/data/sequences.json',
//		reader: {
//			type: 'json',
//			root: 'root',
//			successProperty: 'success'
//		}
//	}
});
