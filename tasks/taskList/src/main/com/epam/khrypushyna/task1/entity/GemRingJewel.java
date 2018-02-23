package com.epam.khrypushyna.task1.entity;

public class GemRingJewel extends RingJewel{

    private String gem;

    public GemRingJewel() {
    }

    public GemRingJewel(String material, int price, boolean availability, int size, String gem) {
        super(material, price, availability, size);
        this.gem = gem;
    }

    public String getGem() {
        return gem;
    }

    public void setGem(String gem) {
        this.gem = gem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GemRingJewel that = (GemRingJewel) o;

        return gem != null ? gem.equals(that.gem) : that.gem == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (gem != null ? gem.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GemRingJewel{" +
                "gem='" + gem + '\'' +
                ", size" + getSize();
    }
}
