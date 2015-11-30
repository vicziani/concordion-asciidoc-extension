package org.concordion.jtechlog.asciidoc.extension;

import org.concordion.api.extension.Extensions;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@RunWith(ConcordionRunner.class)
@Extensions(AsciiDocConcordionExtension.class)
public class PartialMatchesTest {

    private Set<String> usernamesInSystem = new HashSet<>();

    public void setUpUser(String username) {
        usernamesInSystem.add(username);
    }

    public Iterable<String> getSearchResultsFor(String searchString) {
        SortedSet<String> matches = new TreeSet<>();
        for (String username : usernamesInSystem) {
            if (username.contains(searchString)) {
                matches.add(username);
            }
        }
        return matches;
    }
}
