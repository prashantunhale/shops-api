package com.db.service;

import com.db.domain.Message;
import com.db.domain.Shop;
import com.db.utils.GoogleMapsAPI;
import com.db.utils.HaversineDistanceCalculator;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Service layer for shops api.
 * Created by Prashant on 23-04-2017.
 */
@Service
public class InMemoryShopRepository {
    private GoogleMapsAPI googleMapsAPI = new GoogleMapsAPI();
    private final ConcurrentMap<String, Shop> shops = new ConcurrentHashMap<>();

    public Iterable<Shop> getAllShops(){
        return this.shops.values();
    }

    /**
     * Add new shop
     * If shop already exists then override and let user know about previous version
     * Also call google api and store co-ordinates
     * @param shop
     * @return
     */
    public Message addShop(Shop shop) {
        String address = shop.getShopAddress().getNumber() + " " + shop.getShopAddress().getPostCode();
        try{
            shop.getShopAddress().setCoordinates(fetchCoordindates(address));
        }catch(Exception e){
            return new Message("Could not add shop because of invalid address : " + address);
        }

        Message result = new Message();

        if(shops.containsKey(shop.getShopName())){
            Shop oldShop = shops.get(shop.getShopName());
            shops.put(shop.getShopName(), shop);
            result.setMessage("Successfully replaced the shop. Older version was: " + oldShop);
        }else{
            shops.put(shop.getShopName(), shop);
            result.setMessage("Successfully added the shop.");
        }
        return result;
    }

    private LatLng fetchCoordindates(String address) throws Exception{
        return googleMapsAPI.getDetails(address);
    }

    /**
     * Find the nearest shop
     * @param latitude
     * @param longitude
     * @return
     */
    public Message getClosestShop(double latitude, double longitude){
        LatLng customerLocation = new LatLng(latitude, longitude);

        double minimum = Double.MAX_VALUE;
        Shop closest = null;

        for(String key : shops.keySet()){
            LatLng shopLocation = shops.get(key).getShopAddress().getCoordinates();
            double distance = HaversineDistanceCalculator.calculate(customerLocation, shopLocation);
            if(distance < minimum){
                minimum = distance;
                closest = shops.get(key);
            }
        }
        if(closest != null){
            return new Message("Closest shop is " + closest + " at distance " + minimum + " Miles.");
        }else{
            return new Message("No shops in the repository.");
        }

    }

}
