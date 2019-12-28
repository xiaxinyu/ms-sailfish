package com.sailfish.beanutils;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JobBuild {
    private Long jobId;
    private Long runId;
    private String partialUri;
    private String version;
    private String packageName;
}
