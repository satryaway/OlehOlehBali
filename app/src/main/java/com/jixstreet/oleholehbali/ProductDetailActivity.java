package com.jixstreet.oleholehbali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jixstreet.oleholehbali.models.Product;
import com.jixstreet.oleholehbali.utils.CommonConstants;
import com.jixstreet.oleholehbali.utils.UniversalImageLoader;

/**
 * Created by satryaway on 12/6/2015.
 * activity to display the detail of a product
 */
public class ProductDetailActivity extends BaseActivity {
    private Product product;
    private UniversalImageLoader imageLoader;
    private ImageView productImageIV;
    private TextView priceTV, descriptionTV, specificationTV,stockTV, quantityTV;
    private SeekBar quantitySB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleIntent();
        super.onCreate(savedInstanceState);
        putData();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        product = intent.getParcelableExtra(CommonConstants.PRODUCT);
    }

    @Override
    public int setView() {
        return R.layout.product_detail_layout;
    }

    @Override
    public void initUI() {
        super.initUI();

        imageLoader = new UniversalImageLoader(this);
        imageLoader.initImageLoader();

        productImageIV = (ImageView) findViewById(R.id.product_image_iv);
        priceTV = (TextView) findViewById(R.id.price_tv);
        descriptionTV = (TextView) findViewById(R.id.description_tv);
        specificationTV = (TextView) findViewById(R.id.specification_tv);
        stockTV = (TextView) findViewById(R.id.stock_tv);
        quantitySB = (SeekBar) findViewById(R.id.quantity_sb);
        quantityTV = (TextView) findViewById(R.id.quantity_tv);
    }

    @Override
    public void setCallBack() {
        super.setCallBack();

        quantitySB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                quantityTV.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void putData() {
        imageLoader.display(productImageIV, CommonConstants.SERVICE_GET_PRODUCT_IMAGE + product.getImageURL());
        activityTitleTV.setText(product.getName());
        priceTV.setText(product.getPrice());
        descriptionTV.setText(product.getDescription());
        specificationTV.setText(product.getSpecification());
        stockTV.setText(product.getStock());
        quantitySB.setMax(Integer.valueOf(product.getStock()));
    }
}
