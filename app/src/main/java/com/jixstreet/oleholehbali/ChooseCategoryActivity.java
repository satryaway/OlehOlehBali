package com.jixstreet.oleholehbali;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupMenu;

import com.jixstreet.oleholehbali.models.Product;
import com.jixstreet.oleholehbali.utils.CommonConstants;

import java.util.ArrayList;

/**
 * Created by satryaway on 11/23/2015.
 * page for selecting category
 */
public class ChooseCategoryActivity extends BaseActivity {

    private FrameLayout souvenirWrapper;
    private FrameLayout culinaryWrapper;
    private ArrayList<Product> products = new ArrayList<>();

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
                intent.putParcelableArrayListExtra(CommonConstants.PRODUCTS, products);
                startActivityForResult(intent, CommonConstants.PRODUCT_CODE);
            }
        });

        souvenirWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseCategoryActivity.this, SelectProductActivity.class);
                intent.putExtra(CommonConstants.CATEGORY, CommonConstants.SOUVENIR);
                intent.putParcelableArrayListExtra(CommonConstants.PRODUCTS, products);
                startActivityForResult(intent, CommonConstants.PRODUCT_CODE);
            }
        });

        optionMenuIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ChooseCategoryActivity.this, optionMenuIV);
                popup.getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_shopping_cart:
                                Intent intent = new Intent(ChooseCategoryActivity.this, ShoppingCartActivity.class);
                                intent.putParcelableArrayListExtra(CommonConstants.PRODUCTS, products);
                                startActivityForResult(intent, CommonConstants.PRODUCT_CODE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        products = data.getParcelableArrayListExtra(CommonConstants.PRODUCTS);
    }
}
