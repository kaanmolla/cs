package com.tradeservice.model;

import com.tradeservice.validation.SpotProductValidator;

import java.util.List;


public class SpotProduct extends Product {

    private String valueDate;

    @Override
    public List<String> validate() {
        return SpotProductValidator.getInstance().validate(this);
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }
}
