package com.tradeservice.utils;


import java.util.Arrays;
import java.util.List;

public class CounterPartyUtils {

    private static final CounterPartyUtils instance = new CounterPartyUtils();
    private final List<String> counterParties;

    private CounterPartyUtils() {
        counterParties = Arrays.asList("PLUTO1", "PLUTO2");
    }

    public static CounterPartyUtils getInstance() {
        return instance;
    }

    public boolean checkCounterParty(String customer) {
        return counterParties.contains(customer);
    }
}
