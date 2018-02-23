package com.epam.khrypushyna.shop.creator;

import com.epam.khrypushyna.shop.creator.mode.Mode;
import com.epam.khrypushyna.shop.entity.Furniture;

public abstract class AddFurniture extends Creator{

    Mode mode;

    AddFurniture(Mode mode) {
        this.mode = mode;
    }

    @Override
    public void fill(Furniture item) {
        item.setPrice(mode.getPrice());
        item.setAvailability(true);
    }
}
