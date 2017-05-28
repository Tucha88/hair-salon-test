package hairsaon.models.classes_for_master;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;

import java.io.IOException;

/**
 * Created by Лимаренко on 28.05.2017.
 */
public class MapsAPI {
    public MapsAPI() {
    }

    public void testMapsGoogle(String address) throws InterruptedException, ApiException, IOException {

        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCV43DMS9LJA9XaK10nY0I_sAGSxeDetlc");
        GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
        Geometry geometry = results[0].geometry;
        String placeId = results[0].placeId;
        double latitude = geometry.location.lat;
        double longitude = geometry.location.lng;
    }
}
