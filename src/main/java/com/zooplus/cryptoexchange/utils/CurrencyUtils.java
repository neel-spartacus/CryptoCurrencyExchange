package com.zooplus.cryptoexchange.utils;

import com.zooplus.cryptoexchange.service.currency.CryptoCurrency;

import java.util.Currency;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

public class CurrencyUtils {

    public static String getCurrencyCode(String countryCode) {
        return Currency.getInstance(new Locale("", countryCode)).getCurrencyCode();
    }

    public static Set<CryptoCurrency> currencies() {
        final Set<CryptoCurrency> currencies = new LinkedHashSet() {{
            add(CryptoCurrency.BTC);
            add(CryptoCurrency.ETH);
            add(CryptoCurrency.ADA);
            add(CryptoCurrency.BNB);
            add(CryptoCurrency.DOGE);
            add(CryptoCurrency.ETC);
            add(CryptoCurrency.TRX);
            add(CryptoCurrency.XRP);
        }};
        return currencies;
    }
}
