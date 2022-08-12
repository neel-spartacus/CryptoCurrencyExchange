package com.zooplus.cryptoexchange.service;

import com.zooplus.cryptoexchange.entity.ExchangeRate;

public interface CryptoRateAdaptor<T> {
    ExchangeRate adapt(T object);

    T adapt(ExchangeRate exchangeRate);
}
