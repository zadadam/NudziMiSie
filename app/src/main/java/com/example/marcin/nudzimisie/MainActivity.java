package com.example.marcin.nudzimisie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.widget.SwipeRefreshLayout;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.marcin.nudzimisie.adapter.PostListAdapter;
import com.example.marcin.nudzimisie.app.AppController;
import com.example.marcin.nudzimisie.data.PostItem;
import com.example.marcin.nudzimisie.libraries.MyLibrary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
//zmieniłem z Activity na ActionBarActivity
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PostListAdapter listAdapter;
    private List<PostItem> postItems;
    private Uri fileUri; //ZDJECIA ITP


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);


        postItems = new ArrayList<PostItem>();

        listAdapter = new PostListAdapter(this, postItems);
        listView.setAdapter(listAdapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        fetchPosts();
                                    }
                                }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();

        MyLibrary.isOnline(this);
    }

    /**
     * Parsing json reponse and passing the data to feed view list adapter
     * */
    private void parseJsonPost(JSONObject response) {
        try {
            postItems.clear();
            JSONArray postArray = response.getJSONArray("posts");

            for (int i = 0; i < postArray.length(); i++) {
                JSONObject postObj = (JSONObject) postArray.get(i);

                PostItem item = new PostItem();
                item.setPostId(postObj.getInt("post_id"));
                item.setPostTitle(postObj.getString("post_title"));
                item.setPost_user_name(postObj.getString("username"));
                // Image might be null sometimes
                String post_img_url = postObj.isNull("post_img_url") ? null : postObj
                        .getString("post_img_url");
                item.setImageURL(post_img_url);
                item.setPostTimestamp(postObj.getString("post_timestamp"));
                item.setPost_tags(postObj.getString("post_tags"));

                postItems.add(item);
            }

            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            case R.id.action_take_picture:
                takePicture();
                return true;
            case R.id.action_logout:
                logout();
                return true;
            case R.id.action_add_picture:
                addPicture();
                return true;
            case R.id.action_add_content:
                Intent i = new Intent(this, UploadContent.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addPicture() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,1);
    }

    private void openSearch() {

    }
    private void openSettings() {

    }
    private void takePicture() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,Constants.CAM_REQUEST);
    }

    public void logout() {
        SharedPreferences sharedpreferences = getSharedPreferences(Constants.USER_DATA_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        moveTaskToBack(true);
        MainActivity.this.finish();
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    // exit on double click *********************************************
    private boolean doubleBackToExitPressedOnce;

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            moveTaskToBack(true);
            this.finish();
            return;
        }



        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.double_back_on_exit_toas), Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        fetchPosts();
        Log.d(TAG, "SWIIIIIIPE DOOOOOOOOOOWN");
    }

    private void fetchPosts() {

        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);


        // making fresh volley request and getting json
        JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
                Constants.POSTS_JSON_ARRAY_URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d(TAG, "Response: " + response.toString());
                if (response != null) {
                    parseJsonPost(response);
                    // stops showing refreshing image
                    swipeRefreshLayout.setRefreshing(false);
                }else Log.d(TAG, "puste DOOOOO");
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(jsonReq);




    }

    //***********************************************************************************

}