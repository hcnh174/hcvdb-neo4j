Ext.define('Hcv.view.user.Edit', {
    extend: 'Ext.window.Window',
    alias : 'widget.feedback',

    title: 'Please use the form below to send your comments or suggestions.',
    layout: 'fit',
    autoShow: true,

    initComponent: function() {

        this.items = [
            {
                xtype: 'form',
                defaults: {width: 350, allowBlank: true},	
//                labelWidth: 75,
//            	frame: true,
//            	width: 550,
//            	bodyStyle: 'padding: 5px 5px 0',
//            	defaultType: 'textfield',
//            	monitorValid: true,
//            	iconCls: 'icon-email',
                items: [
	                {
						fieldLabel: 'Name',
						name: 'name',
						value: 'name'//this.name
					},
					{
						fieldLabel: 'Affiliation',
						name: 'affiliation',
						value: 'affiliation'//this.affiliation
					},
					{
						fieldLabel: 'Email*',
						name: 'email',
						vtype: 'email',
						allowBlank: false,
						value: 'email'//this.email
					},
					//purposeCombo,
					{
						fieldLabel: 'Comments*',
						name: 'comments',
						xtype: 'textarea',
						allowBlank: false,
						grow: true		
					}                    
                ]
            }
        ];

        this.buttons = [
			{
				text: 'Submit',
				action: 'submit'
				//formBind: true,
				//scope: this,
				//handler: this.submitHandler
			},
			{
				text: 'Reset',
				action: 'reset'
				//scope: this,
				//handler: function(){this.getForm().reset();}
			}
        ];

        this.callParent(arguments);
    },


//	var purposeCombo=new nelson.extjs.SelectList(
//		{
//			data: [['COMMENT','Comment'],['QUESTION','Question'],['CORRECTION','Correction'],['SUBMISSION','Submission']],
//			name: 'purpose',
//			fieldLabel: 'Purpose',
//			value: this.purpose
//		});


//	submitHandler:function()
//	{
//		if (!this.getForm().isValid())
//			{return;}
//		this.getForm().getEl().dom.action=vardb.webapp+'/contact.html';
//		this.getForm().getEl().dom.method='post';
//		this.getForm().getEl().dom.submit();
//	}
});
