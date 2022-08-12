package com.zooplus.cryptoexchange.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Currency {
    private String displayName;
    private String currencyCode;
}
