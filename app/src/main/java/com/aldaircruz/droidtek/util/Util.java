package com.aldaircruz.droidtek.util;

import com.aldaircruz.droidtek.model.Product;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Cruz on 16-10-2016.
 */

public class Util {

    static Random random = new Random();

    public static int randomNumberGenerator(int min, int max){
        return Math.abs((random.nextInt(max - min + 1) + min));
    }

    public static Double sumOfProducts(ArrayList<Double> products){
        Double sum=0d;

        for (Double aDouble:products) {
            sum+=aDouble;
        }
        return sum;

    }

}
