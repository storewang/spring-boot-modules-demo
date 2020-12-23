package com.ai.spring.sofa.ark.module;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/11
 * @Version 1.0
 **/

public class Hessian4Service {
    public byte[] serialize(Object obj) throws IOException {
        if(obj==null) {
            throw new NullPointerException();
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(obj);
        return os.toByteArray();
    }

    public Object deserialize(byte[] by) throws IOException {
        if(by==null) {
            throw new NullPointerException();
        }

        ByteArrayInputStream is = new ByteArrayInputStream(by);
        HessianInput hi = new HessianInput(is);
        return hi.readObject();
    }
}
