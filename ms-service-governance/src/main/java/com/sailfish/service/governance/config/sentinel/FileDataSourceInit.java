package com.sailfish.service.governance.config.sentinel;
import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
/**
 * @Description: 文件模式数据源
 * @param:
 * @return:
 * @author: fanjc
 * @Date: 2019/5/15
 */
public class FileDataSourceInit implements InitFunc {
    @Override
    public void init() throws Exception {
        // A fake path.
        String flowRuleDir = System.getProperty("user.home") + "/sentinel/rules";
        String flowRuleFile = "flowRule.json";
        String flowRulePath = flowRuleDir + "/" + flowRuleFile;

        ReadableDataSource<String, List<FlowRule>> ds = new FileRefreshableDataSource<>(
                flowRulePath, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {})
        );
        // Register to flow rule manager.
        FlowRuleManager.register2Property(ds.getProperty());

        WritableDataSource<List<FlowRule>> wds = new FileWritableDataSource<>(flowRulePath, this::encodeJson);
        // Register to writable data source registry so that rules can be updated to file
        // when there are rules pushed from the Sentinel Dashboard.
        WritableDataSourceRegistry.registerFlowDataSource(wds);
    }

    private <T> String encodeJson(T t) {
        return JSON.toJSONString(t);
    }
}
