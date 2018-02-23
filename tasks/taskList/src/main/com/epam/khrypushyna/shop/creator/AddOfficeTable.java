package com.epam.khrypushyna.shop.creator;

import com.epam.khrypushyna.shop.creator.mode.Mode;
import com.epam.khrypushyna.shop.entity.Furniture;
import com.epam.khrypushyna.shop.entity.OfficeTable;

public class AddOfficeTable extends AddDesk {

    public AddOfficeTable(Mode mode) {
        super(mode);
    }

    @Override
    protected Furniture getInstance() {
        return new OfficeTable();
    }

    @Override
    public void fill(Furniture item) {
        super.fill(item);
        OfficeTable officeTable = (OfficeTable) item;
        officeTable.setCases(mode.getBoolean());
    }
}
