package com.tradeservice.validation;

import com.tradeservice.model.OptionProduct;
import com.tradeservice.model.Product;
import com.tradeservice.utils.CounterPartyUtils;
import com.tradeservice.utils.CurrencyUtils;
import com.tradeservice.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class OptionProductValidator implements Validator {

    private static final OptionProductValidator instance = new OptionProductValidator();
    private static final String STYLE_AMERICAN = "AMERICAN";
    private static final String STYLE_EUROPEAN = "EUROPEAN";

    public static OptionProductValidator getInstance() {
        return instance;
    }

    public List<String> validate(Product product) {
        List<String> errors = new ArrayList<>();

        OptionProduct optionProduct = (OptionProduct) product;

        if (!(STYLE_AMERICAN.equals(optionProduct.getStyle()) || STYLE_EUROPEAN.equals(optionProduct.getStyle()))) {
            errors.add("Style should be AMERICAN or EUROPEAN");
        }

        if (STYLE_AMERICAN.equals(optionProduct.getStyle())) {
            if (DateUtils.getInstance().isBefore(optionProduct.getExcerciseStartDate(), optionProduct.getTradeDate())) {
                errors.add("excercise start date should be after trade date");
            }

            if (!DateUtils.getInstance().isBefore(optionProduct.getExcerciseStartDate(), optionProduct.getExpiryDate())) {
                errors.add("excercise start date should be before expiry date");
            }
        }

        if (!DateUtils.getInstance().isBefore(optionProduct.getExpiryDate(), optionProduct.getDeliveryDate())){
            errors.add("Expiry date should be before delivery date");
        }

        if (!DateUtils.getInstance().isBefore(optionProduct.getPremiumDate(), optionProduct.getDeliveryDate())){
            errors.add("Premium date should be before delivery date");
        }

        if (!CurrencyUtils.getInstance().checkCurrencyByCode(optionProduct.getPayCcy())) {
            errors.add("Currency is not valid for payCcy");
        }

        if (!CurrencyUtils.getInstance().checkCurrencyByCode(optionProduct.getPremiumCcy())) {
            errors.add("Currency is not valid for premiumCcy");
        }

        if (!CurrencyUtils.getInstance().checkCurrencyByCode(optionProduct.getCcyPair().substring(0,3))
                || !CurrencyUtils.getInstance().checkCurrencyByCode(optionProduct.getCcyPair().substring(3))) {
            errors.add("currencies are not valid for ccyPair");
        }

        if (!CounterPartyUtils.getInstance().checkCounterParty(optionProduct.getCustomer())) {
            errors.add("Customer is not supported");
        }

        return errors;
    }
}
