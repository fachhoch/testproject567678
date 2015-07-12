define(function(require) {
	return {
		get:function(templateName,cb){
			templateName='text!../templates/'+templateName;
		    getTemplate(templateName,function(template){
				cb(template);
			});
			function getTemplate(templateName,innercb){
				try{
				   innercb(require(templateName));
				}catch(err){
				   console.log(err);
				  require([templateName], function(template){
					 innercb(template);
				 });
			   }
			}			
		}	
	}
});

