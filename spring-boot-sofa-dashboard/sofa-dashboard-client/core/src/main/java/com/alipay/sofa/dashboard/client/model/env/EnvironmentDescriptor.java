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
package com.alipay.sofa.dashboard.client.model.env;

import org.springframework.core.env.Environment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A model copy of {@link org.springframework.boot.actuate.env.EnvironmentEndpoint.EnvironmentDescriptor}
 * A description of an {@link Environment}.
 *
 * @author chen.pengzhi (chpengzh@foxmail.com)
 */
public class EnvironmentDescriptor implements Serializable {

    private static final int                     serialVersionUID = 0x11;

    private final List<String>                   activeProfiles   = new ArrayList<>();

    private final List<PropertySourceDescriptor> propertySources  = new ArrayList<>();

    public List<String> getActiveProfiles() {
        return this.activeProfiles;
    }

    public List<PropertySourceDescriptor> getPropertySources() {
        return this.propertySources;
    }

}