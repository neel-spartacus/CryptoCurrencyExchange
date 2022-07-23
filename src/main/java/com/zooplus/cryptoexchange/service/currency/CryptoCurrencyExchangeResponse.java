package com.zooplus.cryptoexchange.service.currency;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class CryptoCurrencyExchangeResponse {
    private Long               timestamp;
    private String             target;
    private Map<String, Float> rates;
    private boolean             success;
}
