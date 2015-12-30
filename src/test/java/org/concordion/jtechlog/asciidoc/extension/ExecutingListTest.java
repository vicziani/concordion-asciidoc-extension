package org.concordion.jtechlog.asciidoc.extension;

import org.concordion.api.MultiValueResult;
import org.concordion.api.extension.Extensions;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(ConcordionRunner.class)
@Extensions(AsciiDocConcordionExtension.class)
public class ExecutingListTest {

    private List<MultiValueResult> treeEntries = new ArrayList<>();

    public void parseNode(String text, int level) {
        treeEntries.add(new MultiValueResult()
                .with("name", text)
                .with("level", level));
    }

    public List<MultiValueResult> getNodes() {
        return treeEntries;
    }
}
