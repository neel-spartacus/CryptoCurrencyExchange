package com.zooplus.cryptoexchange.service;

import com.zooplus.cryptoexchange.entity.ExchangeRate;
import com.zooplus.cryptoexchange.repository.CryptoExchangeRateRepository;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CryptoDataServiceTest {


    @Mock
    private CryptoRateService cryptoRateService;

    @Mock
    private CryptoExchangeRateRepository cryptoExchangeRateRepository;

    @InjectMocks
    private CryptoDataService cryptoDataService;


    @Test
    public void shouldRetrieveCryptoCurrencyExchangeRate() {

        //Given
        String source = "INR";
        String target = "BTC";
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setRate(10000000F);
        exchangeRate.setRateDate("22-JUL-2022");
        exchangeRate.setSource(source);
        exchangeRate.setTarget(target);

        List<ExchangeRate> exchangeRates = Arrays.asList(exchangeRate);
        Page page = new PageImpl<ExchangeRate>(exchangeRates);

        //When
        when(cryptoExchangeRateRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(cryptoRateService.get(anyString(), anyString())).thenReturn(exchangeRate);

        GetExchangeRateResponse response = cryptoDataService.get(source, target);

        //Then
        Assert.assertNotNull(response);
        ExchangeRate exchangeRateResponse = response.getCurrent();
        Assert.assertTrue(exchangeRateResponse.getRate().equals(10000000.00));
        Assert.assertTrue(exchangeRateResponse.getSource().equals("INR"));
        Assert.assertTrue(exchangeRateResponse.getTarget().equals("BTC"));


    }

    @Test
    public void shouldReturnNegativeExchangeRateWhenCallToExchangeApiFails() {

        //Given
        Page page = new PageImpl<ExchangeRate>(Lists.emptyList());
        //When

        when(cryptoExchangeRateRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(cryptoRateService.get(anyString(), anyString()))
                .thenThrow(new RuntimeException("Could get response from the Exchange server"));

        GetExchangeRateResponse response = cryptoDataService.get("ABC123", "BTC");

        //Then
        Assert.assertTrue(response.getCurrent().getRate() == -1.00);
    }

    @Test
    public void shouldReturnTheLatestRanQueries() {

        //Given
        String source = "INR";
        String target = "BTC";
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setRate(10000000.00F);
        exchangeRate.setRateDate("22-JUL-2022");
        exchangeRate.setSource(source);
        exchangeRate.setTarget(target);

        List<ExchangeRate> exchangeRates = Arrays.asList(exchangeRate);
        Page page = new PageImpl<ExchangeRate>(exchangeRates);

        //When
        when(cryptoExchangeRateRepository.findAll(any(Pageable.class))).thenReturn(page);

        List<ExchangeRate> latestRanQueries = cryptoDataService.lastQueries();

        //Then
        Assert.assertTrue(latestRanQueries.size() == 1);
        Assert.assertTrue(latestRanQueries.get(0).getRate() == 10000000.00F);

    }

}
