package clikmng.nanet.go.kr.mdm.utl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
//import org.w3c.dom.SAXException;

public class MdmXmlUtil 
{

	public MdmXmlUtil()
	{

	}
	
	public Document xmlParser(String path)
	{
		Document doc = null;
		
		try
		{
			DocumentBuilderFactory 	factory 		=	DocumentBuilderFactory.newInstance();
			DocumentBuilder			builder 		=	factory.newDocumentBuilder();
			doc = builder.parse(path);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return doc;
	}

	public String getValue(Document doc, String tagName)
	{
		NodeList 	nodeList		=	doc.getElementsByTagName(tagName);
		String		value			=	new String();
		String		result			=	"";
		Element 	element;
		
		if(nodeList.getLength() > 0)
		{
			element = (Element) nodeList.item(0);
			result = element.getTextContent();
		}

		return result;

	
/*
		try
		{
			for(int i = 0 ; i < nodeList.getLength() ; i ++)
			{
				Node	node	=	nodeList.item(i).getChildNodes().item(0);
				value 			=	node.getNodeValue();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return value;
*/
	}
	
	public HashMap<String, String> getAttribute(Document doc, String tagName)
	{

//		System.out.println("############## getAttribute Start ####################");
//		System.out.println("tagName ===> " + tagName);
		
		NodeList nodeList = doc.getElementsByTagName(tagName);

		HashMap<String, String>	attr	=	new HashMap();

		try
		{
			for(int i = 0 ; i < nodeList.getLength() ; i++)
			{
				NamedNodeMap map = nodeList.item(i).getAttributes();
				
//				System.out.println("map.getLength() ===> " + map.getLength());
				for(int x = 0 ; x < map.getLength() ; x++)
				{
					Node node = map.item(x);
					if(node != null)
					{
//						System.out.println("node ===> " + node);
//						System.out.println( node.getNodeName() + " ===> " + node.getNodeValue());
					
						attr.put(node.getNodeName(), node.getNodeValue());
					}
					
					
					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
//		System.out.println("############## getAttribute End ####################");
		return attr;

	}

}
