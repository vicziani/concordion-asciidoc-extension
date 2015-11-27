package org.concordion.jtechlog.asciidoc.macro.html;

public enum Namespaces {
    CONCORDION("concordion", "http://www.concordion.org/2007/concordion");

    private String prefix;

    private String uri;

    Namespaces(String prefix, String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getUri() {
        return uri;
    }
}
