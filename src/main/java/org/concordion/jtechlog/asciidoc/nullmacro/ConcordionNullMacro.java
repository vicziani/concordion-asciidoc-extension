package org.concordion.jtechlog.asciidoc.nullmacro;

import org.asciidoctor.ast.AbstractBlock;
import org.asciidoctor.extension.InlineMacroProcessor;
import org.concordion.jtechlog.asciidoc.macro.command.CommandType;

import java.util.Map;

public class ConcordionNullMacro extends InlineMacroProcessor {

    public ConcordionNullMacro(String macroName, Map<String, Object> config) {
        super(macroName, config);
    }

    @Override
    protected Object process(AbstractBlock parent, String target, Map<String, Object> attributes) {
        return CommandType.commandForName(target).process(attributes);
    }
}
