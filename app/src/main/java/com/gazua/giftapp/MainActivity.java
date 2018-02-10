package com.gazua.giftapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MainActivity extends AppCompatActivity {

    public static String request = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        Log.e("main", "fuk");

        Button btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Search Menu", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        Button btn_best_seller = (Button) findViewById(R.id.btn_best_seller);
        btn_best_seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Best Seller", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        Button btn_random = (Button) findViewById(R.id.btn_random);
        btn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Random", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        Button btn_about = (Button) findViewById(R.id.btn_about);
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "GAZUUUUUA!", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

    }

}
