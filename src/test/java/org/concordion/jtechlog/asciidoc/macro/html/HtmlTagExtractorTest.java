package org.concordion.jtechlog.asciidoc.macro.html;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.isEquivalentTo;
import static org.xmlmatchers.transform.XmlConverters.the;

public class HtmlTagExtractorTest {

    @Test
    public void testExtractExecuteOnParagraphs() {
        HtmlTagExtractor htmlTagExtractor = new HtmlTagExtractor();
        String output = htmlTagExtractor.extractExecuteOnParagraphs("<html xmlns:concordion='http://www.concordion.org/2007/concordion'><p><concordion:executeOnParagraph statement='foo' />bar</p></html>");
        assertThat(the(output), isEquivalentTo(the("<html xmlns:concordion='http://www.concordion.org/2007/concordion'><p concordion:execute='foo'>bar</p></html>")));
    }
}
