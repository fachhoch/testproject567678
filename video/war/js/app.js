require.config({
  paths: {
    "jquery": "https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min",
    "jqueryui"		:"http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min",
    "handlebars": 'handlebars',
    "prettyPhoto": 'jquery.prettyPhoto',
    "youtubepopup": 'youtubepopup',
	},
  shim: {
    'underscore': {
      exports: '_'
    },
    'handlebars': {
        exports: 'Handlebars'
    },
   'prettyPhoto': {
        'deps': ['jquery']
     },
    'jqueryui': {
          'deps': ['jquery']
     },
     'youtubepopup': {
         'deps': ['jqueryui']
    },
      
	urlArgs: "bust="+ (new Date()).getTime()
  }
});
require(["jquery","templates","page","utube","handlebars","prettyPhoto","youtubepopup","jqueryui"],
	function($,templates,page,utube,Handlebars) 
	{		
				//window.goTo=page.replaceContent;
			//	window.getTemplate=templates.get;
				//goTo('template1.html');
			window.app=utube;
			app.showSearch();
	}
);


