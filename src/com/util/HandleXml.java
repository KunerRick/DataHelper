package com.util;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;



public class HandleXml {
	public HandleXml(){
		super();
	}
	 public static Document parse(String url) throws DocumentException {
	        SAXReader reader = new SAXReader();
	        Document document = reader.read(url);
	        return document;
	    }


public static Document createDocument(String rootTagName) {
    Document document = DocumentHelper.createDocument();
    document.addElement(rootTagName );
    return document;
}

public static void saveDocument(String fileName,Document  document){
	try {

	//��ӡ	
		 OutputFormat format = OutputFormat.createPrettyPrint();
	        format.setNewlines(true); //�����Ƿ���
	        format.setEncoding("UTF-8");
	        format.setIndent(true);
	        format.setIndent("	");
	        XMLWriter  writer = new XMLWriter( new FileWriter(fileName), format );
	        writer.write( document );
	        writer.flush();
	        writer.close();

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		  
}

public static void treeWalk(Element element) {
    for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
        Node node = element.node(i);
        if ( node instanceof Element ) {
            treeWalk( (Element) node );
        }
        else {
            // do something....
        }
    }
}



}
