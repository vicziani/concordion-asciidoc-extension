package org.concordion.jtechlog.asciidoc.macro.command;

import java.util.Map;

public class ExecuteCommand {
    public String process(Map<String, Object> attributes) {
        AttributeParser attributeParser = new AttributeParser(attributes);
        return String.format("<span concordion:execute='%s' />",
                attributeParser.getValueAt(0));
    }
}
