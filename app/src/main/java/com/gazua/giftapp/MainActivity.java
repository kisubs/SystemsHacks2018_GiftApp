package com.gazua.giftapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

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

        Button btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Search Menu", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        Button btn_best_seller = (Button) findViewById(R.id.btn_best_seller);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Best Seller", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        Button btn_random = (Button) findViewById(R.id.btn_random);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Random", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        Button btn_about = (Button) findViewById(R.id.btn_about);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "GAZUUUUUA!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

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
