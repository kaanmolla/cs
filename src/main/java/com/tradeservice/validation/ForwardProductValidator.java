package com.tradeservice.validation;

import com.tradeservice.model.ForwardProduct;
import com.tradeservice.model.Product;
import com.tradeservice.utils.CounterPartyUtils;
import com.tradeservice.utils.CurrencyUtils;
import com.tradeservice.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;


public class ForwardProductValidator implements Validator{

    private static final ForwardProductValidator instance = new ForwardProductValidator();

    public static ForwardProductValidator getInstance() {
        return instance;
    }

    @Override
    public List<String> validate(Product product) {
        List<String> errors = new ArrayList<>();
        ForwardProduct forwardProduct = (ForwardProduct) product;

        if (DateUtils.getInstance().isBefore(forwardProduct.getValueDate(), forwardProduct.getTradeDate())) {
            errors.add("value date can not be before trade date");
        }

        if (!CurrencyUtils.getInstance().checkCurrencyByCode(forwardProduct.getCcyPair().substring(0,3))
                || !CurrencyUtils.getInstance().checkCurrencyByCode(forwardProduct.getCcyPair().substring(3))) {
            errors.add("currencies are not valid for ccyPair");
        }

        if (!CounterPartyUtils.getInstance().checkCounterParty(forwardProduct.getCustomer())) {
            errors.add("Customer is not supported");
        }

        if (DateUtils.getInstance().isWeekend(forwardProduct.getValueDate())) {
            errors.add("value date can not be weekend");
        }

        return errors;
    }
}
