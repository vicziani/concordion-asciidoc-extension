package org.concordion.jtechlog.asciidoc.macro;

import org.asciidoctor.ast.AbstractBlock;
import org.asciidoctor.extension.InlineMacroProcessor;
import org.concordion.jtechlog.asciidoc.macro.command.*;

import java.util.Map;

// It doesn't work with hu.* package name
public class ConcordionMacro extends InlineMacroProcessor {

    public ConcordionMacro(String macroName, Map<String, Object> config) {
        super(macroName, config);
    }

    @Override
    protected Object process(AbstractBlock parent, String target, Map<String, Object> attributes) {
        return CommandType.commandForName(target).process(attributes);
    }
}
