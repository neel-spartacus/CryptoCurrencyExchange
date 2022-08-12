package com.zooplus.cryptoexchange.service;


import com.zooplus.cryptoexchange.entity.ExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetExchangeRateResponse {
    public List<ExchangeRate> latestSearches;
    public ExchangeRate current;
    public Issue issue = Issue.NO_ISSUE;

    public static enum Issue {
        NO_ISSUE(""),
        NO_CONNECTION_TO_SERVER("Communication error with exchange rate server"),
        NO_CURRENCY_FOUND_FOR_PROVIDED_IP("No currency found for the ip address provided");

        private final String message;

        Issue(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
