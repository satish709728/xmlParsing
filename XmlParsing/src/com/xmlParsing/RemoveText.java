package com.xmlParsing;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RemoveText {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub

		String file = "src/student.xml";
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(file);
		doc.normalize();
		
		String root = doc.getDocumentElement().getNodeName();
		System.out.println(root);
		
		NodeList list= doc.getDocumentElement().getChildNodes();
		System.out.println(list.getLength());
		
	    int count_elem=0 , countText=0;   
	    
	    for (int i=list.getLength()-1; i>-1; i--) {  
	        Node node=list.item(i);
	        
	        if (node.getNodeType()==Node.ELEMENT_NODE) {  
	            count_elem++;  
	        } 
	        
	        if (node.getNodeType()==Node.TEXT_NODE) {  
	            //if you are not 100% it contains only ignorable whitespace, add some testing here  
	            node.getParentNode().removeChild(node);
	            countText++;
	        }  
	    }  
		System.out.println("Element Node : "+count_elem+"\tText Node : "+countText);
   }
}

