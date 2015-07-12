define(['jquery','handlebars','text!../templates/utube.template.html'], function($,Handlebars,_template) {
	var searchTemplate =Handlebars.compile($(_template).filter('#searchTemplate').html());
	var resultsTemplate =Handlebars.compile($(_template).filter('#resultsTemplate').html());
	var contentTemplate =Handlebars.compile($(_template).filter('#content').html());
	var noResultsTemplate =Handlebars.compile($(_template).filter('#noResultsTemplate').html());
	var showSearch= function() {
		search();
	};
	
	var secondsToTime=function secondsToTime(secs){
	    var hours = Math.floor(secs / (60 * 60));
	    
	    var divisor_for_minutes = secs % (60 * 60);
	    var minutes = Math.floor(divisor_for_minutes / 60);
	 
	    var divisor_for_seconds = divisor_for_minutes % 60;
	    var seconds = Math.ceil(divisor_for_seconds);
	   
	    var obj = {
	        "h": hours,
	        "m": minutes,
	        "s": seconds
	    };
	    return obj;
	}
	 var search = function() {
		var searchText= $('#searchText').val();	
		 if(searchText==null|| searchText.trim()===""){
				displayEmpty();
				
				
				
			}else{
				searchUtube(searchText);
			}
		  };
		  
		  var displayEmpty =function(){
			  $("#content").html(contentTemplate());
			  $("#search").html(searchTemplate());
		  }
		  var searchUtube =function (searchText){
			  var request = gapi.client.youtube.search.list({
				    q: searchText,
				    part: 'snippet',
				    maxResults:'50'
				  });

				  request.execute(function(response) {
				    var str = JSON.stringify(response.result);
				    //console.log(str);
				    //console.log(response)
					if(response.items!=null&&response.items.length>0  ){
//						var results=response.items;
//						for(var i=0;i<results.length;i++){
//							var entry=results[i];
//							entry.videourl='http://www.youtube.com/watch?v='+entry.id.videoId; 
//							//entry.duration=secondsToTime(entry.media$group.media$content[0].duration);
//							//console.log(entry.duration);
//							//console.log(entry.duration.h);
//							//console.log(entry.duration.m);
//							//console.log(entry.duration.s);
//							//console.log(entry);
//							//console.log(entry.snippet.title);
//							//console.log(entry.snippet.thumbnails.default.url);
//							
//							//console.log(entry.videourl);
//						}
						//console.log(results);
						$("#results").html(resultsTemplate(response));
						$("a.youtube").YouTubePopup({ autoplay: 0 });
					}
					else{
						$("#results").html(noResultsTemplate);
					}
				  });

				/*$.getJSON('https://gdata.youtube.com/feeds/api/videos?q='+searchText+'&orderby=published&v=2&alt=json&max-results=500', function(data){
				});*/
		  }
		  
	  return {
		  showSearch: showSearch,
		  search:search
	  };

});


