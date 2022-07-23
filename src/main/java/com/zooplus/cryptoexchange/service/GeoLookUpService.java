package com.zooplus.cryptoexchange.service;


import com.zooplus.cryptoexchange.model.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static com.zooplus.cryptoexchange.utils.Constants.API_DOMAIN;
import static com.zooplus.cryptoexchange.utils.Constants.FORMAT;

@Service
@Slf4j
public class GeoLookUpService {

    @Autowired
    private RestTemplate restTemplate;

    public GeoLookUpService() {
    }

    //https://api.ipgeolocationapi.com/geolocate/
    public Optional<String> getCurrencyOfACountryFromIpAddress(String ipAddress){

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host(API_DOMAIN)
                .path(ipAddress).path("/").path(FORMAT).build();

        Country country=restTemplate.getForObject(uriComponents.toUriString(), Country.class);

        return Optional.ofNullable(country.getCurrency());

    }
}
