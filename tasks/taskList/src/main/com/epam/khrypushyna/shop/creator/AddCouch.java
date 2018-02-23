package com.epam.khrypushyna.shop.creator;

import com.epam.khrypushyna.shop.creator.mode.Mode;
import com.epam.khrypushyna.shop.entity.Couch;
import com.epam.khrypushyna.shop.entity.Furniture;

public class AddCouch extends AddFurniture {

    public AddCouch(Mode mode) {
        super(mode);
    }

    @Override
    protected Furniture getInstance() {
        return new Couch();
    }

    @Override
    public void fill(Furniture item) {
        super.fill(item);
        Couch couch = (Couch) item;
        couch.setMaterial(mode.getMaterial());
    }
}
