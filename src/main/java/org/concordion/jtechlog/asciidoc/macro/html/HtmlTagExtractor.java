package org.concordion.jtechlog.asciidoc.macro.html;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
        forAll(document, "executeOnList", new DomManipulateFunction() {
            @Override
            public void manipulate(Element element) {
                extractExecuteOnList(element);
            }
        });
        forAll(document, "assertTrueOnParagraph", new DomManipulateFunction() {
            @Override
            public void manipulate(Element element) {
                extractAssertTrueOnParagraph(element);
            }
        });
        forAll(document, "assertFalseOnParagraph", new DomManipulateFunction() {
            @Override
            public void manipulate(Element element) {
                extractAssertFalseOnParagraph(element);
            }
        });
        removeListDivs(document, document.getDocumentElement());
        return new DocumentToString().convert(document);
    }

    private void removeListDivs(Document document, Node node) {
        if (nodeToRemove(node)) {
            Node parent = node.getParentNode();
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                Node child = document.importNode(node.getChildNodes().item(i), true);
                parent.getParentNode().insertBefore(child, parent.getNextSibling());
            }
            parent.removeChild(node);
        }
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            removeListDivs(document, node.getChildNodes().item(i));
        }
    }

    private boolean nodeToRemove(Node node) {
        if (node instanceof Element) {
            Element element = (Element) node;
            return element.getNodeName().equals("div") && hasListChild(element) && isListItem(element.getParentNode());
        } else {
            return false;
        }
    }

    private boolean hasListChild(Node node) {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (isListNode(node.getChildNodes().item(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isListNode(Node node) {
        return node.getNodeName().equals("ul") || node.getNodeName().equals("ol");
    }

    private boolean isListItem(Node node) {
        return node.getNodeName().equals("li");
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
        Element parent = findParentWithName(element, "p");
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

    private void extractExecuteOnList(Element element) {
        String statement = element.getAttribute("statement");
        Element listElement ;
        if (hasParentWithName(element, "ul")) {
            listElement = findParentWithName(element, "ul");
        }
        else if (hasParentWithName(element, "ol")) {
            listElement = findParentWithName(element, "ol");
        }
        else {
            throw new IllegalArgumentException("List (ul or ul) not found. Maybe the executeOnList command is not in a list.");
        }
        listElement.setAttributeNS(Namespaces.CONCORDION.getUri(), "concordion:execute", statement);
        Element parent = (Element) element.getParentNode();
        parent.removeChild(element);
    }

    private boolean hasParentWithName(Element baseElement, String elementNameToFind) {
        Node parent = baseElement;
        while (((parent != null)) && (!parent.getNodeName().equals(elementNameToFind))) {
            parent = parent.getParentNode();
        }
        return (parent != null) && (parent.getNodeName().equals(elementNameToFind));
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

    private void extractAssertTrueOnParagraph(Element element) {
        String statement = element.getAttribute("statement");
        Element parent = findParentWithName(element, "p");
        parent.setAttributeNS(Namespaces.CONCORDION.getUri(), "concordion:assertTrue", statement);
        parent.removeChild(element);
    }

    private void extractAssertFalseOnParagraph(Element element) {
        String statement = element.getAttribute("statement");
        Element parent = findParentWithName(element, "p");
        parent.setAttributeNS(Namespaces.CONCORDION.getUri(), "concordion:assertFalse", statement);
        parent.removeChild(element);
    }

    private interface DomManipulateFunction {
        void manipulate(Element element);
    }
}
