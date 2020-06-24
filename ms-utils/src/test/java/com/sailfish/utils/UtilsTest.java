package com.sailfish.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

@Slf4j
public class UtilsTest {

    @Test
    public void testEncodingUrl() throws UnsupportedEncodingException {
        String url = "http://build-complie.steam.crcloud.com/job/%s/reload";
        String jobName = "crcsoft-ios2019-szyd_szyd-claim_构建华润云主机pro";
        String tmp = UrlUtils.encodingUrl(jobName);
        Assert.assertNotNull(tmp);
        log.info(String.format(url, tmp));
    }
}
