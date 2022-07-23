package com.zooplus.cryptoexchange.service;

import com.zooplus.cryptoexchange.model.Country;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class GeoLookUpServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GeoLookUpService geoLookUpService;

    @Test
    public void shouldReturnCountryCurrencyCodeFromIpAddress(){

        //Given
        String ipAddress="49.162.96.128";
        Country country=Country.builder().country_name("INDIA").country_code("IND").currency("INR").build();

        //When
        Mockito.when(restTemplate.getForObject(anyString(),eq(Country.class))).thenReturn(country);

        Optional<String> currency=geoLookUpService.getCurrencyOfACountryFromIpAddress(ipAddress);

        //Then
        Assert.assertTrue(currency.get().equals("INR"));
    }
}
