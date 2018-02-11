package com.gazua.giftapp;

/**
 * Created by Bobae on 2018-02-10.
 */

public class ResultItem {

    String title;
    String formattedPrice;
    String pageURL;
    String imageURL;

    public ResultItem(String title, String formattedPrice, String pageURL, String imageURL) {
        this.title = title;
        this.formattedPrice = formattedPrice;
        this.pageURL = pageURL;
        this.imageURL = imageURL;
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

    public String getImageURL() {
        return this.imageURL;
    }

}
