package org.concordion.jtechlog.asciidoc.extension;

import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;

public class AsciiDocConcordionExtension implements ConcordionExtension {

    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender
                .withSpecificationLocator(new AsciiDocLocator())
                .withSource(new AsciiDocClasspathSource())
                .withTarget(new AsciiDocSuffixRenamingTarget());
    }
}
