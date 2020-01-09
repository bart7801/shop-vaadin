package com.bart.shopvaadin.extension;

import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.binder.ValueContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MinLengthValidator implements Validator<String> {

    @NonNull
    private int minLength;

    @Override
    public ValidationResult apply(String value, ValueContext valueContext) {
        if (value.length() < minLength) {
            return ValidationResult.error("Value is to short");
        } else  {
            return ValidationResult.ok();
        }
    }

}
