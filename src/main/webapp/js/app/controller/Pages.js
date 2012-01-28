Ext.define('Hcv.controller.Pages', {
	extend: 'Ext.app.Controller',
	stores: ['Pages','Refs'],
	models: ['Page','Ref'],
	views:
	[
		'nav.Toolbar',
		'nav.Page',
		'nav.Navpanel'
	],
	refs:
	[
		{
		    ref: 'hcvtabpanel',
		    selector: 'viewport hcvpanel tabpanel'
		}
	],
	
	init: function() {
		this.control({
			'navpanel': {
				itemclick: function(view, record, item, index, e) {
					alert(record.get('text'));
				}
			},			
			'navbar menuitem[action=refs]': {
				click: function()
				{
					this.addTab(Ext.widget('refs'));
				}
			},
			'navbar menuitem[action=announcements]': {
				click: function()
				{
					this.addTab(Ext.widget('announcements'));
				}
			},
			'navbar menuitem[action=page]': {
				click: function()
				{
					var self=this;
					var view = Ext.widget('page',{
						id: 'welcome',
						callback: function(){
							self.addTab(view);
						}
					});
				}
			},
			'navbar menuitem[action=feedback]': {
				click: function()
				{
					Ext.widget('feedback');
				}
			},
			'navbar menuitem[action=login]': {
				click: function()
				{
					Ext.widget('login');
				}
			},
			'navbar menuitem[action=term]': {
				click: function()
				{
					alert(arguments);
					Ext.widget('termpopup',{term: 'hepatitis'});
				}
			},
			'navbar menuitem[action=loadgenbankfile]': {
				click: function()
				{
					var filename='d:/temp/sequence.gb';
					hcvDirect.loadGenbankFile(filename,function(result)
					{
						Ext.MessageBox.alert("loaded genbank data: ", result);
					});
				}
			},
			'navbar menuitem[action=cleardb]': {
				click: function()
				{
					hcvDirect.clearDatabase(function(result)
					{
						Ext.MessageBox.alert("database cleared",result);
					});
				}
			},
			'navbar menuitem[action=ref]': {
				click: function()
				{
					Ext.widget('refpopup',{data: {pmid: 12345}});
				}
			},
			'navbar menuitem[action=sequence]': {
				click: function()
				{
					Ext.widget('sequencepopup',{data: {accession: 'DEF456'}});
				}
			},
			'navbar menuitem[action=gettaxids]': {
				click: function()
				{
					hcvDirect.getTaxids(function(result)
					{
						Ext.MessageBox.alert("got taxids: ", result);
					});
				}
			},
			'navbar menuitem[action=getrefids]': {
				click: function()
				{
					hcvDirect.getRefids(function(result)
					{
						Ext.MessageBox.alert("got refids: ", result);
					});
				}
			},
			'navbar menuitem[action=gettaxa]': {
				click: function()
				{
					hcvDirect.getTaxa(function(result)
					{
						Ext.MessageBox.alert("got taxa",result);
					});
				}
			}
		});
	},
	
	addTab: function(tab)
	{
		this.getHcvtabpanel().add(tab);
		this.getHcvtabpanel().setActiveTab(tab);
	}
});

