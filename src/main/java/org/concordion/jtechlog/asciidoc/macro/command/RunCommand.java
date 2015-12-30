package org.concordion.jtechlog.asciidoc.macro.command;

import java.util.Map;

public class RunCommand implements Command {

    @Override
    public String process(Map<String, Object> attributes) {
        AttributeParser attributeParser = new AttributeParser(attributes);
        return String.format("<a concordion:run='concordion' href='%s'>%s</a>",
                attributeParser.getValueAt(0).replace(".adoc", ".html"),
                attributeParser.getValueAt(1));
    }
}
