package com.gazua.giftapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<ResultItem> resultItemList;
    private AutoCompleteTextView textView;
    private Button searchButton;
    private TextView title;
    private TextView prices;
    private ImageView images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_search);
        // Get a reference to the AutoCompleteTextView in the layout
        textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_textView);
// Get the string array
        String[] countries = getResources().getStringArray(R.array.autocomplete_array);
// Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);

        searchButton = (Button) findViewById(R.id.button_search);

        title = (TextView) findViewById(R.id.title);
        prices = (TextView) findViewById(R.id.prices);
        images = (ImageView) findViewById(R.id.images);


        searchButton.setOnClickListener(onClickListener);
    }




    private void searchByKeyword(String keyword) {
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
        params.put("Condition", "New");
        params.put("Keywords", keyword);

        //parse this fucker
        requestUrl = helper.sign(params);
        Log.d("Main", "Signed URL: \"" + requestUrl + "\"");

        HttpAsyncTask task = new HttpAsyncTask();
        task.execute(requestUrl);

    }


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String jsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = new URL(params[0]);

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            resultItemList = new ArrayList<>();

            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(s)));

                document.getDocumentElement().normalize();

                System.out.println("root element: " + document.getDocumentElement().getNodeName());

                Element rootList = (Element) document.getElementsByTagName("ItemSearchResponse").item(0);
                Element itemList = (Element) rootList.getElementsByTagName("Items").item(0);
                NodeList nList = itemList.getElementsByTagName("Item");

                System.out.println("---------------------");

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    System.out.println("\nCurrent element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                        Element itemAttributes = (Element) eElement.getElementsByTagName("ItemAttributes").item(0);
                        Element offerSummary = (Element) eElement.getElementsByTagName("OfferSummary").item(0);
                        Element lowestPrice = (Element) offerSummary.getElementsByTagName("LowestNewPrice").item(0);
                        Element largeImage = (Element) eElement.getElementsByTagName("LargeImage").item(0);


                        String title = itemAttributes.getElementsByTagName("Title").item(0).getTextContent();
                        String price = "";
                        if(lowestPrice != null) {
                            price = lowestPrice.getElementsByTagName("FormattedPrice").item(0).getTextContent();

                        }
                        String pageURL = eElement.getElementsByTagName("DetailPageURL").item(0).getTextContent();
                        String imageURL = "";
                        if(largeImage != null) {
                            imageURL = largeImage.getElementsByTagName("URL").item(0).getTextContent();
                        }

                        resultItemList.add(new ResultItem(title, price, pageURL, imageURL));
                    }
                }

                for(ResultItem r : resultItemList) {
                    Log.d("Title", r.getTitle());
                    Log.d("Price", r.getFormattedPrice());
                    Log.d("pageURL", r.getPageURL());
                    Log.d("imageURL", r.getImageURL());
                }

                title.setText(resultItemList.get(0).getTitle());
                prices.setText(resultItemList.get(0).getFormattedPrice());
                Picasso.with(getApplicationContext()).load(resultItemList.get(0).getImageURL()).into(images);

                images.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(resultItemList.get(0).getPageURL()));
                        startActivity(intent);
                    }
                });




            } catch (Exception e) {
                e.printStackTrace();
            }


            Log.i("json", s);
        }

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                searchByKeyword(textView.getText().toString());

            } catch (NullPointerException e) {
                System.out.print("Caught the NullPointerException");
            }
        }
    };




}
