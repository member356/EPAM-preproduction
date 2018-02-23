package com.epam.khrypushyna.shop.creator;

import com.epam.khrypushyna.shop.creator.mode.Mode;
import com.epam.khrypushyna.shop.entity.Desk;
import com.epam.khrypushyna.shop.entity.Furniture;

public class AddDesk extends AddFurniture {

    public AddDesk(Mode mode) {
        super(mode);
    }

    @Override
    protected Furniture getInstance() {
        return new Desk();
    }

    @Override
    public void fill(Furniture item) {
        super.fill(item);
        Desk desk = (Desk) item;
        desk.setHeight(mode.getHeight());
    }
}
