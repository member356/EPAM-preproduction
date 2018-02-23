package com.epam.khrypushyna.task7.proxy;

import com.epam.khrypushyna.task7.entity.ICouch;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapInvocationHandler implements InvocationHandler{

    private static final String NAME_OF_METHOD_GET = "get";
    private static final String NAME_OF_METHOD_SET = "set";
    private static final String NAME_OF_METHOD_IS = "is";
    private static final String NAME_OF_METHOD_EQUALS = "equals";
    private Map<String, Object> proxyMap = new HashMap<>();

    public MapInvocationHandler(Map<String, Object> map) {
        this.proxyMap.putAll(map);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (methodName.startsWith(NAME_OF_METHOD_GET)) {
            return proxyMap.get(methodName.substring(3).toLowerCase());
        }
        else if (methodName.startsWith(NAME_OF_METHOD_SET)) {
            return proxyMap.put(method.getName().substring(3).toLowerCase(), args[0]);
        }
        else if (methodName.startsWith(NAME_OF_METHOD_IS)) {
            return proxyMap.get(methodName.substring(2).toLowerCase());
        }
        else if(methodName.equals(NAME_OF_METHOD_EQUALS)){
            if (proxy == args[0]) return true;
            if (args[0] == null || getClass() != proxy.getClass()) return false;

            ICouch o = (ICouch) proxy;
            ICouch b = (ICouch) args[0];
            return o.getPrice() == b.getPrice() &&
                    (o.isAvailability() == b.isAvailability() &&
                    Objects.equals(o.getMaterial(), b.getMaterial()));
        }
        return method.invoke(proxy, args);
    }
}
