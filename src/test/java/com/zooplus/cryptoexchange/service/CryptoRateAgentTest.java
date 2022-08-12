package com.zooplus.cryptoexchange.service;


import com.zooplus.cryptoexchange.entity.ExchangeRate;
import com.zooplus.cryptoexchange.repository.CryptoExchangeRateRepository;
import com.zooplus.cryptoexchange.service.geoip.GeoLookUpService;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CryptoRateAgentTest {

    @Mock
    private CryptoRateService    cryptoRateService;
    @Mock
    private CryptoExchangeRateRepository cryptoExchangeRatesRepo;
    @Mock
    private GeoLookUpService geoLookUpService;
    @InjectMocks
    private CryptoRateAgent cryptoRateAgent;

    @Test
    public void shouldRetrieveCryptoExchangeRateForValidIpAddress(){

        //Given
        String target="BTC";
        String ip="49.207.207.149";
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setRate(10000000F);
        exchangeRate.setRateDate("22-JUL-2022");
        exchangeRate.setSource("INR");
        exchangeRate.setTarget(target);
        List<ExchangeRate> exchangeRates = Arrays.asList(exchangeRate);
        Page page = new PageImpl<ExchangeRate>(exchangeRates);

        //When
        when(cryptoExchangeRatesRepo.findAll(any(Pageable.class))).thenReturn(page);
        Mockito.when(geoLookUpService.getCurrencyOfACountryFromIpAddress(anyString())).thenReturn(Optional.of("INR"));
        Mockito.when(cryptoRateService.get(anyString(),anyString())).thenReturn(exchangeRate);
        GetExchangeRateResponse response=cryptoRateAgent.get(target,ip);


        //Then
        Assert.assertNotNull(response);
        ExchangeRate exchangeRateResponse = response.getCurrent();
        Assert.assertTrue(exchangeRateResponse.getRate().equals(10000000.00F));
        Assert.assertTrue(exchangeRateResponse.getSource().equals("INR"));
        Assert.assertTrue(exchangeRateResponse.getTarget().equals("BTC"));

    }


    @Test
    public void shouldRetrieveNoCurrencyForInValidIpAddress(){

        String target="BTC";
        String ip="12345";
        List<ExchangeRate> exchangeRates = Lists.newArrayList();
        Page page = new PageImpl<ExchangeRate>(exchangeRates);

        //When
        when(cryptoExchangeRatesRepo.findAll(any(Pageable.class))).thenReturn(page);
        Mockito.when(geoLookUpService.getCurrencyOfACountryFromIpAddress(anyString())).thenReturn(Optional.empty());
        GetExchangeRateResponse response=cryptoRateAgent.get(target,ip);

        //Then
        Assert.assertNotNull(response);
        Assert.assertNull(response.getCurrent());
        Assert.assertTrue(response.getIssue().getMessage().equals("No currency found for the ip address provided"));
    }
}
