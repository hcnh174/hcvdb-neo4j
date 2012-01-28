Ext.define('Hcv.view.nav.Toolbar' ,{
	extend: 'Ext.toolbar.Toolbar',
	alias : 'widget.navbar',
	height: 25,

	initComponent: function() {
		
		this.items=[
			this.createHomepageMenu(),'-',
			this.createTestMenu(),'-',
			this.createResourceMenu(),'-',
			this.createBlastMenu(),'-',
			this.createToolMenu(),'-',
			this.createUserMenu(),'-',
			this.createAdminMenu(),
			'->',
			this.createSearchSuggestions(),'-',
			this.createSearchButton()
		];		
		this.callParent(arguments);
	},

	
	createHomepageMenu:function()
	{
		return {text: 'Homepage', action: 'homepage'};
	},
	
	createTestMenu:function()
	{
		var menu=
		{
			text: 'Test',
			enableScrolling: false,
			menu:
			{
				items:
				[
					{text: 'Login', action: 'login'},
					{text: 'Feedback', action: 'feedback'},
					{text: 'Page', action: 'page'},
					{text: 'Term', action: 'term'},
					{text: 'Announcements',	action: 'announcements'},
					{text: 'Refs', action: 'refs'},
					{text: 'Load genbank file',	action: 'loadgenbankfile'},
					{text: 'Clear database', action: 'cleardb'},
					{text: 'Ref', action: 'ref'},
					{text: 'Sequence', action: 'sequence'},
					{text: 'Get taxids', action: 'gettaxids'},
					{text: 'Get refids', action: 'getrefids'},
					{text: 'Get taxa', action: 'gettaxa'}
				]
			}
		};
		return menu;
	},
		
	createResourceMenu:function()
	{
		var menu=
		{
			text: 'Resources',
			enableScrolling: false,
			menu:
			{
				items:
				[
					{text: 'Pathogens', action: ''},
					{text: 'Families', action: ''},
					{text: 'Diseases', action: ''},
					{text: 'Pfam motifs', action: ''},
					{text: 'Structures', action: ''},
					{text: 'Genomes', action: ''},
					{text: 'Map', action: ''},
					{text: 'Alignments', action: ''},
					{text: 'Clinical data', action: ''},
					'-',
					{text: 'Tutorials', action: ''},
					{text: 'Antigenic variation', action: ''},
					{text: 'Database construction', action: ''},
					{text: 'Terms', action: ''},
					{text: 'Links', action: ''},
					{text: 'Images', action: ''},
					{text: 'References', action: ''}
				]
			}
		};
		return menu;
	},
	
	createBlastMenu:function()
	{	
		var menu=
		{
			text: 'BLAST',
			menu:
			{
				items:
				[
					{text: 'PSI', action: ''},
					{text: 'PSI-BLAST', action: ''},
					{text: 'PHI-BLAST', action: ''},
					{text: 'Netblast', action: ''}
				]
			}
		};
		return menu;
	},
	
	createToolMenu:function()
	{
		var menu=
		{
			text: 'Tools',
			menu:
			{
				items:
				[
					{text: 'Search sequences', action: ''},
					{text: 'PROSITE/regex search', action: ''},
					{text: 'MAFFT alignment tool', action: ''},
					{text: 'Alignment viewer', action: ''},
					{text: 'Create a codon alignment', action: ''},
					{text: 'Gblocks', action: ''},
					{text: 'Analyze variability', action: ''}
				]
			}
		};
		return menu;
	},
	
	createUserMenu:function()
	{
		var items=[];
		if (this.anonymous)
		{
			items.push({text: 'New account', action: ''}),
			items.push({text: 'Login', action: ''});
		}
		else
		{
			items.push({text: 'Logout', action: ''});
			items.push({text: 'Edit user information', action: ''});
			//items.push({text: 'User homepage', action: ''});
			items.push({text: 'Change password', action: ''});
			items.push({text: 'Contact us', action: ''});
		}
		items.push('-');
		items.push({text: 'Explorer', action: ''});
		items.push('-');
		items.push({text: 'My searches', action: ''});
		items.push({text: 'My analyses', action: ''});
		items.push({text: 'My sequences', action: ''});
		items.push({text: 'My alignments', action: ''});
		
		var menu=
		{
			text: 'User',
			menu: {items: items}
		};
		return menu;
	},
	
	createAdminMenu:function()
	{
		if (!this.admin)
			{return '';}
		var menu=
		{
			text: 'Admin',
			menu:
			{			
				items:
				[
					{text: 'Admin page', action: ''},
					{text: 'Update XML', action: ''},
					{text: 'Update sequences', action: ''},
					{text: 'Update counts', action: ''},
					{text: 'Clear cache', action: ''},
					{text: 'Users', action: ''}
				]
			}
		};
		return menu;
	},
	
	///////////////////////////////////////////////////
	
	createSearchSuggestions:function()
	{
		Ext.define('Hcv.model.Suggestion', {
			extend : 'Ext.data.Model',
			fields:
			[
				{name: 'keyword', mapping: 'keyword'},
				{name: 'type', mapping: 'type'},
				{name: 'identifier', mapping: 'identifier'}
			],
			proxy : {
				type: 'direct',
				directFn: hcvDirect.getSuggestions,
				reader: {
					root: 'result',
					//totalProperty: 'total'
					idProperty: 'keyword'
				}
			}
		});

		var store = Ext.create('Ext.data.Store', {
			//autoLoad: true,
			model : 'Hcv.model.Suggestion',
			baseParams: {limit:20}
		});

		var combo = Ext.create('Ext.form.field.ComboBox', {
			fieldLabel: '',
			displayField: 'keyword',
			valueField: 'identifier',
			//width: 200,
			//labelWidth: 130,
			store: store,
			queryMode: 'remote',
			typeAhead: true,
			itemId: 'searchtextbox',
			loadingText: 'Searching...',
			width: 150, //width: 100,
			listWidth: 200,
			hideTrigger: true,
			emptyText: 'Search...',
			queryDelay: 800,
			forceSelection: false
		});
		return combo;
	},
	
	createSearchButton:function()
	{
		var button= {
			xtype: 'button',
			text: 'Go',
			width: 32,
			scope: this,
			action: 'submit',
			handler: function()
			{
				alert('search');
			}
		};
		return button;
	}
});
