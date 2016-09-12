package com.tradeservice.validation;

import com.tradeservice.model.Product;
import com.tradeservice.model.SpotProduct;
import com.tradeservice.utils.CounterPartyUtils;
import com.tradeservice.utils.CurrencyUtils;
import com.tradeservice.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class SpotProductValidator implements Validator{

    private static final SpotProductValidator instance = new SpotProductValidator();

    public static SpotProductValidator getInstance() {
        return instance;
    }

    public List<String> validate(Product product) {
        List<String> errors = new ArrayList<>();
        SpotProduct spotProduct = (SpotProduct) product;

        if (DateUtils.getInstance().isBefore(spotProduct.getValueDate(), spotProduct.getTradeDate())) {
            errors.add("value date can not be before trade date");
        }

        if (!CurrencyUtils.getInstance().checkCurrencyByCode(spotProduct.getCcyPair().substring(0,3))
                || !CurrencyUtils.getInstance().checkCurrencyByCode(spotProduct.getCcyPair().substring(3))) {
            errors.add("currencies are not valid for ccyPair");
        }

        if (!CounterPartyUtils.getInstance().checkCounterParty(spotProduct.getCustomer())) {
            errors.add("Customer is not supported");
        }

        if (DateUtils.getInstance().isWeekend(spotProduct.getValueDate())) {
            errors.add("value date can not be weekend");
        }

        return errors;
    }
}
