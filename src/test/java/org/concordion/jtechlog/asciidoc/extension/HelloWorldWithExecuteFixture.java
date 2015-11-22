package org.concordion.jtechlog.asciidoc.extension;

import org.concordion.api.extension.Extensions;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
@Extensions(AsciiDocConcordionExtension.class)
public class HelloWorldWithExecuteFixture {

    public String greeting;

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String greetingFor(String name) {
        return greeting + " " + name;
    }
}
