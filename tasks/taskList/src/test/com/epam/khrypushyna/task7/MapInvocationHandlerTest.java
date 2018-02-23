package com.epam.khrypushyna.task7;

import com.epam.khrypushyna.task7.entity.CouchImpl;
import com.epam.khrypushyna.task7.entity.ICouch;
import com.epam.khrypushyna.task7.proxy.proxyFactory.MapCouchProxyFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapInvocationHandlerTest {

    private ICouch couchProxy;

    @Before
    public void before() throws IllegalAccessException {
        ICouch couch = new CouchImpl(120, true, "material 4");
        couchProxy = new MapCouchProxyFactory().createCouchImpl(couch);
    }

    @Test
    public void shouldReturnMaterialWhenGetMaterial(){
        assertNotNull(couchProxy.getMaterial());
    }

    @Test
    public void shouldChangePriceWhenGetPrice(){
        final Integer prevPrice = couchProxy.getPrice();
        couchProxy.setPrice(9);
        assertFalse(prevPrice.equals(couchProxy.getPrice()));
    }

    @Test
    public void shouldChangeMaterialWhenGetPrice(){
        final String  prevMaterial = couchProxy.getMaterial();
        couchProxy.setMaterial("njnjnj");
        assertFalse(prevMaterial.equals(couchProxy.getMaterial()));
    }

    @Test
    public void shouldReturnFalseWhenCallEqualsUsingProxy(){
        assertFalse(couchProxy.equals(new CouchImpl()));
    }

}