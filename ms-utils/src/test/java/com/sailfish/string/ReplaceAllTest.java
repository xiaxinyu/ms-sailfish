package com.sailfish.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class ReplaceAllTest {
    @Test
    public void testReplaceAll() {
        //初步判断凭证是否相等，从Jenkins返回的credentialVOName的格式为: root(devopsci-backend)
        Assert.assertTrue(isSameCredentialName("root(devopsci-backend)", "devopsci-backend", "root"));
        Assert.assertFalse(isSameCredentialName("root-x(devopsci-backend)", "devopsci-backend", "root"));
        Assert.assertFalse(isSameCredentialName("root(devopsci-backend)", "devopsci-backend", "root-x"));
        Assert.assertFalse(isSameCredentialName("root(devopsci-backend)", "devopsci-backend-x", "root"));
    }

    private boolean isSameCredentialName(String credentialNameVO, String credentialName, String userName) {
        boolean result = false;
        if (credentialNameVO.contains(credentialName)) {
            String cleanCredentialNameVO = credentialNameVO.replaceAll(userName, StringUtils.EMPTY);
            cleanCredentialNameVO = cleanCredentialNameVO.replaceAll("\\(|\\)", StringUtils.EMPTY);
            cleanCredentialNameVO = StringUtils.trim(cleanCredentialNameVO);
            System.out.println(String.format("凭证用户名匹配：cleanCredentialNameVO = %s, credentialName = %s", cleanCredentialNameVO, credentialName));
            if (StringUtils.isNotBlank(cleanCredentialNameVO) && StringUtils.isNotBlank(credentialName)) {
                if (credentialName.equals(cleanCredentialNameVO)) {
                    result = true;
                }
            }
        }
        return result;
    }
}
