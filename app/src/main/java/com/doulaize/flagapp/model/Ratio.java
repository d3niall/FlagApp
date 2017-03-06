package com.doulaize.flagapp.model;

/**
 * Created by rdeleuze on 2/24/2017
 */

public class Ratio {

    public static int DEFAULT_RATIO_HORIZONTAL_VALUE = 4;
    public static int DEFAULT_RATIO_VERTICAL_VALUE = 3;

    private Integer EW;
    private Integer NS;

    public Ratio() {

        this.EW = DEFAULT_RATIO_HORIZONTAL_VALUE;
        this.NS = DEFAULT_RATIO_VERTICAL_VALUE;
    }

    public Ratio(Integer EW, Integer NS) {

        this.EW = EW;
        this.NS = NS;
    }

    public Integer getEW() {
        return EW;
    }

    public Integer getNS() {
        return NS;
    }
}
