package com.zooplus.cryptoexchange.repository;

import com.zooplus.cryptoexchange.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoExchangeRateRepository extends JpaRepository<ExchangeRate, ExchangeRate.ExchangeRateRequest> {
}
