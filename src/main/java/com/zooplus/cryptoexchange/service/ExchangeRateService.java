package com.zooplus.cryptoexchange.service;

import com.zooplus.cryptoexchange.entity.ExchangeRate;

public interface ExchangeRateService {
    ExchangeRate get(final String source, final String target);
    ExchangeRate get(final String source, final String target, final String rateDate);
}
