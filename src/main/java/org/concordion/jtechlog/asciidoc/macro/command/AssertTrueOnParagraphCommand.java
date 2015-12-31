package org.concordion.jtechlog.asciidoc.macro.command;

import java.util.Map;

public class AssertTrueOnParagraphCommand implements Command {

    @Override
    public String process(Map<String, Object> attributes) {
        AttributeParser attributeParser = new AttributeParser(attributes);
        return String.format("<concordion:assertTrueOnParagraph statement='%s' />",
                attributeParser.getValueAt(0));
    }
}
