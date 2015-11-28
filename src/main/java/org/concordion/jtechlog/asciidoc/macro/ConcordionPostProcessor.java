package org.concordion.jtechlog.asciidoc.macro;

import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.Postprocessor;
import org.concordion.jtechlog.asciidoc.macro.html.HtmlTagExtractor;

public class ConcordionPostProcessor extends Postprocessor {

    @Override
    public String process(Document document, String s) {
        return new HtmlTagExtractor().extractExecuteOnParagraphs(s);
    }
}
