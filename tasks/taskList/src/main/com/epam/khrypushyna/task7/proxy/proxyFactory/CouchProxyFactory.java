package com.epam.khrypushyna.task7.proxy.proxyFactory;

import com.epam.khrypushyna.task7.entity.ICouch;

public abstract class CouchProxyFactory {

    public abstract ICouch createCouchImpl(ICouch couch) throws IllegalAccessException;
}
