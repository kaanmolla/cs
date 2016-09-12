package com.tradeservice.model;

import java.util.List;

public class Error {

    private Product product;

    private List<String> errors;

    public Error(Product product, List<String> errors) {
        this.product = product;
        this.errors = errors;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
