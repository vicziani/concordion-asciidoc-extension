package org.concordion.jtechlog.asciidoc.macro.html;

public class BodyWrapper {

    public String wrap(String body) {
        return "<html xmlns:concordion='http://www.concordion.org/2007/concordion'><body>" +
                body +
                "</body></html>";
    }
}
