package com.tradeservice.main;

import com.tradeservice.model.Error;
import com.tradeservice.model.Product;
import com.tradeservice.model.Response;
import com.tradeservice.model.ValidationResult;
import com.tradeservice.service.TradeValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/validator")
public class TradeValidationController {

    @Autowired
    private TradeValidationService tradeValidationService;

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public Response validate(@RequestBody List<Product> products) {
        List<Error> errors = tradeValidationService.validateProducts(products);

        ValidationResult validationResult = CollectionUtils.isEmpty(errors) ? ValidationResult.VALID : ValidationResult.INVALID;

        return new Response(validationResult, errors);
    }

}
