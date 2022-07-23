package com.zooplus.cryptoexchange.service;


import com.zooplus.cryptoexchange.entity.ExchangeRate;

public interface CryptoRateService {
    ExchangeRate get(final String source, final String target);
}
