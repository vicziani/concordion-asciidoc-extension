package org.concordion.jtechlog.asciidoc.macro;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.extension.JavaExtensionRegistry;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.isEquivalentTo;
import static org.xmlmatchers.transform.XmlConverters.the;

public class AssertEqualsTest {

    Asciidoctor asciidoctor = Asciidoctor.Factory.create();

    @Before
    public void init() {
        JavaExtensionRegistry extensionRegistry = asciidoctor.javaExtensionRegistry();
        extensionRegistry.inlineMacro(new ConcordionMacro("concordion", new HashMap<String, Object>()));
        extensionRegistry.postprocessor(new ConcordionNamespacePostProcessor());
    }

    @Test
    public void testAsciidoctorWorks() {
        String output = convert("foo");
        assertThat(the(output), isEquivalentTo(the("<div class='paragraph'><p>foo</p></div>")));
    }

    @Test
    public void testSimpleAssertEquals() {
        String output = convert("concordion:assertEquals[getGreeting(), Hello World]");
        assertThat(the(output), isEquivalentTo(the("<div class='paragraph' xmlns:concordion='http://www.concordion.org/2007/concordion'><p><span concordion:assertEquals='getGreeting()'>Hello World</span></p></div>")));
    }

    private String convert(String s) {
        return asciidoctor.convert(s, new HashMap<String, Object>());
    }
}
