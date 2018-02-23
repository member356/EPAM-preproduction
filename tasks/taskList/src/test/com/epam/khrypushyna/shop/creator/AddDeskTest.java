package com.epam.khrypushyna.shop.creator;

import com.epam.khrypushyna.shop.creator.mode.Mode;
import com.epam.khrypushyna.shop.entity.Desk;
import com.epam.khrypushyna.shop.entity.Furniture;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddDeskTest {

    private Mode modeMock = mock(Mode.class);
    private Furniture item = new Desk();
    private AddDesk addDesk = new AddDesk(modeMock);

    @Before
    public void before(){
        when(modeMock.getHeight()).thenReturn(100);
        when(modeMock.getPrice()).thenReturn(120);
    }

    @Test
    public void shouldSetInstanceFieldsWhenFill(){
        addDesk.fill(item);
        verify(modeMock).getPrice();
        verify(modeMock).getHeight();

        assertFalse(item.getPrice() == 0);
        assertFalse(((Desk)item).getHeight() == 0);
    }

}