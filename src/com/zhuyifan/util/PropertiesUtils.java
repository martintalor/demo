package com.zhuyifan.util;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/** 
* @Author zhuyifan
* @Time 2019年6月4日 上午9:30:36 
* @Package com.iflytek.dep.server.service.scheduled
* @Description: 获取配置属性工具
* @Version 1.0
* <p>Description:PropertiesUtils.java:</p>
*/
@Component
public class PropertiesUtils implements EmbeddedValueResolverAware {
    private  StringValueResolver valueResolver;

    public  String getPropertiesValue(String key) {
        return valueResolver.resolveStringValue("${" + key + "}");
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.valueResolver = stringValueResolver;
    }
}
