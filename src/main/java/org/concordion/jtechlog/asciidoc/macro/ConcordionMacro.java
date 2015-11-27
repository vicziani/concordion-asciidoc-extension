package org.concordion.jtechlog.asciidoc.macro;

import org.asciidoctor.ast.AbstractBlock;
import org.asciidoctor.extension.InlineMacroProcessor;
import org.concordion.jtechlog.asciidoc.macro.command.AssertEqualsCommand;
import org.concordion.jtechlog.asciidoc.macro.command.ExecuteCommand;
import org.concordion.jtechlog.asciidoc.macro.command.ExecuteOnParagraphCommand;
import org.concordion.jtechlog.asciidoc.macro.command.SetCommand;

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
                return new AssertEqualsCommand().process(attributes);
            case "set":
                return new SetCommand().process(attributes);
            case "execute":
                return new ExecuteCommand().process(attributes);
            case "executeOnParagraph":
                return new ExecuteOnParagraphCommand().process(attributes);
            default:
                throw new IllegalArgumentException("Invalid target: " + target);
        }
    }
}
