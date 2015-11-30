package org.concordion.jtechlog.asciidoc.macro.html;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class HtmlTagExtractor {

    public String extractExecuteOnParagraphs(String xml) {
        Document document = new StringToDocument().parse(xml);
        forAll(document, "executeOnParagraph", new DomManipulateFunction() {
            @Override
            public void manipulate(Element element) {
                extractExecuteOnParagraph(element);
            }
        });
        forAll(document, "setOnAllRows", new DomManipulateFunction() {
            @Override
            public void manipulate(Element element) {
                extractSetOrAssertEqualsOnAllRows(element, "concordion:set");
            }
        });
        forAll(document, "assertEqualsOnAllRows", new DomManipulateFunction() {
            @Override
            public void manipulate(Element element) {
                extractSetOrAssertEqualsOnAllRows(element, "concordion:assertEquals");
            }
        });
        forAll(document, "executeOnTable", new DomManipulateFunction() {
            @Override
            public void manipulate(Element element) {
                extractExecuteOnTable(element);
            }
        });
        forAll(document, "verifyRows", new DomManipulateFunction() {
            @Override
            public void manipulate(Element element) {
                extractVerifyRows(element);
            }
        });
        return new DocumentToString().convert(document);
    }

    private void forAll(Document document, String tagName, DomManipulateFunction domManipulateFunction) {
        NodeList nodeList = document.getElementsByTagNameNS(Namespaces.CONCORDION.getUri(), tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            domManipulateFunction.manipulate(element);
        }
    }

    private void extractExecuteOnParagraph(Element element) {
        String statement = element.getAttribute("statement");
        Element parent = (Element) element.getParentNode();
        parent.setAttributeNS(Namespaces.CONCORDION.getUri(), "concordion:execute", statement);
        parent.removeChild(element);
    }

    private void extractSetOrAssertEqualsOnAllRows(Element element, String attributeName) {
        String statement = element.getAttribute("statement");
        int columnNumber = columnNumber(element);
        Element tableElement = findParentWithName(element, "table");
        NodeList thElements = tableElement.getElementsByTagName("th");
        Element thElement = (Element) thElements.item(columnNumber);
        thElement.setAttributeNS(Namespaces.CONCORDION.getUri(), attributeName, statement);
    }

    private void extractExecuteOnTable(Element element) {
        String statement = element.getAttribute("statement");
        Element tableElement = findParentWithName(element, "table");
        tableElement.setAttributeNS(Namespaces.CONCORDION.getUri(), "concordion:execute", statement);
        Element trElement = findParentWithName(element, "tr");
        Element parent = (Element) trElement.getParentNode();
        parent.removeChild(trElement);
    }

    private void extractVerifyRows(Element element) {
        String statement = element.getAttribute("statement");
        Element tableElement = findParentWithName(element, "table");
        tableElement.setAttributeNS(Namespaces.CONCORDION.getUri(), "concordion:verifyRows", statement);
        Element trElement = findParentWithName(element, "tr");
        Element parent = (Element) trElement.getParentNode();
        parent.removeChild(trElement);
    }


    private Element findParentWithName(Element baseElement, String elementNameToFind) {
        Element element = baseElement;
        while (!element.getNodeName().equals(elementNameToFind)) {
            element = (Element) element.getParentNode();
        }
        return element;
    }

    private int columnNumber(Element element) {
        Element tdElement = findParentWithName(element, "td");
        Element trElement = findParentWithName(element, "tr");
        NodeList tdElements = trElement.getElementsByTagName("td");
        for (int i = 0; i < tdElements.getLength(); i++) {
            if (tdElement == tdElements.item(i)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Element is not in a proper table.");
    }


    private interface DomManipulateFunction {
        void manipulate(Element element);
    }
}
