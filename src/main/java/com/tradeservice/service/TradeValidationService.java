package com.tradeservice.service;

import com.tradeservice.model.Error;
import com.tradeservice.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeValidationService {

    public List<Error> validateProducts(List<Product> products) {
        List<Error> errors = new ArrayList<>();

        for (Product product : products) {
            List<String> results = product.validate();
            if (!CollectionUtils.isEmpty(results)) {
                errors.add(new Error(product, results));
            }
        }

        return errors;
    }
}
