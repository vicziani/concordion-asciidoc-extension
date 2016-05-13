package org.concordion.jtechlog.asciidoc.nullmacro.command;

import org.concordion.jtechlog.asciidoc.macro.command.AttributeParser;

import java.util.Map;

public class ExecuteNullCommand implements NullCommand {

    @Override
    public String process(Map<String, Object> attributes) {
        AttributeParser attributeParser = new AttributeParser(attributes);
        if (attributeParser.getSize() == 2) {
            return attributeParser.getValueAt(1);
        }
        else {
            return "";
        }
    }
}
