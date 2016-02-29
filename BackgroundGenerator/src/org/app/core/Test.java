package org.app.core;

import java.io.File;
import java.io.IOException;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Test {
	private Document svgDocument;
	
	public Test(String filePath) {
		setSVGDocument(createSVGDocument(filePath));
		System.out.println(this.getSVGDocument().getBaseURI());
	}
	
	public Document getSVGDocument() {
		return svgDocument;
	}

	public void setSVGDocument( Document document ) {
	    initSVGDOM( document );
	    this.svgDocument = document;
	  }
	
	
	private void initSVGDOM( Document document ) {
	    UserAgent userAgent = new UserAgentAdapter();
	    DocumentLoader loader = new DocumentLoader( userAgent );
	    BridgeContext bridgeContext = new BridgeContext( userAgent, loader );
	    bridgeContext.setDynamicState( BridgeContext.DYNAMIC );

	    // Enable CSS- and SVG-specific enhancements.
	    (new GVTBuilder()).build( bridgeContext, document );
	  }
	
	private Document createSVGDocument(String filePath) {
		Document doc = null;
		try {
		    String parser = XMLResourceDescriptor.getXMLParserClassName();
		    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
		    String strURI = new File(filePath).toURI().toString();
		    doc = f.createDocument(strURI);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return doc;
	}
	
	public static void main(String[] args) {
		Test t = new Test("C:/Users/bella/Documents/SVG Files/template.svg");
		NodeList nl = t.getSVGDocument().getElementsByTagName("g").item(0).getChildNodes();
		
		for( int i = 0; i < nl.getLength(); i++) {
			System.out.println("Nodename " + i + ": " + nl.item(i).getNodeName());
		}
	}
}
