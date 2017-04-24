package com.db.domain;

/**
 * Created by Prashant on 23-04-2017.
 */
public class Shop {

    private String shopName;
    private ShopAddress shopAddress;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public ShopAddress getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(ShopAddress shopAddress) {
        this.shopAddress = shopAddress;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopName='" + shopName + '\'' +
                "," + shopAddress +
                '}';
    }
}
