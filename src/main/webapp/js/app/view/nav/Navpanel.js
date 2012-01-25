Ext.define('Hcv.view.nav.Navpanel' ,{
	extend: 'Ext.tree.Panel',
	alias : 'widget.navpanel',
	rootVisible: false,
	useArrows: true,
	root: {
		text: 'Root',
		expanded: true,
		children: [
			{
				text: 'Hepatitis',
				leaf: true,
				pageid: 'genes'
			},
			{
				text: 'Drugs',
				leaf: true,
				pageid: 'drugs'
			},
			{
				text: 'Genes',
				expanded: true,
				pageid: 'genes',
				children: [
					{
						text: 'Core',
						leaf: true
					},
					{
						text: 'NS3',
						leaf: true
					},
					{
						text: 'NS5A',
						leaf: true
					}
				]
			}
		]
	}
});
