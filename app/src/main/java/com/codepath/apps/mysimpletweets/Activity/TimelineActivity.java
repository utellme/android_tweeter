package com.codepath.apps.mysimpletweets.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.codepath.apps.mysimpletweets.Adapters.TweetsArrayAdapter;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimelineActivity extends AppCompatActivity {

    private TwitterClient client;
    private TweetsArrayAdapter tweetsAdapter;
    private ArrayList<Tweet> tweets;
    private ListView lvTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        lvTweets = (ListView)findViewById(R.id.lvTweets);

        tweets = new ArrayList<>();

        tweetsAdapter = new TweetsArrayAdapter(this, tweets);

        lvTweets.setAdapter(tweetsAdapter);

        client = TwitterApplication.getRestClient(); //get a singleton rest client

        populateTimeline();
    }

    private void populateTimeline(){

        client.getHomeTimeline(new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
               // super.onSuccess(statusCode, headers, response);

                tweetsAdapter.addAll(Tweet.fromJsonArray(response));

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                super.onFailure(statusCode, headers, throwable, errorResponse);

                System.out.println("Inside onFailure: " + errorResponse.toString());
            }


        });
    }
}
