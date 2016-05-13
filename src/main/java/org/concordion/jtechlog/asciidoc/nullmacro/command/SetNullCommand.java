package org.concordion.jtechlog.asciidoc.nullmacro.command;

import org.concordion.jtechlog.asciidoc.macro.command.AttributeParser;

import java.util.Map;

public class SetNullCommand implements NullCommand {

    @Override
    public String process(Map<String, Object> attributes) {
        AttributeParser attributeParser = new AttributeParser(attributes);
        return attributeParser.getValueAt(1);
    }
}
