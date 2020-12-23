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
package com.alipay.sofa.dashboard.client.model.health;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A model copy of {@link org.springframework.boot.actuate.health.Health}
 *
 * @author chen.pengzhi (chpengzh@foxmail.com)
 */
public class HealthDescriptor implements Serializable {

    private static final int              serialVersionUID = 0x11;

    private static final transient String UNKNOWN_STATUS   = "UNKNOWN";

    private String                        status;

    private final Map<String, Object>     details          = new HashMap<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

}
