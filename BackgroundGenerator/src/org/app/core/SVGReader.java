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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SVGReader {
	private Document svgDocument;
	
	public SVGReader(String filePath) {
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
		SVGReader svgReader = new SVGReader("C:/Users/bella/Documents/SVG Files/template.svg");
		NodeList nl = svgReader.getSVGDocument().getElementsByTagName("g").item(0).getChildNodes();
		
		for( int i = 0; i < nl.getLength(); i++) {
			NamedNodeMap nnm = nl.item(i).getAttributes();
			
			if (nnm != null) {
				System.out.println("NodeID " + i + ": " + nnm.getNamedItem("id").getNodeValue());
			}
		}
	}
}
