package com.isbein.cloud.common.basic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "local-client")
public class LocalClientGlobalConfig {
    /**
     * 版本
     */
    private Map<Long,Integer> version = new HashMap<>();


    /**
     * 本地端版本
     */
    public Integer getLocalClientVersion(long pId){
        return version.getOrDefault(pId, null);
    }



}
