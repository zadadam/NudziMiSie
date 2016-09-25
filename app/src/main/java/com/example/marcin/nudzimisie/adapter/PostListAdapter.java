package com.example.marcin.nudzimisie.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.marcin.nudzimisie.FeedImageView;
import com.example.marcin.nudzimisie.R;
import com.example.marcin.nudzimisie.app.AppController;
import com.example.marcin.nudzimisie.data.PostItem;

import java.util.List;


public class PostListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<PostItem> postItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public PostListAdapter(Activity activity, List<PostItem> postItems) {
        this.activity = activity;
        this.postItems = postItems;
        // kolory tla - bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.movie_serial_bg);
    }

    @Override
    public int getCount() {
        return postItems.size();
    }

    @Override
    public Object getItem(int location) {
        return postItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.single_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView postTitleTV = (TextView) convertView.findViewById(R.id.post_title_tv);
        TextView postUserNameTV = (TextView) convertView.findViewById(R.id.post_user_name_tv);
        TextView postTagsTV = (TextView) convertView.findViewById(R.id.post_tags_tv);
        TextView postTimestampTV = (TextView) convertView
                .findViewById(R.id.post_timestamp_tv);
       /* NetworkImageView profilePic = (NetworkImageView) convertView
                .findViewById(R.id.profilePic);*/ //zdjecie profilowe

        FeedImageView postIMG = (FeedImageView) convertView
                .findViewById(R.id.post_img);

        PostItem item = postItems.get(position);

        postTitleTV.setText(item.getPostTitle());
        postUserNameTV.setText(item.getPost_user_name());
        postTagsTV.setText(item.getPost_tags());
        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getPostTimestamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        postTimestampTV.setText(timeAgo);





        // user profile pic
      /*  profilePic.setImageUrl(item.getProfilePic(), imageLoader);*/

        // Feed image
        if (item.getImageURL() != null) {
            postIMG.setDefaultImageResId(R.drawable.logo_bar);
            postIMG.setImageUrl(item.getImageURL(), imageLoader);
            postIMG.setVisibility(View.VISIBLE);
            postIMG
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            postIMG.setVisibility(View.GONE);
        }

        return convertView;
    }



}