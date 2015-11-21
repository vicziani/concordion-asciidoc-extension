package org.concordion.jtechlog.asciidoc.extension;

import org.concordion.api.extension.Extensions;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
@Extensions(AsciiDocConcordionExtension.class)
public class HelloWorldWithSetFixture {

    public String greetingFor(String firstName) {
        return "Hello " + firstName + "!";
    }

}
