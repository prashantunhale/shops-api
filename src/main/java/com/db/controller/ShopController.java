package com.db.controller;

import com.db.domain.Message;
import com.db.domain.Shop;
import com.db.service.InMemoryShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Shop API
 * Created by Prashant on 23-04-2017.
 */
@RestController
@RequestMapping("")
public class ShopController {
    @Autowired
    private InMemoryShopRepository repository;

    @RequestMapping("/")
    public Message home(){
        return new Message("Welcome to Shop API");
    }

    @RequestMapping("/Shops")
    public Iterable<Shop> getAllShops(){
        return repository.getAllShops();
    }

    @RequestMapping("/Shop")
    public Message getClosestShop(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude){
        return repository.getClosestShop(latitude, longitude);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Shops")
    public Message addShop(@RequestBody Shop shop){
        return repository.addShop(shop);
    }

}
