package com.epam.khrypushyna.shop.creator;

import com.epam.khrypushyna.shop.creator.mode.Mode;
import com.epam.khrypushyna.shop.entity.Furniture;
import com.epam.khrypushyna.shop.entity.OfficeTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddOfficeTableTest {

    private Mode modeMock = mock(Mode.class);
    private Furniture item = new OfficeTable();
    private AddOfficeTable addOfficeTable = new AddOfficeTable(modeMock);

    @Before
    public void before(){
        when(modeMock.getHeight()).thenReturn(100);
        when(modeMock.getPrice()).thenReturn(120);
        when(modeMock.getBoolean()).thenReturn(true);
    }

    @Test
    public void shouldSetInstanceFieldsWhenFill(){
        addOfficeTable.fill(item);
        verify(modeMock).getPrice();
        verify(modeMock).getHeight();
        verify(modeMock).getBoolean();

        assertFalse(item.getPrice() == 0);
        assertFalse(((OfficeTable)item).getHeight() == 0);
        assertFalse(!((OfficeTable)item).isCases());
    }

}