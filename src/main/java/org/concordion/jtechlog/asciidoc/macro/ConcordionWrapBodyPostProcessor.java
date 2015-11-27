package org.concordion.jtechlog.asciidoc.macro;

import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.Postprocessor;
import org.concordion.jtechlog.asciidoc.macro.html.BodyWrapper;

public class ConcordionWrapBodyPostProcessor extends Postprocessor {

    @Override
    public String process(Document document, String body) {
        return new BodyWrapper().wrap(body);
    }
}
