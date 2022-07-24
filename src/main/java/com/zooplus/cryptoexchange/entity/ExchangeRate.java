package com.zooplus.cryptoexchange.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@IdClass(ExchangeRate.ExchangeRateRequest.class)
@Table(name = "ExchangeRate")
public class ExchangeRate implements Serializable {
    @Id
    @Column(name = "SOURCE", nullable = false)
    @NotNull
    @Size(max = 3)
    private String source;
    @Id
    @Column(name = "TARGET", nullable = false)
    @NotNull
    @Size(max = 3)
    private String target;
    @Id
    @Column(name = "RATE_TIMESTAMP", nullable = false)
    @NotNull
    private Long rateTimestamp;
    @Column(name = "RATE", nullable = false)
    private Float rate;
    @Column(name = "RATE_DATE", nullable = true)
    @Size(max = 10)
    private String rateDate;
    @Column(name = "REQUEST_TIMESTAMP", nullable = true)
    private Long requestTimestamp;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExchangeRateRequest implements Serializable {
        private String source;
        private String target;
        private Long rateTimestamp;
    }
}
