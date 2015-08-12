package com.xmlParsing;

import java.io.File;
import java.io.IOException;

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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXml {

	/**
	 * @param args
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws TransformerException 
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		// TODO Auto-generated method stub
		
		String xmlFile = "src/student.xml";
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(xmlFile);
		document.normalize();
		
		String root = document.getDocumentElement().getNodeName();
		System.out.println("Root Element : "+root);
		
		Node rNode = document.getElementsByTagName(root).item(0);
		
		NodeList nList = document.getElementsByTagName("student");
		//System.out.println(document.getChildNodes().item(1));
		
		for(int i=0;i<nList.getLength();i++)
		{
			Node node = nList.item(i);
			System.out.println("\n\nNode Name is : " +node.getNodeName()+" "+(i+1));
			
			if(node.hasAttributes()){
				NamedNodeMap nameMap = node.getAttributes();
				for(int k=0;k<nameMap.getLength();k++){
					if(nameMap.item(k).getNodeValue().equalsIgnoreCase("1")){
						rNode.removeChild(node);
					}
				}
			}
			
			if(node.getNodeType()==node.ELEMENT_NODE){
				Element ele = (Element)node;
				System.out.println("NAME : "+ele.getElementsByTagName("name").item(0).getTextContent());
				System.out.println("AGE : "+ele.getElementsByTagName("age").item(0).getTextContent());
				System.out.println("BRANCH : "+ele.getElementsByTagName("branch").item(0).getTextContent());
			}
		}
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(xmlFile));
		transformer.transform(source, result);
		System.out.println("Xml Writing done");
		
	}

}


/**
 * String[] text =new String[]{"hello","hii","Morning","Night@^&%^&%&*("};
 * for(int f=0;f<text.length;f++){
 * System.out.println(text);
 * }
 * */
