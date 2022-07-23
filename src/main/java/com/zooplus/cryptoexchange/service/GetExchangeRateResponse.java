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
    public ExchangeRate       current;
    public Issue              issue = Issue.NO_ISSUE;
    
    public static enum Issue {
        NO_ISSUE(""),
        NO_CONNECTION_TO_SERVER("Communication error with exchange rate server");
        
        private final String message;
        
        Issue(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
