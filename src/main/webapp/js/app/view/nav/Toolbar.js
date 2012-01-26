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
//			this.createSearchSelect(),
//			this.createSearchTextBox(),'-',
//			this.createSearchButton()
//			{
//				xtype: 'textfield',
//				name: 'field1',
//				emptyText: 'enter search term'
//			},
			this.createSearchSuggestions(),'-',
			this.createSearchButton()
		];		
		this.callParent(arguments);
	},
	
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
			autoLoad: true,
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
	},
	
	createHomepageMenu:function()
	{
		var menu=
		{
			text: 'Homepage', handler: function(){alert('/homepage.html');}
		};
		return menu;
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
					{
						text: 'Login',
						handler: function()
						{
							var view = Ext.widget('login');
						}
					},
					{
						text: 'NewUser',
						handler: function()
						{
							var view = Ext.widget('newuser');
						}
					},
					{
						text: 'Feedback',
						handler: function()
						{
							var view = Ext.widget('feedback');
						}
					},
					{
						text: 'Direct',
						handler: function()
						{
							hcvDirect.multiply(2, function(result)
							{
								Ext.MessageBox.alert("Result", result);
							});
						}
					},
					{
						text: 'Page',
						//action: 'page',
						scope: this,
						handler: function()
						{
							console.log('trying to update page');
							//Hcv.util.Util.clearExtjsComponent(panel);
							var view = Ext.widget('page',{pageid: 'welcome'});
							view.loadPage(1);					
						}
					},
					{
						text: 'Term',
						scope: this,
						handler: function()
						{
							console.log('trying to update page');
							var view = Ext.widget('termpopup',{term: 'hepatitis'});
						}
					},
					{
						text: 'Announcements',
						action: 'announcements'
//						handler: function()
//						{
//							var view = Ext.widget('announcements');
//							var container=Ext.getCmp('centerContainer');
//							var panel=Ext.getCmp('centerPanel');
//							container.remove(panel,true);
//							container.add(view);
//							container.doLayout();
//						}
					},
					{
						text: 'Load genbank file',
						handler: function()
						{
							var filename='h:/hcvdatabase.etc/sequence.gb';
							hcvDirect.loadGenbankFile(filename,function(result)
							{
								Ext.MessageBox.alert("loaded genbank data: ", result);
							});
						}
					},
					{
						text: 'Get taxids',
						handler: function()
						{
							hcvDirect.getTaxids(function(result)
							{
								Ext.MessageBox.alert("got taxids: ", result);
							});
						}
					},
					{
						text: 'Get refids',
						handler: function()
						{
							hcvDirect.getRefids(function(result)
							{
								Ext.MessageBox.alert("got refids: ", result);
							});
						}
					},
					{
						text: 'Refs',
						action: 'refs',
						handler: function()
						{
							//var view = Ext.widget('refs');
//							var container=Ext.getCmp('centerContainer');
//							var panel=Ext.getCmp('centerPanel');
//							container.remove(panel,true);
//							container.add(view);
//							container.doLayout();		
							/*
							hcvDirect.getRefs(function(result)
							{
								Ext.MessageBox.alert("Result", result);
							});
							*/
						}
					},
					{
						text: 'Get taxids',
						handler: function()
						{
							hcvDirect.getTaxa(function(result)
							{
								Ext.MessageBox.alert("got taxa",result);
							});
						}
					},
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
					{text: 'Pathogens', handler: function(){alert('/pathogens.html');}},
					{text: 'Families', handler: function(){alert('/families.html');}},
					{text: 'Diseases', handler: function(){alert('/diseases.html');}},
					{text: 'Pfam motifs', handler: function(){alert('/pfams.html');}},
					{text: 'Structures', handler: function(){alert('/structures.html');}},
					{text: 'Genomes', handler: function(){alert('/genomes.html');}},
					{text: 'Map', handler: function(){alert('/search/map.html');}},
					{text: 'Alignments', handler: function(){alert('/alignments.html');}},
					{text: 'Clinical data', handler: function(){alert('/bundles.html');}},
					'-',
					{text: 'Tutorials', handler: function(){alert('/tutorials.html');}},
					{text: 'Antigenic variation', handler: function(){alert('/antigenicvariation.html');}},
					{text: 'Database construction', handler: function(){alert('/database.html');}},
					{text: 'Terms', handler: function(){alert('/terms.html');}},
					{text: 'Links', handler: function(){alert('/links.html');}},
					{text: 'Images', handler: function(){alert('/images.html');}},
					{text: 'References', handler: function(){alert('/references.html');}}
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
					{text: 'PSI', handler: function(){alert('/blast/blast.html');}},
					{text: 'PSI-BLAST', handler: function(){alert('/blast/psiblast.html');}},
					{text: 'PHI-BLAST', handler: function(){alert('/blast/phiblast.html');}},
					{text: 'Netblast', handler: function(){alert('/analysis/netblast.html');}}
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
					{text: 'Search sequences', handler: function(){alert('/search/sequences.html');}},
					{text: 'PROSITE/regex search', handler: function(){alert('/regex/search.html');}},
					{text: 'MAFFT alignment tool', handler: function(){alert('/mafft.html');}},
					{text: 'Alignment viewer', handler: function(){alert('/alignments/view.html');}},
					{text: 'Create a codon alignment', handler: function(){alert('/analysis/codonalign.html');}},
					{text: 'Gblocks', handler: function(){alert('/analysis/gblocks.html');}},
					{text: 'Analyze variability', handler: function(){alert('/analysis/variability.html');}}
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
			items.push({text: 'New account', handler: function(){alert('/newuser.html');}});
			items.push({text: 'Login', handler: function(){alert('/login.html');}});
		}
		else
		{
			items.push({text: 'Logout', handler: function(){alert('/logout.html');}});
			items.push({text: 'Edit user information', handler: function(){alert('/edituser.html');}});
			//items.push({text: 'User homepage', handler: function(){alert('/user.html');}});
			items.push({text: 'Change password', handler: function(){alert('/changepassword.html');}});
			items.push({text: 'Contact us', handler: function(){alert('/contact.html');}});
		}
		items.push('-');
		items.push({text: 'Explorer', handler: function(){alert('/explorer.html');}});
		items.push('-');
		items.push({text: 'My searches', handler: function(){alert('/user/searches.html');}});
		items.push({text: 'My analyses', handler: function(){alert('/user/analyses.html');}});
		items.push({text: 'My sequences', handler: function(){alert('/user/sequences.html');}});
		items.push({text: 'My alignments', handler: function(){alert('/user/alignments.html');}});	
		
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
					{text: 'Admin page', handler: function(){alert('/admin/index.html');}},
					{text: 'Update XML', handler: function(){alert('/admin/setup.html');}},
					{text: 'Update sequences', handler: function(){alert('/admin/load/table.html');}},
					{text: 'Update counts', handler: function(){alert('/admin/update/counts.html');}},
					{text: 'Clear cache', handler: function(){alert('/admin/cache/clear.html');}},
					{text: 'Users', handler: function(){alert('/admin/users.html');}}
				]
			}
		};
		return menu;
	}
});
