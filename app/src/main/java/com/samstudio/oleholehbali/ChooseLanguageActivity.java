package com.samstudio.oleholehbali;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ChooseLanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_language_layout);
        getSupportActionBar().hide();
    }
}
