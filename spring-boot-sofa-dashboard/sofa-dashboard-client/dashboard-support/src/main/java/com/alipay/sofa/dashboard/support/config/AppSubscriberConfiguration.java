/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.dashboard.support.config;

import com.alipay.sofa.dashboard.client.registry.AppSubscriber;
import com.alipay.sofa.dashboard.client.registry.zookeeper.ZookeeperAppSubscriber;
import com.alipay.sofa.dashboard.client.registry.zookeeper.ZookeeperRegistryConfig;
import com.alipay.sofa.dashboard.support.properties.SofaDashboardZookeeperProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chen.pengzhi (chpengzh@foxmail.com)
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties({ SofaDashboardZookeeperProperties.class })
public class AppSubscriberConfiguration {

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public AppSubscriber<?> getSubscriber(SofaDashboardZookeeperProperties prop) {
        ZookeeperRegistryConfig config = new ZookeeperRegistryConfig();
        config.setAddress(prop.getAddress());
        config.setBaseSleepTimeMs(prop.getBaseSleepTimeMs());
        config.setMaxRetries(prop.getMaxRetries());
        config.setSessionTimeoutMs(prop.getSessionTimeoutMs());
        config.setConnectionTimeoutMs(prop.getConnectionTimeoutMs());

        ZookeeperAppSubscriber subscriber = new ZookeeperAppSubscriber(config);
        subscriber.start();
        return subscriber;
    }

}
