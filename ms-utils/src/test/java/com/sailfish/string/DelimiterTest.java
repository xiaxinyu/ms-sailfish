package com.sailfish.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DelimiterTest {
    @Test
    public void testReplaceAll() {
        String logs = "summer\nautumn\nspring";

        Map<String,String> segments1 = handleSegment(logs, 2);
        System.out.println(segments1);

        System.out.println("=============================");

        Map<String,String> segments2 = handleSegment(logs, 5);
        System.out.println(segments2);
    }

    private Map<String, String> handleSegment(String logText, Integer segmentSize) {
        String key1 = "segment1", key2 = "segment2";
        String delimiter = "\n";
        Map<String, String> result = new HashMap<String, String>() {{
            put(key1, StringUtils.EMPTY);
            put(key2, StringUtils.EMPTY);
        }};
        if (StringUtils.isNotBlank(logText)) {
            // String[] lines = text.split("\\r?\\n");
            String[] logs = logText.split(delimiter);
            if (logs.length <= segmentSize) {
                result.put(key1, StringUtils.join(logs, delimiter));
            } else {
                String[] logs1 = Arrays.copyOfRange(logs, 0, segmentSize);
                String[] logs2 = Arrays.copyOfRange(logs, segmentSize, logs.length);
                result.put(key1, StringUtils.join(logs1, delimiter));
                result.put(key2, StringUtils.join(logs2, delimiter));
            }
        }
        return result;
    }
}
