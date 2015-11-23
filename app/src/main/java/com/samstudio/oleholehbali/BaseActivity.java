package com.samstudio.oleholehbali;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(setView());
        initUI();
        setCallBack();
    }

    public void initUI() {
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

        optionMenuIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(BaseActivity.this, optionMenuIV);
                popup.getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent();
                        switch (item.getItemId()) {
                            case R.id.menu_shopping_cart:
                                break;

                            case R.id.menu_faq:
                                break;

                            default:
                                break;
                        }
                        return true;
                    }
                });

                popup.show();
            }
        });
    }


}
