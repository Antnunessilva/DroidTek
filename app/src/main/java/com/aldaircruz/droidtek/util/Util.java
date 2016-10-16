package com.aldaircruz.droidtek.util;

import java.util.Random;

/**
 * Created by Cruz on 16-10-2016.
 */

public class Util {

    static Random random = new Random();

    public static int randomNumberGenerator(int min, int max){
        int numeroGerado=Math.abs((random.nextInt(max - min + 1) + min));
        return numeroGerado;
    }

}
