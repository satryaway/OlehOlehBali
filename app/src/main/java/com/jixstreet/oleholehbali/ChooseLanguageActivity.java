package com.jixstreet.oleholehbali;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseLanguageActivity extends BaseActivity {

    private Button bahasaBtn;
    private Button englishBtn;

    @Override
    public int setView() {
        return R.layout.choose_language_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initUI() {
        super.initUI();
        actionBarWrapper.setVisibility(View.GONE);
        englishBtn = (Button) findViewById(R.id.english_btn);
        bahasaBtn = (Button) findViewById(R.id.bahasa_btn);
    }

    @Override
    public void setCallBack() {
        super.setCallBack();

        englishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLanguageActivity.this, ChooseCategoryActivity.class);
                startActivity(intent);
            }
        });

        bahasaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLanguageActivity.this, ChooseCategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
