package com.zooplus.cryptoexchange.service;


import com.zooplus.cryptoexchange.entity.ExchangeRate;
import com.zooplus.cryptoexchange.repository.CryptoExchangeRateRepository;
import com.zooplus.cryptoexchange.service.geoip.GeoLookUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.zooplus.cryptoexchange.service.GetExchangeRateResponse.Issue.NO_CONNECTION_TO_SERVER;
import static com.zooplus.cryptoexchange.service.GetExchangeRateResponse.Issue.NO_ISSUE;

@Component
public class CryptoRateAgent {
    @Autowired private CryptoRateService    exchangeService;
    @Autowired private CryptoExchangeRateRepository cryptoExchangeRatesRepo;
    @Autowired private GeoLookUpService geoLookUpService;
    
    private static final int LAST_SIZE = 10;
    
    public GetExchangeRateResponse get(final String target,String ip) {
        GetExchangeRateResponse response = new GetExchangeRateResponse();
        response.setLatestSearches(lastQueries());
        Optional<String> currencyCode=geoLookUpService.getCurrencyOfACountryFromIpAddress(ip);

        if(currencyCode.isPresent()){{
            currentExchangeRate(response, target, currencyCode.get());
        }}else{
            throw new RuntimeException("No currency found for the country for the ip address provided");
        }

        if (response.issue==NO_ISSUE) {
            response.getCurrent().setRequestTimestamp(System.currentTimeMillis());
            cryptoExchangeRatesRepo.save(response.getCurrent());
        }
        return response;
    }

    public List<ExchangeRate> lastQueries() {
        final Pageable lastExchanges =PageRequest.of(0, LAST_SIZE, Sort.by(Sort.Direction.DESC,"rate"));
        return cryptoExchangeRatesRepo.findAll(lastExchanges).getContent();
    }

    
    private void currentExchangeRate(final GetExchangeRateResponse response, final String source, 
                                     final String target) {
        ExchangeRate exchangeRate;
        try {
            exchangeRate = exchangeService.get(source,target);
        } catch(Throwable t) {
            Long now = System.currentTimeMillis();
            exchangeRate = new ExchangeRate();
            exchangeRate.setSource(source);
            exchangeRate.setTarget(target);
            exchangeRate.setRateTimestamp(now);
            exchangeRate.setRate(-1F);
            
            response.setIssue(NO_CONNECTION_TO_SERVER);
        }
        response.setCurrent(exchangeRate);
    }
}
