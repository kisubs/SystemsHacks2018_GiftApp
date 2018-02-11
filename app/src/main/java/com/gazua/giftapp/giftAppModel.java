package com.gazua.giftapp;

import android.content.Context;
import com.gazua.giftapp.App;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jonkim on 2018-02-10.
 */

public class giftAppModel {
    private static giftAppModel instance = new giftAppModel();
    private Context applicationContext = App.getInstance();

    private List<ResultItem> giftList = new ArrayList<>();

    public List<ResultItem> getBestSellerList(){
        for(int i = 0 ; i < 10 ; i++){

            ResultItem tmp = new ResultItem("anItem", "99.99","www.what.com", "www.google.ca");
            giftList.add(tmp);
        }
        return giftList;
    }

    public static giftAppModel getInstance(){
        return instance;
    }
}
