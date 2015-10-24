package com.codepath.apps.mysimpletweets.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dvalia on 10/24/15.
 */
public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {


    private static class ViewHolder{

        private TextView tvBody;
        private TextView tvUserName;
        private ImageView ivProfileImage;

    }

    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, R.layout.item_tweet, tweets);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        Tweet tweet = getItem(position);

        ViewHolder viewHolder;

        //check if we are using a recycle view; if not we need to inflate
        if(convertView == null){


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_tweet,parent,false);

            //lookup the views in populating data(image, caption, profileImage, username, likes)
            viewHolder.ivProfileImage = (ImageView)convertView.findViewById(R.id.ivProfileImage);
            viewHolder.tvUserName = (TextView)convertView.findViewById(R.id.tvUserName);
            viewHolder.tvBody = (TextView)convertView.findViewById(R.id.tvBody);



            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();


        }

        //clear out imageview incase having an old image
        viewHolder.ivProfileImage.setImageResource(android.R.color.transparent );



        //insert image using picasso
        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(viewHolder.ivProfileImage);


        viewHolder.tvBody.setText(tweet.getBody());
        viewHolder.tvUserName.setText(tweet.getUser().getScreenName());


        //return the created item as a view
        return convertView;
    }
}
