package com.codepath.apps.mysimpletweets.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dvalia on 10/24/15.
 */
public class User {

    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;


    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public long getUid() {
        return uid;
    }

    public String getScreenName() {
        return screenName;
    }



    public static User fromJson(JSONObject jsonObject){

        User user = new User();

        try {
            user.name = jsonObject.getString("name");
            user.uid = jsonObject.getLong("id");
            user.profileImageUrl = jsonObject.getString("profile_image_url");
            user.screenName = jsonObject.getString("screen_name");
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        return user;
    }
}
