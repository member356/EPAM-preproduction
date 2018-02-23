package com.epam.khrypushyna.shop.creator.mode;

import java.util.Random;

public class AutoMode implements Mode {

    private Random random = new Random();

    @Override
    public String getMaterial() {
        return "material " + generateRandom(1, 30);
    }

    @Override
    public int getPrice() {
        return generateRandom(300, 15000);
    }

    @Override
    public int getHeight() {
        return generateRandom(80, 160);
    }

    @Override
    public boolean getBoolean() {
        return random.nextBoolean();
    }

    private int generateRandom(int from, int to) {
        return from + random.nextInt(to);
    }
}
