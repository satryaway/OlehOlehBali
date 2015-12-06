package com.jixstreet.oleholehbali;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.jixstreet.oleholehbali.utils.CommonConstants;

/**
 * Created by satryaway on 11/23/2015.
 * page for selecting category
 */
public class ChooseCategoryActivity extends BaseActivity {

    private FrameLayout souvenirWrapper;
    private FrameLayout culinaryWrapper;

    @Override
    public int setView() {
        return R.layout.choose_category_layout;
    }

    @Override
    public void initUI(){
        super.initUI();
        overridePendingTransition(0,0);
        culinaryWrapper = (FrameLayout) findViewById(R.id.culinary_wrapper);
        souvenirWrapper = (FrameLayout) findViewById(R.id.souvenir_wrapper);
    }

    @Override
    public void setCallBack(){
        super.setCallBack();
        culinaryWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseCategoryActivity.this, SelectProductActivity.class);
                intent.putExtra(CommonConstants.CATEGORY, CommonConstants.CULINARY);
                startActivity(intent);
            }
        });

        souvenirWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseCategoryActivity.this, SelectProductActivity.class);
                intent.putExtra(CommonConstants.CATEGORY, CommonConstants.SOUVENIR);
                startActivity(intent);
            }
        });
    }
}
