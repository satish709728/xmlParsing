package com.xmlParsing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ComparingTwoXML {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws TransformerException 
	 */
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		
		String file1 = "src/file1.xml";
		String file2 = "src/file2.xml";
		
		/**
		 * XML document building configuration
		 */
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document1 = builder.parse(file1);
		Document document2 = builder.parse(file2);
		String rootName = document1.getDocumentElement().getNodeName();
		
		/**
		 * Call the function for getting Second file's Object id list
		 * convert this list into object array
		 */
		List<String> Document1List = listObjectId(document1.getElementsByTagName("User"));
		List<String> Document2List = listObjectId(document2.getElementsByTagName("User"));
		Object[] Document1object = Document1List.toArray();
		Object[] Document2object = Document2List.toArray();
		
		Node rNode = document1.getElementsByTagName(rootName).item(0);		
		System.out.println(document1.getElementsByTagName("User").item(0).getNodeName());
		
		for(Object obj : Document1object){
			System.out.println("First : "+obj);
			
			for(Object obj1 : Document2object){
				System.out.println("Second : "+obj1);
				
				if(obj.equals(obj1)){
					System.out.println("EQUAL : "+obj+"\tAND : "+obj1);
					removeChildNode(rNode , obj);
				}
			}
			
		}	
		
		/**
		 * Again rewrite the xml file after deleting node of trim
		 */
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(document1);
		StreamResult result = new StreamResult(new File(file1));
		transformer.transform(source, result);
		System.out.println("Xml Writing done");		
	}


	/**
	 * Method for removing Child Node According Condition
	 * @param rNode
	 * @param obj
	 */
	@SuppressWarnings("static-access")
	private static void removeChildNode(Node rNode, Object obj) {
		// TODO Auto-generated method stub
		
		NodeList list = rNode.getChildNodes();
		System.out.println("Length : "+list.getLength());
		
		for(int i=list.getLength()-1;i>-1;i--){
			Node node = list.item(i);
			
			if(node.getNodeType()==node.TEXT_NODE){
				node.getParentNode().removeChild(node);
				//System.out.println("#TEXT field Removed");
			}
			
			if(node.getNodeType()==Node.ELEMENT_NODE){
				
				System.out.println(node.getNodeName());
				Element element = (Element) node;
				String id = element.getElementsByTagName("ObjectId").item(0).getTextContent();
				System.out.println(id);
				
				if(id.equals(obj)){
					rNode.removeChild(node);
					System.out.println("Child Node Removed !!");
				}
			}			
		}		
	}


	/**
	 * Method that returns objectID's list
	 * @param list
	 * @return
	 */
	private static List<String> listObjectId(NodeList list) {
		// TODO Auto-generated method stub		
		List<String> ObjectId = new ArrayList<String>();
		
		for(int i=0;i<list.getLength();i++){
			Node node = list.item(i);
			System.out.println(node.getNodeName());
			
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element element = (Element) node;
				String id = element.getElementsByTagName("ObjectId").item(0).getTextContent();
				System.out.println(id);
				ObjectId.add(id);
			}
		}
		
		return ObjectId;
	}
	
	
	
}
