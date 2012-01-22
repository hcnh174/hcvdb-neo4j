Ext.define('Hcv.view.HbvPanel' ,{
	extend: 'Ext.tab.Panel',
	alias : 'widget.hbvpanel',
	title: 'Hepatitis B virus',
	activeTab: 0,
	items:
	[
		{
		    title: 'Tab 1',
		    bodyPadding: 10,
		    html : 'A simple tab'
		},
		{
		    title: 'Tab 2',
		    html : 'Another one'
		}
	]
});