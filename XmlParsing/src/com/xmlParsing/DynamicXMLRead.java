package com.xmlParsing;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DynamicXMLRead {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		
			File file = new File("src/student.xml");
			
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.normalize();
			
			String root = doc.getDocumentElement().getNodeName();
			System.out.println(root);
			
			NodeList list = doc.getElementsByTagName(root);
			System.out.println(list.getLength());
			
			for(int i=0;i<list.getLength();i++){
				Node node = list.item(i);
				if(node.hasAttributes()){
					getAttributes(node.getAttributes());
				}
				
				if(node.hasChildNodes()){
					getNodes(node.getChildNodes());			
				}
			}
	 }

	@SuppressWarnings("static-access")
		private static void getNodes(NodeList childNodes) {
			// TODO Auto-generated method stub
			//System.out.println(childNodes.getLength());
		
			//System.out.println("Length is : "+childNodes.getLength());
		
			for(int i=childNodes.getLength()-1;i>-1;i--){
				Node cNode = childNodes.item(i);
	
				if(cNode.getNodeType()==cNode.TEXT_NODE){
					cNode.getParentNode().removeChild(cNode);
					//System.out.println("#TEXT field Removed");
				}
				
				if(cNode.hasAttributes()){
					System.out.println("\n"+cNode.getNodeName());
					getAttributes(cNode.getAttributes());
					}
				
				if(cNode.hasChildNodes()){
					
					if(cNode.getChildNodes().getLength()>=2){
						
					getNodes(cNode.getChildNodes());
					}
					
					else if(cNode.getNodeType()==cNode.ELEMENT_NODE){
						
						Element ele = (Element) cNode;
						System.out.println(ele.getNodeName()+"\t"+ele.getTextContent());
						
					}
				}
			}

		}

	private static void getAttributes(NamedNodeMap attributes) {
		// TODO Auto-generated method stub
		for(int j=0;j<attributes.getLength();j++){
			System.out.println(attributes.item(j).getNodeName()+
					"\t: "+attributes.item(j).getNodeValue());
		}
	}
}

