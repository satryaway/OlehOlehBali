package com.jixstreet.oleholehbali;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by satryaway on 11/23/2015.
 * basic page for all activities
 */
public abstract class BaseActivity extends AppCompatActivity {

    public abstract int setView();

    public TextView activityTitleTV;
    public ImageView closeActivityIV;
    public ImageView optionMenuIV;
    public RelativeLayout actionBarWrapper;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(setView());
        sharedPreferences = OlehOlehBaliApplication.getInstance().getSharedPreferences();
        initUI();
        setCallBack();
    }

    public void initUI() {
        actionBarWrapper = (RelativeLayout) findViewById(R.id.action_bar_wrapper);
        activityTitleTV = (TextView) findViewById(R.id.activity_title_tv);
        closeActivityIV = (ImageView) findViewById(R.id.close_activity_iv);
        optionMenuIV = (ImageView) findViewById(R.id.option_menu_iv);
    }

    public void setCallBack() {
        closeActivityIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0);
    }
}
