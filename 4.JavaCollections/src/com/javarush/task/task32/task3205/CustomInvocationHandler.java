package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {

    SomeInterfaceWithMethods smm;

    public CustomInvocationHandler(SomeInterfaceWithMethods sma) {
        this.smm = sma;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println(method.getName() + " in");
        Object result = method.invoke(smm,objects);
        System.out.println(method.getName() +" out");
        return result;
    }
}