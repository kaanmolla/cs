package com.tradeservice.validation;

import com.tradeservice.model.ForwardProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class ForwardProductValidatorTest {

    @Test
    public void shouldReturnEmptyErrorsIfProductIsValid() {
        ForwardProduct forwardProduct = buildForwardProduct("2016-10-10", "2016-10-01");
        assertTrue(forwardProduct.validate().isEmpty());
    }

    @Test
    public void shouldReturnErrorsIfProductIsInvalid() {
        ForwardProduct forwardProduct = buildForwardProduct("2016-10-09", "2016-10-12");
        forwardProduct.setCcyPair("INVALIDUSD");
        forwardProduct.setCustomer("INVALIDCUSTOMER");
        List<String> results = forwardProduct.validate();
        assertEquals(4, results.size());
        assertTrue(results.contains("value date can not be before trade date"));
        assertTrue(results.contains("currencies are not valid for ccyPair"));
        assertTrue(results.contains("Customer is not supported"));
        assertTrue(results.contains("value date can not be weekend"));
    }

    private ForwardProduct buildForwardProduct(String valueDate, String tradeDate) {
        ForwardProduct forwardProduct = new ForwardProduct();
        forwardProduct.setValueDate(valueDate);
        forwardProduct.setTradeDate(tradeDate);

        forwardProduct.setCcyPair("USDEUR");
        forwardProduct.setCustomer("PLUTO1");
        return forwardProduct;
    }


}