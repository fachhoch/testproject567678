package com.saibaba.links;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.util.CollectionUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;

import com.google.appengine.repackaged.com.google.common.base.Joiner;

public class Mirchi9Reader {
	
	private Map<String, String>  links= new HashMap<String, String>();
	private static final Logger log = Logger.getLogger(Mirchi9Reader.class.getName()); 
	
	Map<String, String>  getLinks(){
		return links;
	}

	public Mirchi9Reader() {
		System.setProperty ("sun.net.client.defaultReadTimeout", "70000");
		System.setProperty ("sun.net.client.defaultConnectTimeout", "70000");
		try{
			readMirci9("http://www.mirchi9.com/cinemas/");
			//readTeluguFreeLinks("http://telugufreelinks.blogspot.com/search/label/telugu%20movies?max-results=100");
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private void  readMirci9(String url) throws Exception{
		System.out.println("url " + url);
		log.info(" url "+url);
		NodeList  mainContentDivs =parse(url);
		NodeList  postThumbnailDiv=mainContentDivs.extractAllNodesThatMatch(new HasAttributeFilter("class","Post thumbnailview"), true);
		NodeList  anchorTags=postThumbnailDiv.extractAllNodesThatMatch(new NodeFilter() {
			public boolean accept(Node node) {
				if(node instanceof LinkTag){
					LinkTag  linkTag=(LinkTag)node;
					String id=linkTag.getAttribute("id");
					return id==null ?  false: id.indexOf("textLink")!=-1;
				}
				return false;
			}
		}, true);
		for(Node node   :anchorTags.toNodeArray()){
			LinkTag  linkTag =(LinkTag)  node;
			//System.out.println(linkTag.getLinkText()+"  "+);
			String videolink=getVideoLink(linkTag.extractLink());
			log.info(linkTag.getLinkText()+" "+videolink);
			links.put(linkTag.getLinkText(),videolink);
		}
		NodeList  navigationList=mainContentDivs.extractAllNodesThatMatch(new HasAttributeFilter("class", "navigation"), true);
		NodeList previousLinkNode= navigationList.extractAllNodesThatMatch(new NodeFilter() {
			public boolean accept(Node node) {
				//System.out.println(node.getClass());
				if(!(node instanceof  LinkTag))return false;
				LinkTag  linkTag=(LinkTag)node;
				return linkTag.getLinkText().indexOf("Previous ")!=-1;
			}
		}, true);
		for(NodeIterator  iterator=previousLinkNode.elements() ; iterator.hasMoreNodes();){
			Node node=iterator.nextNode();	
			LinkTag tag=(LinkTag)node;
			readMirci9(tag.extractLink());
		}
	}
	 private String  getVideoLink(String url) throws Exception{
		Parser  parser= new Parser(url);
		NodeList  nodeList= parser.parse(new TagNameFilter("embed"));
		for(NodeIterator  iterator=nodeList.elements() ; iterator.hasMoreNodes();){
			Node node=iterator.nextNode();	
			Tag tag=(Tag)node;
			return tag.getAttribute("src");
		}
		return "N/A"+"-"+"*********************";
	}
	
	
	 private NodeList parse(String url) throws Exception{
		Parser  parser= new Parser(url);
		NodeList  nodeList =parser.parse(new HasAttributeFilter("id","MainContent"));
		return nodeList;
	 }
	
	 private void readTeluguFreeLinks(String url)throws Exception{
		 Parser  parser= new Parser(url);
		 NodeList pageNodeList= parser.parse(new HasAttributeFilter("class", "widget Blog"));
		 NodeList blogPostNodeList= pageNodeList.extractAllNodesThatMatch(new HasAttributeFilter("class", "blog-posts hfeed"), true);
		 NodeList  h3NodeLists=blogPostNodeList.extractAllNodesThatMatch(new HasAttributeFilter("class", "new"), true);
		 
		 for(NodeIterator  iterator=h3NodeLists.elements() ; iterator.hasMoreNodes();){
			Node node=iterator.nextNode();	
			LinkTag  linkTag=(LinkTag)node.getChildren().elementAt(0);
			log.info("name "+linkTag.getLinkText());
			NodeList videoLinkNodeList= new Parser(linkTag.extractLink())
			.parse(new HasAttributeFilter("class", "post-body entry-content"))
			.extractAllNodesThatMatch(new NodeFilter() {
				@Override
				public boolean accept(Node node) {
					if(node  instanceof  LinkTag){
						LinkTag  linkTag=(LinkTag)node;
						return StringUtils.contains(linkTag.extractLink(), "http://telugufreelinks.ulmb.com/");
					}
					return false;
				}
			}, true);
			List links= teluguFreeLinksVideoLink(videoLinkNodeList);
			log.info("link "+Joiner.on(',').join(links));
		 }		
		 NodeList blogPager= pageNodeList.extractAllNodesThatMatch(new HasAttributeFilter("id", "blog-pager"), true);
		 NodeList  olderNodeList=pageNodeList.extractAllNodesThatMatch(new HasAttributeFilter("id","Blog11_blog-pager-older-link"), true);
		 for(NodeIterator  iterator=olderNodeList.elements() ; iterator.hasMoreNodes();){
				Node node=iterator.nextNode();	
				LinkTag  linkTag=(LinkTag)node;
				readTeluguFreeLinks(linkTag.getLink());
		 }
	 }

	 private List teluguFreeLinksVideoLink(NodeList  srcNodeList)  throws Exception{
		List<String>  links= new ArrayList<String>();
		for(NodeIterator  iterator=srcNodeList.elements() ; iterator.hasMoreNodes();){
				Node node=iterator.nextNode();	
				LinkTag  linkTag=(LinkTag)node;
				String url=StringUtils.remove(linkTag.getLink(), "amp;");
				links.add(getVideoLink(url));
		 }
		 return links;
	}
	
	
}
