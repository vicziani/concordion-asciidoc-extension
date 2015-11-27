package org.concordion.jtechlog.asciidoc.macro.html;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class HtmlTagExtractor {

    public String extractExecuteOnParagraphs(String xml) {
        Document document = new StringToDocument().parse(xml);
        NodeList nodeList = document.getElementsByTagNameNS(Namespaces.CONCORDION.getUri(), "executeOnParagraph");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            extractExecuteOnParagraph(element);
        }
        return new DocumentToString().convert(document);
    }

    private void extractExecuteOnParagraph(Element element) {
        String statement = element.getAttribute("statement");
        Element parent = (Element) element.getParentNode();
        parent.setAttributeNS(Namespaces.CONCORDION.getUri(), "concordion:execute", statement);
        parent.removeChild(element);
    }
}
