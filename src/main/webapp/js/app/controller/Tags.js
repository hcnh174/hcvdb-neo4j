Ext.define('Hcv.controller.Tags', {
	extend: 'Ext.app.Controller',
	stores: ['Tags'],
	models: ['Tag'],
	views:
	[
		//'tags.Tags'
	],
	
	init: function() {

	}
	
	/*
	createTagCloud:function(grid)
	{
		var params={'list_id': grid.list_id, filter: grid.store.baseParams.filter};
		utils.ajaxRequest('/ajax/lists/tagcloud.json',params,function(tagcloud)
		{
			var dialog=new nelson.tags.TagCloud({tagcloud: tagcloud});
		});
	},
	
	untagItems:function(grid,callback)
	{
		var ids=grid.getSelectedIds();
		var total=ids.length;
		if (total===0)
		{
			Ext.MessageBox.alert('Warning','No sequences selected');
			return;
		}
		var message='Un-tag the selected sequences ('+total+')?';
		utils.ajaxRequestConfirm(message,'/tags/ajax/untag.json',{ids: ids.join(',')},callback);
	}
	*/
});

