package com.tradeservice.utils;


import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyUtils {

    private static final CurrencyUtils instance = new CurrencyUtils();

    private final List<String> currencyCodes;

    private CurrencyUtils() {
        this.currencyCodes = Currency.getAvailableCurrencies().stream().map(Currency::getCurrencyCode).collect(Collectors.toList());
    }

    public static CurrencyUtils getInstance() {
        return instance;
    }

    public boolean checkCurrencyByCode(String currencyCode) {
        return currencyCodes.contains(currencyCode);
    }

}
