package org.concordion.jtechlog.asciidoc.macro;

import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.Postprocessor;

public class ConcordionNamespacePostProcessor extends Postprocessor {

    private XmlNamespaceEnhancer xmlNamespaceEnhancer = new XmlNamespaceEnhancer();

    @Override
    public String process(Document document, String output) {
        return xmlNamespaceEnhancer.addNamespace(output);
    }
}
