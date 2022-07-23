package com.zooplus.cryptoexchange.service.currency;

public enum CryptoCurrency {

    ADA("ADA","Cardano"),
    BNB("BNB","Binance Coin"),
    BTC("BTC","Bitcoin"),
    DOGE("DOGE","Dogecoin"),
    ETC("ETC","Ethereum Classic"),
    ETH("ETH","Ethereum"),
    TRX("TRX","Tronix"),
    XRP("XRP","Ripple");

    private final String currencyCode;
    private final String displayName;

    CryptoCurrency(String currencyCode, String displayName) {
        this.currencyCode = currencyCode;
        this.displayName = displayName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getDisplayName() {
        return displayName;
    }
}
