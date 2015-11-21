package org.concordion.jtechlog.asciidoc.macro;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XmlNamespaceEnhancer {


    public static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
    public static final String XMLNS_PREFIX_WITH_SEPARATOR = "xmlns:";

    public String addNamespace(String xml) {
        try {
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
            document.getDocumentElement().setAttributeNS(XMLNS_URI,
                    XMLNS_PREFIX_WITH_SEPARATOR + ConcordionNamespace.PREFIX, ConcordionNamespace.URI);
            return toString(document);
        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e) {
            throw new IllegalArgumentException("Error by parsing XML", e);
        }
    }

    private String toString(Document document) throws TransformerException {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        StringWriter buffer = new StringWriter();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(new DOMSource(document), new StreamResult(buffer));
        return buffer.toString();
    }


}
