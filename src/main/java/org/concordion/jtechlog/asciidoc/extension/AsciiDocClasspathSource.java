package org.concordion.jtechlog.asciidoc.extension;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.extension.JavaExtensionRegistry;
import org.concordion.api.Resource;
import org.concordion.api.Source;
import org.concordion.jtechlog.asciidoc.macro.ConcordionPostProcessor;
import org.concordion.jtechlog.asciidoc.macro.ConcordionMacro;
import org.concordion.internal.ClassPathSource;
import org.concordion.jtechlog.asciidoc.macro.ConcordionWrapBodyPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AsciiDocClasspathSource implements Source {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsciiDocClasspathSource.class);

    private final Source classPathSource = new ClassPathSource();

    @Override
    public InputStream createInputStream(Resource resource) throws IOException {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        JavaExtensionRegistry extensionRegistry = asciidoctor.javaExtensionRegistry();
        extensionRegistry.inlineMacro(new ConcordionMacro("concordion", new HashMap<String, Object>()));
        extensionRegistry.postprocessor(new ConcordionWrapBodyPostProcessor());
        extensionRegistry.postprocessor(new ConcordionPostProcessor());
        Reader reader = new InputStreamReader(classPathSource.createInputStream(resource));
        CharArrayWriter writer = new CharArrayWriter();
        Map<String, Object> options =
            OptionsBuilder.options()
                    .backend("xhtml5")
                    .asMap();
        asciidoctor.convert(reader, writer, options);

        String html = writer.toString();
        LOGGER.debug("AsciiDoctorJ output:\n" + html);

        return new ByteArrayInputStream(html.getBytes("UTF-8"));
    }

    @Override
    public boolean canFind(Resource resource) {
        return classPathSource.canFind(resource);
    }
}
