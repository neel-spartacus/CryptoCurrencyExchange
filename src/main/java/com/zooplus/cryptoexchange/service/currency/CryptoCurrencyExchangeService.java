package com.zooplus.cryptoexchange.service.currency;

import com.zooplus.cryptoexchange.entity.ExchangeRate;
import com.zooplus.cryptoexchange.service.CryptoRateAdaptor;
import com.zooplus.cryptoexchange.service.CryptoRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CryptoCurrencyExchangeService implements CryptoRateService {
    private static final String access_key = "b0d305f4bc887efea2b113297deb846c";

    private static final String API= "api.coinlayer.com/api/";


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CryptoRateAdaptor adaptor;
    
    @Override
    public ExchangeRate get(final String source, final String target) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host(API)
                .path("live").queryParam("access_key",access_key).queryParam("target",target)
                .queryParam("symbols",source).build();
        CryptoCurrencyExchangeResponse response = restTemplate.getForObject(uriComponents.toUriString(), CryptoCurrencyExchangeResponse.class);
        return adaptor.adapt(response);
    }

}
