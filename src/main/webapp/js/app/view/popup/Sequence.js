Ext.define('Hcv.view.popup.Sequence', {
	extend: 'Hcv.view.popup.AbstractPopup',
	alias : 'widget.sequencepopup',
	//title : 'Sequence',
	
	initComponent:function()
	{
		this.data={
			accession: 'ABC123'	
		};
		
		this.title='Sequence: '+this.data.accession;
		
		var tabs=[];		
		tabs.push(this.createPropertyTab(this.data));
//		this.createNucleotideSequenceTab(this.data,tabs);
//		this.createCodingSequenceTab(this.data,tabs);
//		this.createTranslationTab(this.data,tabs);
//		this.createTagsTab(this.data,tabs);
//		this.createGcContentTab(this.data,tabs);
//		this.createImageTab(this.data,tabs);
		
		this.items=this.createTabPanel(tabs);
		
		this.callParent(arguments);
	},

	createPropertyTab:function(data)
	{
		var self=this;
		var fields=[];
		this.addField(fields,data,'Accession','accession',this.renderAccession);
		this.addField(fields,data,'Taxon','taxon_name',function(value){return String.format('<a href="#" onclick="vardb.taxonPopup(\'{1}\')">{0}</a>',value,data.taxon_identifier);});
		this.addField(fields,data,'Disease','disease_name',function(value){return String.format('<a href="#" onclick="vardb.diseasePopup(\'{1}\')">{0}</a>',value,data.disease_identifier);});
		this.addField(fields,data,'Pathogen','pathogen_name',function(value){return String.format('<a href="#" onclick="vardb.pathogenPopup(\'{1}\')">{0}</a>',value,data.pathogen_identifier);});
		this.addField(fields,data,'Ortholog','ortholog_name',function(value){return String.format('<a href="#" onclick="vardb.orthologPopup(\'{1}\')">{0}</a>',value,data.ortholog_identifier);});
		this.addField(fields,data,'Family','family_name',function(value){return String.format('<a href="#" onclick="vardb.familyPopup(\'{1}\')">{0}</a>',value,data.family_identifier);});
		this.addField(fields,data,'Subgroup','subgroup_name',function(value){return String.format('<a href="#" onclick="vardb.subgroupPopup(\'{1}\')">{0}</a>',value,data.subgroup_identifier);});
		this.addField(fields,data,'Country','country_name',function(value){return String.format('<a href="#" onclick="vardb.countryPopup(\'{1}\')">{0}</a>',value,data.country_identifier);});
		this.addField(fields,data,'Ref','ref_name',function(value){return String.format('<a href="#" onclick="vardb.refPopup(\'{1}\')">{0}</a>',value,data.ref_identifier);});
		this.addField(fields,data,'Strain','strain');
		this.addField(fields,data,'Pseugodene','pseudogene');
		this.addField(fields,data,'Truncated','truncated');
		this.addField(fields,data,'Location','location');
		this.addField(fields,data,'Locus tag','locus_tag');
		this.addField(fields,data,'Description','defline');
		this.addField(fields,data,'Gene','gene');
		this.addField(fields,data,'Product','product');
		this.addField(fields,data,'UniProt','uniprot');
		this.addField(fields,data,'Date','udate');
		this.addField(fields,data,'Length','ntlength');	
		this.addField(fields,data,'Isolate','isolate');
		this.addField(fields,data,'Division','division');
		this.addField(fields,data,'Natype','natype');
		this.addField(fields,data,'Collection date','collection_date');
		this.addField(fields,data,'Serotype','serotype');
		this.addField(fields,data,'Serogroup','serogroup');
		this.addField(fields,data,'Serovar','serovar');
		this.addField(fields,data,'Subtype','subtype');
		this.addField(fields,data,'Clone','clone');
		this.addField(fields,data,'Segment','segment');
		this.addField(fields,data,'Host','host');
		this.addField(fields,data,'Specific host','specific_host');
		this.addField(fields,data,'Lab host','lab_host');
		this.addField(fields,data,'Isolation source','isolation_source');
		this.addField(fields,data,'Molecule type','mol_type');
		this.addField(fields,data,'Locus','locus');
		this.addField(fields,data,'Allele','allele');
		this.addField(fields,data,'Strand','strand');	
		this.addField(fields,data,'Geneid','geneid');
		this.addField(fields,data,'Plasmid','plasmid');
		this.addField(fields,data,'Codon start','codon_start');
		this.addField(fields,data,'Translation table','transl_table');
		this.addField(fields,data,'Protein','protein');
		this.addField(fields,data,'Protein ID','protein_id');
		this.addField(fields,data,'GI','gi');	
		this.addField(fields,data,'Molecular weight','molwt');
		this.addField(fields,data,'EC number','ec');	
		this.addField(fields,data,'Architecture','architecture');
		this.addField(fields,data,'Method','method');
		this.addField(fields,data,'Model','model');
		this.addField(fields,data,'Score','score');
		this.addField(fields,data,'E-value','evalue');
		return this.createPropertyGrid(fields,data);
	},
	
	renderAccession:function(value, p, record)
	{
		return Ext.String.format('<a href="/sequences/{1}.html" target="_new">{0}</a>',value,value);
	}
	
	/*
	formatTaxon:function(value, taxon_identifier)
	{
		 return String.format('<a href="#" onclick="vardb.taxonPopup(\'{1}\')">{0}</a>',
			value,taxon_identifier);
	},
	
	formatDisease:function(value, p, record)
	{
		return String.format('<a href="#" onclick="vardb.diseasePopup(\'{1}\')">{0}</a>',
			value,this.data.disease_identifier);
	},
	
	formatPathogen:function(value, p, record)
	{
		return String.format('<a href="#" onclick="vardb.pathogenPopup(\'{1}\')">{0}</a>',
			value,this.data.pathogen_identifier);
	},
	
	formatOrtholog:function(value, p, record)
	{
		return String.format('<a href="#" onclick="vardb.orthologPopup(\'{1}\')">{0}</a>',
			value,this.data.ortholog_identifier);
	},
	
	formatFamily:function(value, p, record)
	{
		return String.format('<a href="#" onclick="vardb.familyPopup(\'{1}\')">{0}</a>',
			value,this.data.family_identifier);
	},
	
	formatSubgroup:function(value, p, record)
	{
		return String.format('<a href="#" onclick="vardb.subgroupPopup\'{1}\')">{0}</a>',
			value,this.data.subgroup_identifier);
	},
	
	formatRef:function(value)
	{
		return String.format('<a href="#" onclick="vardb.refPopup(\'{1}\')">{0}</a>',
			value,this.data.ref_identifier);
	},
	*/

	/*
	formatSequence:function(accession,sequence)
	{
		var template=new Ext.XTemplate(
			'<p style="font-family:monospace;">',
			'>{accession}<br/>',
			'<tpl for="chunks">',
				'{line}<br/>',
			'</tpl>',
			'</p>');
		var chunks=Ext.ux.vardb.Renderer.chunkSequence(sequence,80);
		return template.apply({accession:accession,chunks:chunks});
	},		
	
	formatImage:function(sequence)
	{
		return '<img src="'+vardb.webapp+'/graphics/sequence.img?identifier='+sequence.identifier+'"/>';
	},
	
	createNucleotideSequenceTab:function(sequence,tabs)
	{
		if (!sequence.sequence)
			{return;}
		var tab=
		{
			title: 'Nucleotide sequence',
			autoScroll: true,
			html: this.formatSequence(sequence.accession,sequence.sequence)
		};
		tabs.push(tab);
	},
	
	createCodingSequenceTab:function(sequence,tabs)
	{
		if (!sequence.codingSequence)
			{return;}
		var tab=
		{
			title: 'Coding sequence',
			autoScroll: true,
			html: this.formatSequence(sequence.accession,sequence.codingSequence)
		};
		tabs.push(tab);
	},
	
	createTranslationTab:function(sequence,tabs)
	{
		if (!sequence.translation)
			{return;}
		var tab=
		{
			title: 'Translation',
			autoScroll: true,
			html: this.formatSequence(sequence.accession,sequence.translation)
		};
		tabs.push(tab);
	},

	createTagsTab:function(sequence,tabs)
	{
		if (sequence.tags.length===0)
			{return;}
		var data=[];	
		var index, tag, row;
		for (index=0;index<sequence.tags.length;index++)
		{
			tag=sequence.tags[index];
			row=[];
			row.push(tag.id);
			row.push(tag.name);
			data.push(row);
		}
		
		var store = new Ext.data.JsonStore({
			fields:
			[
				{name: 'id', type: 'int'},
				{name: 'name'}
			]
		});
		store.loadData(data);
		
		var sm=new Ext.grid.CheckboxSelectionModel({sortable: true, width: 20});
		
		function renderTag(value, p, r)
		{
			return '<a href="#" onclick="Ext.ux.vardb.tags.Services.editTag(\''+r.data.id+'\')">'+value+'</a><br/>';
		}
		
		var grid=new Ext.ux.vardb.Grid({
			title: 'Tags',
			store: store,
			columns:
			[
				sm,
				{header: 'Tag', width: 100, sortable: true, dataIndex: 'name', renderer: renderTag}
			],
			viewConfig: {forceFit: true},
			sm: sm,
			stripeRows: true,
			//autoScroll: true,			
			autoWidth: true,
			autoHeight: true
		});
		tabs.push(grid);
	},
	
	createImageTab:function(sequence,tabs)
	{
		var tab=
		{
			title: 'Image',
			autoScroll: true,
			html: this.formatImage(sequence)
		};
		tabs.push(tab);
	},
	
	createGcContentTab:function(sequence,tabs)
	{
		if (!sequence.sequence)
			{return;}
		var tab=new Ext.ux.vardb.GcChart({sequence: sequence.sequence, title: 'GC Content'});
		tabs.push(tab);
	}
	*/
});

