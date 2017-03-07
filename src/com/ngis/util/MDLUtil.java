package com.ngis.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ngis.util.domain.MDLInputParam;

public class MDLUtil {
	public static List<MDLInputParam> parseMDL(String filename) throws Exception{
		List<MDLInputParam> list = new ArrayList<MDLInputParam>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(filename);
		
		Node stateGroupNode = document.getElementsByTagName("StateGroup").item(0);
		for (int i = 0; i < stateGroupNode.getChildNodes().getLength(); i++) {
			//States
			Node statesNode = stateGroupNode.getChildNodes().item(i);
			for (int j = 0; j < statesNode.getChildNodes().getLength(); j++) {
				MDLInputParam model = new MDLInputParam();

				//State
				Node stateNode = statesNode.getChildNodes().item(j);
				
				if(stateNode.getNodeType()==3)
					continue;
				
				model.setStateId(((Element)stateNode).getAttribute("id"));
				
				List<String> resEventNames  =new ArrayList<String>();
				List<String> noresEventNames  =new ArrayList<String>();
				for (int k = 0; k < stateNode.getChildNodes().getLength(); k++) {
					//Event
					Node eventNode = stateNode.getChildNodes().item(k);
					if(eventNode.getNodeType()==3)
						continue;
					
					if(((Element)eventNode).getAttribute("type").equals("response")){
						resEventNames.add(((Element)eventNode).getAttribute("name"));
					}else if(((Element)eventNode).getAttribute("type").equals("noresponse")){
						noresEventNames.add(((Element)eventNode).getAttribute("name"));
					}
				}
				model.setResEventName(resEventNames);
				model.setNoresEventName(noresEventNames);
				
				list.add(model);
			}
		}
		
		return list;
	}
}
