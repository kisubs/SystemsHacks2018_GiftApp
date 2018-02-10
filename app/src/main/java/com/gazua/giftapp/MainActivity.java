package com.gazua.giftapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        Log.e("main", "fuk");
        testCall();
    }

    public void testCall() {
        SignedRequestsHelper helper;

        try {
            helper = SignedRequestsHelper.getInstance(
                    Constants.endpoint,
                    Constants.awsAccessKeyId,
                    Constants.awsSecretKey);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String requestUrl;

        Map<String, String> params = new HashMap<>();

        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemSearch");
        params.put("AWSAccessKeyId", "AKIAIBWVJ4ZESU4AU22Q");
        params.put("AssociateTag", "giftapp69-20");
        params.put("SearchIndex", "All");
        params.put("ResponseGroup", "Images,ItemAttributes,Offers");

        //parse this fucker
        requestUrl = helper.sign(params);

        Log.d("Main", "Signed URL: \"" + requestUrl + "\"");

    }
}
