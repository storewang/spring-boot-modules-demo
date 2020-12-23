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

import java.io.Serializable;

/**
 * A model copy of {@link org.springframework.boot.actuate.env.EnvironmentEndpoint.PropertyValueDescriptor}
 * A description of a property's value, including its origin if available.
 *
 * @author chen.pengzhi (chpengzh@foxmail.com)
 */
public class PropertyValueDescriptor implements Serializable {

    private static final int serialVersionUID = 0x11;

    private String           origin;

    private Object           value;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}