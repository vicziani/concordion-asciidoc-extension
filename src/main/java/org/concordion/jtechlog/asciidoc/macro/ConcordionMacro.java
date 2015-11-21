package org.concordion.jtechlog.asciidoc.macro;

import org.asciidoctor.ast.AbstractBlock;
import org.asciidoctor.extension.InlineMacroProcessor;

import java.util.Map;

// It doesn't work with hu.* package name
public class ConcordionMacro extends InlineMacroProcessor {

    public ConcordionMacro(String macroName, Map<String, Object> config) {
        super(macroName, config);
    }

    @Override
    protected Object process(AbstractBlock parent, String target, Map<String, Object> attributes) {
        switch (target) {
            case "assertEquals":
                String[] attrValues = ((String) attributes.get("text")).split("\\,");

                return String.format("<span concordion:assertEquals=\"%s\">%s</span>", attrValues[0].trim(), attrValues[1].trim());
            default:
                throw new IllegalArgumentException("Invalid target: " + target);
        }
    }
}
