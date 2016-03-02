package com.compassites.friends.UI;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.compassites.friends.Adapter.RecyclerAdapter;
import com.compassites.friends.Model.Friend;
import com.compassites.friends.R;
import com.compassites.friends.Utils.ScrollUtils;
import com.compassites.friends.Utils.SpacesItemDecoration;
import com.compassites.friends.Utils.Util;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AboutFriendActivity extends AppCompatActivity {


    private Toolbar mToolbar;
    private TextView textTitle;
    private ImageView image, imageProfile;
    private RecyclerView recyclerView;
    private Context activity;
    private ArrayList<Friend> storyList;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_friend);
        init();
        loadData(Util.readFile(getApplicationContext()));
    }


    private void init() {
        activity = this;
        imageLoader = ImageLoader.getInstance();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        textTitle = (TextView) findViewById(R.id.textTitle);
        image = (ImageView) findViewById(R.id.image);
        imageProfile = (ImageView) findViewById(R.id.imageProfile);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        setSupportActionBar(mToolbar);
        mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.app_light)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupRecyclerView() {
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
        recyclerAdapter.addItems(storyList);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void loadData(String result) {
        try {
            JSONObject resultObj = new JSONObject(result);
            JSONArray storyArray = resultObj.getJSONArray("our_story");
            storyList = new ArrayList<>();

            textTitle.setText(resultObj.getString("name"));
            imageLoader.displayImage(resultObj.getString("photo"), image);
            imageLoader.displayImage(resultObj.getString("photo"), imageProfile);

            for (int s = 0; s < storyArray.length(); s++) {
                JSONObject storyObj = storyArray.getJSONObject(s);
                Friend friend = new Friend();
                friend.setType(storyObj.getString("type"));
                friend.setTitle(storyObj.getString("title"));

                if (friend.getType().equalsIgnoreCase(activity.getResources().getString(R.string.simple_card))) {
                    friend.setIsSimple(true);
                    friend.setContent(storyObj.getString("content"));
                } else {
                    friend.setIsSimple(false);
                    friend.setImage_url(storyObj.getString("image_url"));
                    friend.setLocation_url(storyObj.getString("location_url"));
                    if (storyObj.has("more_images"))
                        friend.setMore_images_url(storyObj.getString("more_images"));
                    else
                        friend.setMore_images_url("");
                }
                storyList.add(friend);
            }
            setupRecyclerView();


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
