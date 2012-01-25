Ext.define('Hcv.view.user.NewUser', {
	extend: 'Ext.window.Window',
	alias : 'widget.newuser',

	title : 'User information',
	layout: 'fit',
	autoShow: true,

	initComponent: function() {

		this.items = [
		{
			xtype: 'form',
			defaults: {width: 300, allowBlank: false},
			defaultType: 'textfield',
//			labelWidth: 75,
//			url: vardb.webapp+'/newuser.html',
//			frame: true,
//			width: 550,
//			bodyStyle: 'padding: 5px 5px 0',
//			monitorValid: true,
			items:
				[
					{
						fieldLabel: 'Username*',
						name: 'username'
						//plugins: [Ext.ux.plugins.RemoteValidator],
						//rvOptions: {url: vardb.webapp+'/ajax/validate/user.json'}
					},
					{
						fieldLabel:'Password 1*',
						name: 'password1',
						inputType: 'password'
					},
					{
						fieldLabel: 'Password 2*',
						name: 'password2',
						inputType: 'password'		
					},
					{
						fieldLabel: 'First name',
						name: 'firstname',
						allowBlank: true
					},
					{
						fieldLabel: 'Last name',
						name: 'lastname',
						allowBlank: true
					},
					{
						fieldLabel: 'Affiliation',
						name: 'affiliation',
						allowBlank: true
					},
					{
						fieldLabel: 'Email*',
						name: 'email'
						//vtype: 'email'
					}				  
			  ]
		}
		];
		
		this.buttons=[
				{
					text: 'Submit',
					action: 'submit'
//					formBind: true,
//					scope: this,
//					handler: this.submitHandler
				},
				{
					text: 'Reset',
					action: 'reset'
//					formBind: true,
//					scope: this,
//					handler: function(){this.getForm().reset();}
				}
			];
	}

	/*
	onRender:function()
	{
		Ext.ux.vardb.NewUserForm.superclass.onRender.apply(this, arguments);
		this.on('afterlayout', function(){this.getForm().findField('username').focus();},this);
	},
	
	submitHandler:function()
	{
		var form=this.getForm();
		var password1=form.findField('password1').getValue();
		var password2=form.findField('password2').getValue();
		if (password1!==password2)
		{
			Ext.MessageBox.alert('Alert','Passwords do not match.');
			form.findField('password1').setValue('');
			form.findField('password2').setValue('');
			return;
		}
		form.getEl().dom.action=vardb.webapp+'/newuser.html';
		form.getEl().dom.method='post';
		form.getEl().dom.submit();
	}
	*/
});
