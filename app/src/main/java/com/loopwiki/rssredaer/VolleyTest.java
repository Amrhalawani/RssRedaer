package com.loopwiki.rssredaer;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Amr Halawani on 18-02-2018.
 */
public class VolleyTest extends AppCompatActivity {

    String url = "http://www.fifa.com/rss/index.xml";
    //String url = "http://feeds.bbci.co.uk/sport/football/rss.xml";
    // String url = "http://www.google.com";

    Spinner spinner;
    ArrayAdapter<CharSequence> arrayAdapter;
    TextView mTextView, indecator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_test_activity);
        mTextView = (TextView) findViewById(R.id.contenttv);
        indecator = (TextView) findViewById(R.id.indecatorID);

        spinner = (Spinner) findViewById(R.id.my_spinner);
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.chooseSite, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(R.layout.my_layoutforspinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                if (pos == 0) {
                    url = "http://www.fifa.com/rss/index.xml";
                } else if (pos == 1) {
                    url = "https://www.fifa.com/rss/index.xml";
                } else if (pos == 2) {
                    url = "http://feeds.bbci.co.uk/sport/football/rss.xml";
                } else if (pos == 3) {
                    url = "http://www.google.com";
                } else if (pos == 4) {
                    url = "http://rss.cnn.com/rss/edition_world.rss";
                } else if (pos == 5) {
                    url = "http://feeds.feedburner.com/ndtvprofit-latest";
                } else if (pos == 6) {
                    url = "http://www.fifa.com/news/popular/rss.xml";
                } else if (pos == 7) {
                    url = "http://feeds.foxnews.com/foxnews/sports";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void Startvolley(View view) {
        mTextView.setText("");
        // Request Startvolley string response from the provided URL.
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                mTextView.setText(response);
                mTextView.setTextColor(Color.BLACK);
                indecator.setText("worked fine & got the response");
                indecator.setTextColor(Color.GREEN);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                indecator.setText("Didn't get the response ");
                indecator.setTextColor(Color.RED);


                mTextView.setText(" networkResponse.statusCode = " + error.networkResponse.statusCode);


            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
