Ext.define('Hcv.view.tool.Variability', {
    extend: 'Ext.window.Window',
    alias : 'widget.variabilityform',

	title: 'Variability',
	width: 580,
	bodyStyle: 'padding: 10px 10px 0 10px;',
	labelWidth: 30,
	standardSubmit: true,
	fileUpload: true,
	//url: utils.webapp+'/analysis/variability.html',
	
	initComponent:function()
	{
		this.items=
		[
		 /*
			this.createRow(
			[
				this.createControl(new vardb.UserAlignmentSelectList({hiddenName: 'alignmentIdentifier'}))
			]),
			this.createRow(
			[
				this.createTextAreaControl({name: 'sequences', fieldLabel: 'Enter a multiple sequence alignment in FASTA or CLUSTALW format'})
			]),
			this.createRow(
			[
				this.createFileUploadControl({name: 'file', fieldLabel: 'File', emptyText: 'Upload an alignment file'})
			])
			*/
		];

		this.buttons=
		[
		 	/*
			this.createExampleButton(),
			this.createClearButton(),
			this.createStandardSubmitButton()
			*/
		];
		this.callParent(arguments);
	}
	
	/*
	checkValidation:function()
	{
		if (!this.checkNotEmpty('sequences,file,alignmentIdentifier','Please enter an alignment or select a file to upload'))
			{return false;}
		return true;
	},
	
	showExampleHandler:function()
	{
		var form=this.getForm();
		vardb.ajaxRequest('/demo/variability.json',{},function(json)
		{
			form.findField('sequences').setValue(json.sequences);
		});
	}
	*/
});