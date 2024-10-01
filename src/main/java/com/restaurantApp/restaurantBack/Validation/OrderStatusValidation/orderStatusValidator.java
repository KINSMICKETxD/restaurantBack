package com.restaurantApp.restaurantBack.Validation.OrderStatusValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class orderStatusValidator implements ConstraintValidator<OrderStatusValidation,String> {

   private List<String> values = new ArrayList<>();


    @Override
    public void initialize(OrderStatusValidation orderStatusValidation) {

        values = new ArrayList<String>();
        Enum<?>[] enumConstants = orderStatusValidation.enumClass().getEnumConstants();
        for(Enum<?> enumConstant : enumConstants){
            values.add(enumConstant.toString());
        }

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null){
            return true;
        }else{
            return values.contains(s);
        }
    }
}
