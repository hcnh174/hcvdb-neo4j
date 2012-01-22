Ext.define('Hcv.view.tags.TagCloud', {
    extend: 'Ext.window.Window',
    alias : 'widget.tagcloud',
	layout: 'fit',
	width: 600,
	height: 400,
	title: 'Tag clouds',
	autoShow: true,
	autoScroll: true,
	maximizable: true,
	bodyStyle: 'background-color: white; padding: 3px',
	
	initComponent:function()
	{
//		var buffer=[], facet, tag, i, j;
//		for (i=0;i<this.tagcloud.facets.length;i++)
//		{
//			facet=this.tagcloud.facets[i];
//			if (facet.tags.length>0)
//				{buffer.push(this.formatFacet(facet));}
//		}		
//		var html=buffer.join('\n');
	
		this.html='tag cloud'
		this.buttons=[
			{
				text: 'Close',
				scope: this,
				handler: function(){this.hide();}
			}
		];
		this.callParent(arguments);
	},	
	
	formatFacet:function(facet)
	{
		var tags=[], tag, index, html;
		for (index=0;index<facet.tags.length;index++)
		{
			tag=facet.tags[index];
			html='<a href="#" onclick="'+this.formatLink(tag)+'" style="font-size:'+tag.fontsize+'pt;text-decoration:none;">';
			html+=tag.name.replace(' ','&nbsp;')+'&nbsp;('+tag.numsequences+')';
			html+='</a>';
			tags.push(html);
		}
		var buffer=[];
		buffer.push('<h2 style="color:#3764A0;border-bottom:2px solid #99BBE8;">'+facet.name+'&nbsp;('+facet.tags.length+' tags)</h2>');
		buffer.push(tags.join('&nbsp; '));
		buffer.push('<br/><br/>');
		return buffer.join('\n');
	},
	
	formatLink:function(tag)
	{	
		return 'nelson.popup(\''+tag.resourceType+'\',\''+tag.identifier+'\')';
	}
});
