define(['underscore','jquery'], function(require) {
  var replaceContent = function(templateName) {
    
	getTemplate(templateName,function(template){
		$("#content").html(_.template(template));
	})
  };
  return {
    replaceContent: replaceContent
  };
});