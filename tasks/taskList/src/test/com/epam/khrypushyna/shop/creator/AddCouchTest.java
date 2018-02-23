package com.epam.khrypushyna.shop.creator;

import com.epam.khrypushyna.shop.creator.mode.Mode;
import com.epam.khrypushyna.shop.entity.Couch;
import com.epam.khrypushyna.shop.entity.Furniture;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddCouchTest {

    private Mode modeMock = mock(Mode.class);
    private Furniture item = new Couch();
    private AddCouch addCouch = new AddCouch(modeMock);

    @Before
    public void before(){
        when(modeMock.getMaterial()).thenReturn("testMaterial");
        when(modeMock.getPrice()).thenReturn(120);
    }

    @Test
    public void shouldSetInstanceFieldsWhenFill(){
        addCouch.fill(item);
        verify(modeMock).getPrice();
        verify(modeMock).getMaterial();
        assertNotNull(((Couch)item).getMaterial());
        assertFalse(item.getPrice() == 0);
    }

}