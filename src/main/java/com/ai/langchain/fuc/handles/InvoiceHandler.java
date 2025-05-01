package com.ai.langchain.fuc.handles;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
@Slf4j
public class InvoiceHandler {
    @Tool("根据用户提交的开票信息进行开票")
    public String handle(@P("公司名称") String companyName, @P("公司税号")String dutyNumber, @P("金额保留两位有效数字") String amount) {
        log.info("companyName =>>>> {} dutyNumber =>>>> {} amount =>>>> {}", companyName, dutyNumber, amount);
        return "开票成功";
    }
}
