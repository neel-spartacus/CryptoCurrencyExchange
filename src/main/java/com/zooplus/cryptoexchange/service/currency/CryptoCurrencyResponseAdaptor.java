package com.zooplus.cryptoexchange.service.currency;


import com.zooplus.cryptoexchange.entity.ExchangeRate;
import com.zooplus.cryptoexchange.service.CryptoRateAdaptor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class CryptoCurrencyResponseAdaptor implements CryptoRateAdaptor<CryptoCurrencyExchangeResponse> {


    @Override
    public ExchangeRate adapt(final CryptoCurrencyExchangeResponse response) {
        if (response.getRates() != null && response.getRates().size() == 1) {
            Map.Entry<String, Float> exchangeRate = response.getRates().entrySet().iterator().next();
            String target = response.getTarget();
            Float value = exchangeRate.getValue();

            ExchangeRate rate = new ExchangeRate();
            rate.setSource(exchangeRate.getKey());
            rate.setRateTimestamp(response.getTimestamp());
            rate.setTarget(target);
            rate.setRate(value);
            rate.setRateDate(convertTimestampToDateTime(response.getTimestamp()));

            return rate;
        } else {
            throw new RuntimeException("Exchange response does not have rates or have more than one value");
        }
    }

    private String convertTimestampToDateTime(Long timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime dateTime =
                LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.of("UTC"));

        return dateTime.format(formatter);
    }

    @Override
    public CryptoCurrencyExchangeResponse adapt(ExchangeRate exchangeRate) {
        throw new UnsupportedOperationException("Not implemented."); //To change body of generated methods, choose Tools | Templates.
    }
}
