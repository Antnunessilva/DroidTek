package com.aldaircruz.droidtek.data;

import com.aldaircruz.droidtek.model.Client;
import com.aldaircruz.droidtek.model.Product;

import java.util.ArrayList;

/**
 * Created by Cruz on 16-10-2016.
 */

public class StaticData {

    public static ArrayList<Client> client = new ArrayList<>(20);
    public static ArrayList<Product> products = new ArrayList<>();
    public static int lastEditedSerial=0;

}
