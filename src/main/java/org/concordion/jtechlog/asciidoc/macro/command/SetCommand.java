package org.concordion.jtechlog.asciidoc.macro.command;

import java.util.Map;

public class SetCommand implements Command {
    public String process(Map<String, Object> attributes) {
        AttributeParser attributeParser = new AttributeParser(attributes);
        return String.format("<span concordion:set='%s'>%s</span>",
                attributeParser.getValueAt(0),
                attributeParser.getValueAt(1));
    }
}
