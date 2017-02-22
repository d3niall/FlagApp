package com.doulaize.flagapp.exception;

/**
 * Created by rdeleuze on 2/16/17.
 */

public class CoordinateException extends RuntimeException {

    public CoordinateException(Integer value, Integer minValue, Integer maxValue) {

        super("Illegal coordinates : " + value.toString() + " should be between values : " + minValue.toString() + " and " + maxValue.toString());
    }
}
