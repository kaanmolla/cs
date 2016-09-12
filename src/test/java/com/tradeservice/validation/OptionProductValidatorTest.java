package com.tradeservice.validation;

import com.tradeservice.model.OptionProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class OptionProductValidatorTest {

    @Test
    public void shouldReturnEmptyErrorsIfProductIsValid() {
        OptionProduct optionProduct = buildOptionProduct("2016-10-10", "2016-10-01", "2016-10-11", "2016-10-12", "2016-10-10");
        assertTrue(optionProduct.validate().isEmpty());
    }

    @Test
    public void shouldReturnErrorsIfProductIsInvalid() {
        OptionProduct optionProduct = buildOptionProduct("2016-10-10", "2016-10-19", "2016-10-19", "2016-10-12", "2016-10-15");
        optionProduct.setCcyPair("INVALID_USD");
        optionProduct.setCustomer("INVALID_CUSTOMER");
        optionProduct.setPremiumCcy("INVALID");
        optionProduct.setPayCcy("INVALID");
        optionProduct.setStyle("STYLE_INVALID");
        List<String> results = optionProduct.validate();
        assertEquals(7, results.size());
        assertTrue(results.contains("Style should be AMERICAN or EUROPEAN"));
        assertTrue(results.contains("Expiry date should be before delivery date"));
        assertTrue(results.contains("Premium date should be before delivery date"));
        assertTrue(results.contains("Currency is not valid for payCcy"));
        assertTrue(results.contains("Currency is not valid for premiumCcy"));
        assertTrue(results.contains("currencies are not valid for ccyPair"));
        assertTrue(results.contains("Customer is not supported"));

    }

    private OptionProduct buildOptionProduct(String exDate, String tradeDate, String expiryDate, String deliveryDate, String premiumDate) {
        OptionProduct optionProduct = new OptionProduct();
        optionProduct.setExcerciseStartDate(exDate);
        optionProduct.setTradeDate(tradeDate);
        optionProduct.setExpiryDate(expiryDate);
        optionProduct.setDeliveryDate(deliveryDate);
        optionProduct.setPremiumDate(premiumDate);

        optionProduct.setStyle("AMERICAN");
        optionProduct.setPayCcy("USD");
        optionProduct.setPremiumCcy("EUR");
        optionProduct.setCcyPair("USDEUR");

        optionProduct.setCustomer("PLUTO1");

        return optionProduct;
    }
}