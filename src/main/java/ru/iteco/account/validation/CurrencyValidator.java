package ru.iteco.account.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private static final Set<String> CURRENCY = Set.of("RUB", "EUR", "USD", "GBP");

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext constraintValidatorContext) {
        return CURRENCY.contains(currency);
    }

}
