package com.gazua.giftapp;

/**
 * Created by Bobae on 2018-02-10.
 */

public class ResultItem {

    String title;
    String formattedPrice;
    String pageURL;

    public ResultItem(String title, String formattedPrice, String pageURL) {
        this.title = title;
        this.formattedPrice = formattedPrice;
        this.pageURL = pageURL;
    }

    public String getTitle() {
        return this.title;
    }

    public String getFormattedPrice() {
        return this.formattedPrice;
    }

    public String getPageURL() {
        return this.pageURL;
    }

}
