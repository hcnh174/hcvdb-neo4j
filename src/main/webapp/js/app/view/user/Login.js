Ext.define('Hcv.view.user.Login', {
    extend: 'Ext.window.Window',
    alias : 'widget.login',

    title : 'Login',
    layout: 'fit',
    autoShow: true,
    frame: false,

//	labelWidth: 100,
//	url: utils.webapp+'/j_spring_security_check',
//	frame: false, //true
//	width: 300,
//	bodyStyle: 'padding:5px 5px 0',
//	defaultType: 'textfield',
//	monitorValid: false,
	iconCls: 'login-icon',    

	initComponent:function()
	{
		var self=this;
		this.items=[
			{
			    xtype: 'form',
			    items: [
			        {
			            xtype: 'textfield',
			            name : 'j_username',
			            fieldLabel: 'Name',
			            allowBlank: false,
			            value: 'username'
			        },
			        {
			            xtype: 'textfield',
			            name : 'email',
			            fieldLabel: 'Email',
			            allowBlank: false,
			            inputType: 'password'
			        },
			        {
			        	xtype: 'checkbox',
						fieldLabel: 'Remember me',
						name: '_spring_security_remember_me'						
					}			        
			    ]
			}			
		],			
		this.buttons=[
			{
				text: 'Login',
				action: 'login'
			}
		];
		this.callParent(arguments);
	}
	
	/*
	onRender:function()
	{
		nelson.users.Login.superclass.onRender.apply(this, arguments);
		this.on('afterlayout', function()
		{
			if (this.username)
				{this.getForm().findField('j_password').focus();}
			else {this.getForm().findField('j_username').focus();}	
		},this);
	},
	
	submitForm:function()
	{
		var self=this;
		//if (!this.isValid())
		//	{return;}
		this.getForm().submit(
		{
			method: 'post',
			waitTitle: 'Connecting',
			waitMsg: 'Sending data...',
			url: utils.webapp+'/j_spring_security_check',
			success: function(form,action)
			{
				var json=Ext.decode(action.response.responseText);
				var redirect=(!json.redirect) ? utils.webapp+'/index.html' : json.redirect; 
				window.location=redirect;
			},
			failure: function(form,action)
			{
				var json=Ext.decode(action.response.responseText);
				Ext.MessageBox.alert('Failed',json.message);
				self.getForm().reset();
			}
		});
	}
	*/
});
