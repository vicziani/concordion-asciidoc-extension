package org.concordion.jtechlog.asciidoc.macro.command;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AttributeParserTest {

    @Test
    public void testParse() {
        AttributeParser attributeParser = create("foo,bar");
        assertThat(attributeParser.getValueAt(0), is("foo"));
        assertThat(attributeParser.getValueAt(1), is("bar"));
    }

    @Test
    public void testParseWithWhitespaces() {
        AttributeParser attributeParser = create("   foo  , bar    ");
        assertThat(attributeParser.getValueAt(0), is("foo"));
        assertThat(attributeParser.getValueAt(1), is("bar"));
    }

    private AttributeParser create(String text) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("text", text);
        return new AttributeParser(attributes);
    }
}
