package com.zooplus.cryptoexchange.service;


import com.zooplus.cryptoexchange.entity.ExchangeRate;
import com.zooplus.cryptoexchange.repository.CryptoExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.zooplus.cryptoexchange.service.GetExchangeRateResponse.Issue.NO_ISSUE;


@Component
public class CryptoDataService {
    @Autowired
    private CryptoRateService cryptoRateService;

    @Autowired
    private CryptoExchangeRateRepository cryptoExchangeRateRepository;

    private static final int LAST_SIZE = 10;

    public GetExchangeRateResponse get(final String source, final String target) {
        GetExchangeRateResponse response = GetExchangeRateResponse.builder().build();

        response.setLatestSearches(lastQueries());
        currentExchangeRate(response, source, target);
        if (response.issue == NO_ISSUE) {
            response.getCurrent().setRequestTimestamp(System.currentTimeMillis());
            cryptoExchangeRateRepository.save(response.getCurrent());
        }
        return response;
    }

    public List<ExchangeRate> lastQueries() {
        final Pageable lastExchanges = PageRequest.of(0, LAST_SIZE, Sort.by(Sort.Direction.DESC, "rate"));
        return cryptoExchangeRateRepository.findAll(lastExchanges).getContent();
    }


    private void currentExchangeRate(final GetExchangeRateResponse response, final String source,
                                     final String target) {
        ExchangeRate exchangeRate;
        try {
            exchangeRate = cryptoRateService.get(source, target);
        } catch (Throwable t) {

            Long now = System.currentTimeMillis();
            exchangeRate = new ExchangeRate();
            exchangeRate.setSource(source);
            exchangeRate.setTarget(target);
            exchangeRate.setRateTimestamp(now);
            exchangeRate.setRate(-1F);

        }
        response.setCurrent(exchangeRate);
    }
}
