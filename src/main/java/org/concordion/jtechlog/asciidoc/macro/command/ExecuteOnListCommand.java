package org.concordion.jtechlog.asciidoc.macro.command;

import java.util.Map;

public class ExecuteOnListCommand implements Command {
    public String process(Map<String, Object> attributes) {
        AttributeParser attributeParser = new AttributeParser(attributes);
        String statement = attributeParser.getValueAt(0);
        return String.format("<concordion:executeOnList statement='%s' />",
                statement);
    }
}
