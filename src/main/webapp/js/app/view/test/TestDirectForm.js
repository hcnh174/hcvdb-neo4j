Ext.define('Hcv.view.test.TestDirectForm', {
	extend: 'Ext.window.Window',
	alias : 'widget.testdirectform',

	title : 'TestDirectForm',
	layout: 'fit',
	autoShow: true,
	width: 400,
	height: 250,
	plain: true,

	initComponent:function()
	{
		var self=this;
		this.items=[
			{
			    xtype: 'form',
			    title: 'Basic Information',
			    border: false,
			    bodyPadding: 10,
			    api: {
			        load: hcvDirect.loadForm,
			        submit: hcvDirect.updateForm
			    },
			    paramsAsHash: true,
			    dockedItems: [{
			    	xtype: 'toolbar',
			        dock: 'bottom',			        
			        ui: 'footer',
			        style: 'margin: 0 5px 5px 0;',
			        items: ['->', {
			            text: 'Submit',
			            scope: this,
			            handler: function(){
			                self.down('form').getForm().submit({
			                    params: {
			                        foo: 'bar',
			                        uid: 34
			                    }
			                });
			            }      
			        }]
			    }],
			    defaultType: 'textfield',
			    defaults: {
			        anchor: '100%'
			    },
			    items: [{
			        fieldLabel: 'Name',
			        name: 'name'
			    },{
			        fieldLabel: 'Email',
			        msgTarget: 'side',
			        name: 'email'
			    },{
			        fieldLabel: 'Company',
			        name: 'company'
			    }]
			}
		];
	
		this.buttons=
		[
			{
				text: 'Close',
				scope: this,
				handler: function(){this.hide();}
			}
		];
		
		this.callParent(arguments);
	}
});
