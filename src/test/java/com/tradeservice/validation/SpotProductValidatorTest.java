package com.tradeservice.validation;

import com.tradeservice.model.SpotProduct;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SpotProductValidatorTest {

    @Test
    public void shouldReturnEmptyErrorsIfProductIsValid() {
        SpotProduct spotProduct = buildSpotProduct("2016-10-10", "2016-10-01");
        assertTrue(spotProduct.validate().isEmpty());
    }

    @Test
    public void shouldReturnErrorsIfProductIsInvalid() {
        SpotProduct spotProduct = buildSpotProduct("2016-10-09", "2016-10-12");
        spotProduct.setCcyPair("INVALIDUSD");
        spotProduct.setCustomer("INVALIDCUSTOMER");
        List<String> results = spotProduct.validate();
        assertEquals(4, results.size());
        assertTrue(results.contains("value date can not be before trade date"));
        assertTrue(results.contains("currencies are not valid for ccyPair"));
        assertTrue(results.contains("Customer is not supported"));
        assertTrue(results.contains("value date can not be weekend"));
    }

    private SpotProduct buildSpotProduct(String valueDate, String tradeDate) {
        SpotProduct spotProduct = new SpotProduct();
        spotProduct.setValueDate(valueDate);
        spotProduct.setTradeDate(tradeDate);

        spotProduct.setCcyPair("USDEUR");
        spotProduct.setCustomer("PLUTO1");
        return spotProduct;
    }

}