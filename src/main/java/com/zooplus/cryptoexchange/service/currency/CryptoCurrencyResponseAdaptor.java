package com.zooplus.cryptoexchange.service.currency;


import com.zooplus.cryptoexchange.entity.ExchangeRate;
import com.zooplus.cryptoexchange.service.CryptoRateAdaptor;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CryptoCurrencyResponseAdaptor implements CryptoRateAdaptor<CryptoCurrencyExchangeResponse> {
    @Override
    public ExchangeRate adapt(final CryptoCurrencyExchangeResponse response) {
        if (response.getRates()!=null && response.getRates().size()==1) {
            Map.Entry<String, Float> exchangeRate = response.getRates().entrySet().iterator().next();
            String target = response.getTarget();
            Float  value  = exchangeRate.getValue();

            ExchangeRate rate = new ExchangeRate();
            rate.setSource(exchangeRate.getKey());
            rate.setRateTimestamp(response.getTimestamp());
            rate.setTarget(target);
            rate.setRate(value);

            return rate;
        } else {
            throw new RuntimeException("Exchange response does not have rates or have more than one value");
        }
    }

    @Override
    public CryptoCurrencyExchangeResponse adapt(ExchangeRate exchangeRate) {
        throw new UnsupportedOperationException("Not implemented."); //To change body of generated methods, choose Tools | Templates.
    }
}
