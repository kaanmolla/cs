package com.tradeservice.model;

import com.tradeservice.validation.ForwardProductValidator;

import java.util.List;

public class ForwardProduct extends Product {

    private String valueDate;

    @Override
    public List<String> validate() {
        return ForwardProductValidator.getInstance().validate(this);
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }
}
