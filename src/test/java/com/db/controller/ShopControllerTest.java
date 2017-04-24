package com.db.controller;

import com.db.Application;
import com.db.domain.Shop;
import com.db.domain.ShopAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.Map;

/**
 * Controller configuration and behaviour tests for complete flow
 * Created by Prashant on 23-04-2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
public class ShopControllerTest {
    @LocalServerPort
    private int port;

    @Value("${local.management.port}")
    private int mgt;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200WhenSendingRequestToController() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/", Map.class);
        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.mgt + "/info", Map.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    // Complete flow : Add 2 valid shops then query for them and then query for nearest
    public void shouldWorkAsExpected() throws Exception {

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = this.testRestTemplate.postForEntity(
                "http://localhost:" + this.port + "/Shops", getNikeKingsRoad(), Map.class
        );

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(entity.getBody().get("message")).isEqualTo("Successfully added the shop.");

        entity = this.testRestTemplate.postForEntity(
                "http://localhost:" + this.port + "/Shops", getNikeCentral(), Map.class
        );

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(entity.getBody().get("message")).isEqualTo("Successfully added the shop.");

        @SuppressWarnings("rawtypes")
        ResponseEntity<Iterable> entity1 = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/Shops", Iterable.class);
        then(entity1.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(entity.getBody().size() == 2);

        entity = this.testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/Shop?latitude=51.5333126&longitude=-0.1337403", Map.class);
        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(entity.getBody().get("message").equals("Closest shop is Shop{shopName='Nike Central',ShopAddress{number='2', postCode='N1C4AG', coordinates=51.53340340,-0.12503080}} at distance 0.6026891398952323 KMs."));

    }

    private Shop getNikeKingsRoad(){
        Shop shop = new Shop();
        shop.setShopName("Nike Kings Road");
        ShopAddress shopAddress = new ShopAddress();
        shopAddress.setNumber("33");
        shopAddress.setPostCode("SW34LX");
        shop.setShopAddress(shopAddress);
        return shop;
    }

    private Shop getNikeCentral(){
        Shop shop = new Shop();
        shop.setShopName("Nike Central");
        ShopAddress shopAddress = new ShopAddress();
        shopAddress.setNumber("2");
        shopAddress.setPostCode("N1C4AG");
        shop.setShopAddress(shopAddress);
        return shop;
    }

}
