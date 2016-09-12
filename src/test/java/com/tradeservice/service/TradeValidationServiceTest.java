package com.tradeservice.service;

import com.tradeservice.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TradeValidationServiceTest {

    @InjectMocks
    private TradeValidationService tradeValidationService;

    @Test
    public void shouldReturnEmptyListIfAllProductsAreValid() {
        List<Product> products = new ArrayList<>();
        products.add(buildOptionProduct("2016-10-10", "2016-10-01", "2016-10-11", "2016-10-12", "2016-10-10"));
        products.add(buildSpotProduct("2016-10-10", "2016-10-01"));
        products.add(buildForwardProduct("2016-10-10", "2016-10-01"));

        List<com.tradeservice.model.Error> errors = tradeValidationService.validateProducts(products);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnErrorListIfAnyProductsAreInvalid() {
        List<Product> products = new ArrayList<>();
        products.add(buildOptionProduct("2016-10-10", "2016-10-19", "2016-10-11", "2016-10-12", "2016-10-10"));
        products.add(buildSpotProduct("2016-10-10", "2016-10-21"));
        products.add(buildForwardProduct("2016-10-10", "2016-10-21"));

        List<com.tradeservice.model.Error> errors = tradeValidationService.validateProducts(products);

        assertEquals(3, errors.size());
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

    private SpotProduct buildSpotProduct(String valueDate, String tradeDate) {
        SpotProduct spotProduct = new SpotProduct();
        spotProduct.setTradeDate(tradeDate);
        spotProduct.setValueDate(valueDate);

        spotProduct.setCcyPair("USDEUR");
        spotProduct.setCustomer("PLUTO1");

        return spotProduct;
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

