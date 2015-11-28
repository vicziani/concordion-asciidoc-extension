package org.concordion.jtechlog.asciidoc.macro.command;

import java.util.Map;

public interface Command {

    String process(Map<String, Object> attributes);
}
