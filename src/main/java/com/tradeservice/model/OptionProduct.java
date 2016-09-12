package com.tradeservice.model;

import com.tradeservice.validation.OptionProductValidator;

import java.util.List;

public class OptionProduct extends Product {

    private String style;
    private String strategy;
    private String deliveryDate;
    private String expiryDate;
    private String payCcy;
    private double premium;
    private String premiumCcy;
    private String premiumType;
    private String premiumDate;
    private String excerciseStartDate;

    @Override
    public List<String> validate() {
        return OptionProductValidator.getInstance().validate(this);
    }

    public String  getStyle() {
        return style;
    }

    public void setStyle(String  style) {
        this.style = style;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPayCcy() {
        return payCcy;
    }

    public void setPayCcy(String payCcy) {
        this.payCcy = payCcy;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public String getPremiumCcy() {
        return premiumCcy;
    }

    public void setPremiumCcy(String premiumCcy) {
        this.premiumCcy = premiumCcy;
    }

    public String getPremiumType() {
        return premiumType;
    }

    public void setPremiumType(String premiumType) {
        this.premiumType = premiumType;
    }

    public String getPremiumDate() {
        return premiumDate;
    }

    public void setPremiumDate(String premiumDate) {
        this.premiumDate = premiumDate;
    }

    public String getExcerciseStartDate() {
        return excerciseStartDate;
    }

    public void setExcerciseStartDate(String excerciseStartDate) {
        this.excerciseStartDate = excerciseStartDate;
    }

}
