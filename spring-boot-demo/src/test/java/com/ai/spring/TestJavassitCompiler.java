package com.ai.spring;

import javassist.*;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/4
 * @Version 1.0
 **/

public class TestJavassitCompiler {
    public static void main(String[] args) throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("com.alibaba.dubbo.demo.test.Emp");
        //添加属性:private String name
         CtField nameField = new CtField(pool.getCtClass("java.lang.String"), "name", ctClass);
         nameField.setModifiers(Modifier.PRIVATE);
         ctClass.addField(nameField);
         //添加属性:private int account
        CtField accountField = new CtField(pool.getCtClass("int"), "account", ctClass);
        accountField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(accountField);
        //getter和setter
        ctClass.addMethod(CtNewMethod.getter("getName", nameField));
        ctClass.addMethod(CtNewMethod.setter("setName", nameField));
        ctClass.addMethod(CtNewMethod.getter("getAccount", accountField));
        ctClass.addMethod(CtNewMethod.setter("setAccount", accountField));
        //创建构造器
        CtConstructor ctConstructor = new CtConstructor(new CtClass[] {}, ctClass);
        String body = new StringBuilder("{\nthis.account = 1;\nthis.name = \"xiaona\";\n}").toString();
        ctConstructor.setBody(body);
        ctClass.addConstructor(ctConstructor);
        //普通方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "commonMethod", new CtClass[] {}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        ctMethod.setBody(new StringBuilder("{\n System.out.println(\"haha\"); \n}").toString());
        ctClass.addMethod(ctMethod);

        //方法调用
        Class<?> clazz = ctClass.toClass();
        Object obj = clazz.newInstance();
        obj.getClass().getMethod("commonMethod", new Class[] {}).invoke(obj, new Object[] {});

        //将字节码输出到文件中
//        byte[] codeByteArray = ctClass.toBytecode();
//        FileOutputStream fos = new FileOutputStream(new File("/Users/jigangzhao/Desktop/Emp.class"));
//        fos.write(codeByteArray);
//        fos.close();
    }
}
