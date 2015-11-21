package org.concordion.jtechlog.asciidoc.macro;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class XmlNamespaceEnhancerTest {

    @Test
    public void testAddNameSpace() {
        XmlNamespaceEnhancer xmlNamespaceEnhancer = new XmlNamespaceEnhancer();
        String output = xmlNamespaceEnhancer.addNamespace("<foo><bar concordion:assertEquals='getGreeting()'/></foo>");

        assertThat(output.indexOf("xmlns:concordion") > 0, is(true));
    }


}
