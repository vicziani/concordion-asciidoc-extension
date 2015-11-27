package org.concordion.jtechlog.asciidoc.macro;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.extension.JavaExtensionRegistry;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.isEquivalentTo;
import static org.xmlmatchers.transform.XmlConverters.the;

public class ExampleCssClassTest {

    Asciidoctor asciidoctor = Asciidoctor.Factory.create();

    @Before
    public void init() {
        JavaExtensionRegistry extensionRegistry = asciidoctor.javaExtensionRegistry();
        extensionRegistry.inlineMacro(new ConcordionMacro("concordion", new HashMap<String, Object>()));
    }

    @Test
    public void testExampleWithDot() {
        String output = convert("[.example]\nfoo");
        assertThat(the(output), isEquivalentTo(the("<div class='paragraph example'><p>foo</p></div>")));
    }

    @Test
    public void testExampleWithRole() {
        String output = convert("[role='example']\nfoo");
        assertThat(the(output), isEquivalentTo(the("<div class='paragraph example'><p>foo</p></div>")));
    }

    private String convert(String s) {
        return asciidoctor.convert(s, new HashMap<String, Object>());
    }
}
