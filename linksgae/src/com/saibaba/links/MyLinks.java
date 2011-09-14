package com.saibaba.links;

import java.util.HashMap;
import java.util.Map;

public class MyLinks {
	
	private static MyLinks   myLinks= new MyLinks();
	private Map<String, String>  links= new HashMap<String, String>();
	
	private MyLinks(){
		
	}
	
	public static MyLinks  getInstance(){
		return myLinks;
	}
	
	public void addLink(String name , String value){
		links.put(name, value);
	}
	
	public Map<String, String>  getLinks(){
		return links;
	}
	
	void addFromMirchi9(Mirchi9Reader  mirchi9Reader){
		for(String name :mirchi9Reader.getLinks().keySet() ){
			addLink(name, mirchi9Reader.getLinks().get(name));
		}
	}
}
