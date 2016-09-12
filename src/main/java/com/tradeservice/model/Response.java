package com.tradeservice.model;

import java.util.List;


public class Response {

    private ValidationResult validationResult;
    private List<Error> errors;

    public Response(ValidationResult validationResult, List<Error> errors) {
        this.validationResult = validationResult;
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(ValidationResult validationResult) {
        this.validationResult = validationResult;
    }
}
