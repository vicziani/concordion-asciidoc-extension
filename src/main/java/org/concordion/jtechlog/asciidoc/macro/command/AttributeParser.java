package org.concordion.jtechlog.asciidoc.macro.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttributeParser {

    private Map<String, Object> attributes;

    private List<String> values;

    public AttributeParser(Map<String, Object> attributes) {
        this.attributes = attributes;
        String[] attrValues = ((String) attributes.get("text")).split("\\,");
        values = new ArrayList<>();
        for (String attrValue: attrValues) {
            values.add(attrValue.trim());
        }
    }

    public String getValueAt(int i) {
        return values.get(i);
    }
}
