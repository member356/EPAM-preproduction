package com.epam.khrypushyna.task7.proxy.proxyFactory;

import com.epam.khrypushyna.task7.entity.CouchImpl;
import com.epam.khrypushyna.task7.entity.ICouch;
import com.epam.khrypushyna.task7.proxy.MapInvocationHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class MapCouchProxyFactory extends CouchProxyFactory {

    @Override
    public ICouch createCouchImpl(ICouch couch) throws IllegalAccessException {

        Map<String, Object> map = new HashMap<>();
        Field[] fields = CouchImpl.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(couch));
        }

        return (ICouch) Proxy.newProxyInstance(
                CouchImpl.class.getClassLoader(),
                CouchImpl.class.getInterfaces(),
                new MapInvocationHandler(map)
        );
    }
}
