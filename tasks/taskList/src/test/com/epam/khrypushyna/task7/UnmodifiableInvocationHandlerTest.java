package com.epam.khrypushyna.task7;

import com.epam.khrypushyna.task7.entity.CouchImpl;
import com.epam.khrypushyna.task7.entity.ICouch;
import com.epam.khrypushyna.task7.proxy.proxyFactory.UnmodifiableCouchProxyFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UnmodifiableInvocationHandlerTest {

    private ICouch couchProxy;

    @Before
    public void before() throws IllegalAccessException {
        ICouch couch = new CouchImpl(120, true, "material 4");
        couchProxy = new UnmodifiableCouchProxyFactory().createCouchImpl(couch);
    }

    @Test
    public void shouldGetAccessToNotSetterPriceWhenInvoke() throws Throwable {
        assertNotNull(couchProxy.getPrice());
    }

    @Test
    public void shouldGetAccessToNotSetterMaterialWhenInvoke() throws Throwable {
        assertNotNull(couchProxy.getMaterial());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionWhenInvokeIntoSetterPrice() throws Throwable {
        couchProxy.setPrice(120);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionWhenInvokeIntoSetterMaterial() throws Throwable {
        couchProxy.setMaterial("bhbhbh");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionWhenInvokeIntoSetterAvailability() throws Throwable {
        couchProxy.setAvailability(false);
    }
}