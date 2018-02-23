package com.epam.khrypushyna.shop.creator;

import com.epam.khrypushyna.shop.entity.Furniture;

public abstract class Creator {

    protected abstract void fill(Furniture item);

    protected abstract Furniture getInstance();

    public Furniture create(){
        Furniture item = getInstance();
        fill(item);
        return item;
    }
}
