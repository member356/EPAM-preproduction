package com.epam.khrypushyna.task7.proxy;

import com.epam.khrypushyna.task7.entity.ICouch;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UnmodifiableInvocationHandler implements InvocationHandler {

    private ICouch couch;

    public UnmodifiableInvocationHandler(ICouch couch) {
        this.couch = couch;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("set")) {
            throw new UnsupportedOperationException("Setters in CouchImpl.class are not available");
        }
        return method.invoke(couch, args);
    }
}
