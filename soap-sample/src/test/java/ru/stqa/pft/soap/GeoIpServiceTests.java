package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;

public class GeoIPServiceTests {


    @Test
    public void testMyIp() {
        String ipLocation = new GeoIpService().getGeoIPServiceSoap12().getIpLocation("37.214.50.208");
        assertTrue(ipLocation.contains("BY"));
    }

    @Test
    public void testInvalidIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("37.214.50.xxx");
        assertTrue(ipLocation.contains("US"));
    }
}

    