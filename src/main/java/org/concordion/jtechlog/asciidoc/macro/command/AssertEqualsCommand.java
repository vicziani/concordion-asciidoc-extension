package org.concordion.jtechlog.asciidoc.macro.command;

import java.util.Map;

public class AssertEqualsCommand {

    public String process(Map<String, Object> attributes) {
        AttributeParser attributeParser = new AttributeParser(attributes);
        return String.format("<span concordion:assertEquals='%s'>%s</span>",
                attributeParser.getValueAt(0),
                attributeParser.getValueAt(1));
    }
}
