package org.concordion.jtechlog.asciidoc.extension;

import org.concordion.api.extension.Extensions;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
@Extensions(AsciiDocConcordionExtension.class)
public class HelloWorldUnusualSentenceFixture {

    public String greetingFor(String name) {
        return "Hello " + name + "!";
    }

}
