package com.epam.khrypushyna.task7.proxy.proxyFactory;

import com.epam.khrypushyna.task7.entity.CouchImpl;
import com.epam.khrypushyna.task7.entity.ICouch;
import com.epam.khrypushyna.task7.proxy.UnmodifiableInvocationHandler;

import java.lang.reflect.Proxy;

public class UnmodifiableCouchProxyFactory extends CouchProxyFactory {

    @Override
    public ICouch createCouchImpl(ICouch couch) throws IllegalAccessException {
        return (ICouch) Proxy.newProxyInstance(
                CouchImpl.class.getClassLoader(),
                CouchImpl.class.getInterfaces(),
                new UnmodifiableInvocationHandler(couch)
        );
    }
}
