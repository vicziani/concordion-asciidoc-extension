package org.concordion.jtechlog.asciidoc.macro.command;

import java.util.Map;

public class ExecuteCommand {
    public String process(Map<String, Object> attributes) {
        AttributeParser attributeParser = new AttributeParser(attributes);
        if (attributeParser.getSize() == 1) {
            return String.format("<span concordion:execute='%s' />",
                    attributeParser.getValueAt(0));
        }
        else {
            return String.format("<span concordion:execute='%s'>%s</span>",
                    attributeParser.getValueAt(0),
                    attributeParser.getValueAt(1));
        }
    }
}
