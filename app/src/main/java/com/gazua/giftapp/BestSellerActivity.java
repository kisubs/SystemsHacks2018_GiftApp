package com.gazua.giftapp;

import com.gazua.giftapp.giftAppModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BestSellerActivity extends AppCompatActivity {
    private giftAppModel gam = giftAppModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_seller);
        populateListView();
        registerClickCall();
        
    }

    private void registerClickCall() {
        ListView list = (ListView) findViewById(R.id.bestseller_list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //item currentItem = gam.getBestSellerList().get(i);
                Toast toast = Toast.makeText(getApplicationContext(), "item clicked", Toast.LENGTH_SHORT);
                toast.show();
                //populateListView();
            }
        });
    }

    private void populateListView() {
        List<item> itemList = gam.getBestSellerList();
        ArrayAdapter<item> adapter = new ItemListAdapter();
        //ArrayAdapter<item> adapter = new ArrayAdapter<item>(this,R.layout.activity_best_seller)>

        ListView listView = (ListView) findViewById(R.id.bestseller_list);
        listView.setAdapter(adapter);


    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, BestSellerActivity.class);
    }



    private class ItemListAdapter extends ArrayAdapter<item> {

        public ItemListAdapter() {
            super(BestSellerActivity.this, R.layout.item_list, gam.getBestSellerList());
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_list, parent,false);
            }

            // Find the current car to work with
            item currentItem = gam.getBestSellerList().get(position);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.image_item_icon);
            imageView.setImageResource(R.drawable.google);

            TextView name = (TextView) itemView.findViewById(R.id.text_item_name);
            name.setText(""+currentItem.getName());

            TextView price = (TextView) itemView.findViewById(R.id.text_item_price);
            price.setText("$ "+currentItem.getPrice());

            return itemView;
        }
    }


    
    

}
