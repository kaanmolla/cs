package com.tradeservice.validation;


import com.tradeservice.model.Error;
import com.tradeservice.model.Product;

import java.util.List;

public interface Validator {
    List<String> validate(Product product);
}
