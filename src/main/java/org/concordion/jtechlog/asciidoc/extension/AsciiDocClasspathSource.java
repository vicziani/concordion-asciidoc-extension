package org.concordion.jtechlog.asciidoc.extension;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.extension.JavaExtensionRegistry;
import org.concordion.api.Resource;
import org.concordion.api.Source;
import org.concordion.jtechlog.asciidoc.macro.ConcordionMacro;
import org.concordion.internal.ClassPathSource;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AsciiDocClasspathSource implements Source {

    private final Source classPathSource = new ClassPathSource();

    @Override
    public InputStream createInputStream(Resource resource) throws IOException {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        JavaExtensionRegistry extensionRegistry = asciidoctor.javaExtensionRegistry();
        extensionRegistry.inlineMacro(new ConcordionMacro("concordion", new HashMap<String, Object>()));
        Reader reader = new InputStreamReader(classPathSource.createInputStream(resource));
        CharArrayWriter writer = new CharArrayWriter();
        Map<String, Object> options =
            OptionsBuilder.options().asMap();
        asciidoctor.convert(reader, writer, options);

        String html = wrapBody(writer.toString());

        return new ByteArrayInputStream(html.getBytes("UTF-8"));
    }

    private String wrapBody(String body) {
        return "<html xmlns:concordion='http://www.concordion.org/2007/concordion'><body>" +
                body +
                "</body></html>";
    }

    @Override
    public boolean canFind(Resource resource) {
        return classPathSource.canFind(resource);
    }
}
