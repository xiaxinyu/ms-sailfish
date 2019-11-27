package com.sailfish.match;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MatchedTest {
    private final AntPathMatcher matcher = new AntPathMatcher();

    @Test
    public void testFilter() {
        String uri = "/v1/git/applications/765/branches/feature/newMenu2/commits";
        List<String> permissionDOS = new ArrayList<String>() {{
            add("/v1/git/applications/{applicationId}/branches");
            add("/v1/git/applications/{applicationId}/branches/{name}/commits");
            add("/v1/git/applications/{applicationId}/branches/{name}/commits/total");
            add("/v1/git/applications/{applicationId}/commits/{sha}");
        }};

        List<String> matchPermissions = permissionDOS.stream().filter(t -> matcher.match(t, uri))
                .sorted((String o1, String o2) -> {
                    Comparator<String> patternComparator = matcher.getPatternComparator(uri);
                    return patternComparator.compare(o1, o2);
                }).collect(Collectors.toList());


        Assert.assertTrue(matchPermissions.size() == 1);
    }
}
