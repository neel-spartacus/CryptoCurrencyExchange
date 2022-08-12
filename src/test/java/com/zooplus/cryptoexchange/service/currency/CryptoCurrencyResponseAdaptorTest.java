package com.zooplus.cryptoexchange.service.currency;

import com.zooplus.cryptoexchange.entity.ExchangeRate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class CryptoCurrencyResponseAdaptorTest {

    @InjectMocks
    private CryptoCurrencyResponseAdaptor cryptoCurrencyResponseAdaptor;


    @Test
    public void shouldConvertCryptoCurrencyExchangeResponseToExchangeRate() {

        //Given
        Map<String, Float> currencyRateMap = new HashMap<>();
        currencyRateMap.put("INR", 10000000F);
        CryptoCurrencyExchangeResponse currencyExchangeResponse = CryptoCurrencyExchangeResponse.builder().timestamp(Long.parseLong("1658601490"))
                .rates(currencyRateMap).target("BTC").build();

        //When
        ExchangeRate exchangerate = cryptoCurrencyResponseAdaptor.adapt(currencyExchangeResponse);

        //Then
        Assert.assertNotNull(exchangerate);
        Assert.assertTrue(exchangerate.getSource().equals("INR"));
        Assert.assertTrue(exchangerate.getTarget().equals("BTC"));
        Assert.assertTrue(exchangerate.getRate() == 10000000.00F);
        Assert.assertTrue(exchangerate.getRateDate().equals("2022-07-23T18:38:10"));
    }
}
