package org.concordion.jtechlog.asciidoc.macro.command;

import java.util.Map;

public class VerifyRowsCommand implements Command {

    @Override
    public String process(Map<String, Object> attributes) {
        AttributeParser attributeParser = new AttributeParser(attributes);
        String statement = attributeParser.getValueAt(0);
        return String.format("<concordion:verifyRows statement='%s' />",
                statement);
    }
}
