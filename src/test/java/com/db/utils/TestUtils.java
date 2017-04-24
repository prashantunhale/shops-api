package com.db.utils;

import com.google.maps.model.LatLng;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.BDDAssertions.then;

/**
 * Tests around google api and distance calculator
 * Created by Prashant on 23-04-2017.
 */
@RunWith(SpringRunner.class)
public class TestUtils {

    @Test
    public void testGoogleMapsAPI(){
        GoogleMapsAPI api = new GoogleMapsAPI();
        try{
            LatLng coordinates = api.getDetails("2 N1C4AG");
            then(coordinates.lat == 51.53340340);
            then(coordinates.lng == -0.12503080);
        }catch(Exception e){
            //do nothing
        }
    }

    @Test
    public void testDistanceCalculator(){
        GoogleMapsAPI api = new GoogleMapsAPI();
        try{
            LatLng nikeCentral = api.getDetails("2 N1C4AG"); //Nike Central 51.5334034, -0.1250308
            LatLng nikeKingsRoad = api.getDetails("33 SW34LX"); //Nike Kings Road 51.4906034, -0.1611605

            double d1 = HaversineDistanceCalculator.calculate(nikeCentral, nikeKingsRoad);
            double d2 = HaversineDistanceCalculator.calculate(nikeKingsRoad, nikeCentral);

            then(d1 == d2);
            then(d1 == 5.377457788899761);
        }catch(Exception e){
            //do nothing
        }
    }

}
