Ext.define('Hcv.view.nav.Announcements' ,{
	extend: 'Ext.grid.GridPanel',
	alias : 'widget.announcements',
	title: 'Announcements',
	layout: 'fit',
	enableHdMenu: false,
	hideHeaders: true,
	stripeRows: true,
	frame: true,
	height: 200, //145,
	//width: '95%',
	loadingText: 'Loading announcements...',
	emptyText: 'No new announcements',
	iconCls: 'icon-rss',
	//itemSelector: 'someClass',
	//viewConfig: {forceFit: true},
	plugins:
	[
	 	{
			ptype: 'rowexpander',
			rowBodyTpl : '<p style="background-color: white">{description}</p>'
	 	}
	],
	
	initComponent:function()
	{		
		Ext.regModel('Announcement', {
		    fields:
			[
				{name: 'title'},
				{name: 'author'},
				{name: 'pubDate', type:'date'},
				{name: 'link'},
				{name: 'description'},
				{name: 'content'}
			]
		});

		this.store=new Ext.data.Store({
		    model: 'Announcement',
		    autoLoad: true,
		    proxy: {
		        type: 'ajax',
		        url : 'announcements.xml',
		        reader: {
			        type: 'xml',
			        root: 'channel',
			        record: 'item'
			    }
		    },		    
		    sorters:
	    	[
	    		{
	    			property: 'pubDate',
	    			direction: 'DESC'
	    		},
	    		{
	    			property: 'sequence',
	    			direction: 'DESC'
	    		}
	    	],
		});
		

		this.columns=
		[
			{header: "Title", sortable: true, dataIndex: 'title', flex: 1, renderer: this.renderTitle},
			{header: "Date", dataIndex: 'pubDate', width: 100, renderer: Ext.util.Format.dateRenderer('M j, Y')},
			{header: "Link", dataIndex: 'link', width: 50, renderer: this.renderLink}
		];
		
		this.dockedItems=
		[
	  		{
	  			xtype: 'pagingtoolbar',
	  			store: this.store,
	  			dock: 'top',
	  			displayInfo: true,
	  			pageSize: 1,
	  			displayMsg: 'Displaying news {0} - {1} of {2}',
				emptyMsg: "No news to display"
	  		}
  		];
		
		this.store.on('load', function(){
			//expander.expandRow(0);
		});

		this.callParent(arguments);
	},
	
	renderTitle:function(value, p, record)
	{
		return '<span style=\'color: #15428b; font:bold 11px tahoma,arial,sans-serif;\'>'+ record.data.title +'</span>';
	},

	renderLink:function(value, p, record)
	{
		var link = '' + value;
		if(link.length > 0)
			{return '<a href=\''+link+'\'>Link</a>';}
		return link;
	}
});
