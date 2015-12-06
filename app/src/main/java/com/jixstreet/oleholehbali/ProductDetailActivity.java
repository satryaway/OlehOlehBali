package com.jixstreet.oleholehbali;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jixstreet.oleholehbali.models.Product;
import com.jixstreet.oleholehbali.utils.CommonConstants;
import com.jixstreet.oleholehbali.utils.UniversalImageLoader;
import com.jixstreet.oleholehbali.utils.Utility;

import java.util.ArrayList;

/**
 * Created by satryaway on 12/6/2015.
 * activity to display the detail of a product
 */
public class ProductDetailActivity extends BaseActivity {
    private Product product;
    private UniversalImageLoader imageLoader;
    private ImageView productImageIV;
    private TextView priceTV, descriptionTV, specificationTV, stockTV, quantityTV;
    private SeekBar quantitySB;
    private Button addToCartBtn;
    private int quantity = 0;
    private ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleIntent();
        super.onCreate(savedInstanceState);
        putData();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        product = intent.getParcelableExtra(CommonConstants.PRODUCT);
        products = intent.getParcelableArrayListExtra(CommonConstants.PRODUCTS);
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
        addToCartBtn = (Button) findViewById(R.id.add_to_cart_btn);
    }

    @Override
    public void setCallBack() {
        super.setCallBack();

        quantitySB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                quantity = progress;
                quantityTV.setText(quantity + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 0) {
                    Utility.makeSnackBar(ProductDetailActivity.this, getString(R.string.please_set_quantity_not_1));
                } else {
                    putItemToCart();
                }
            }
        });

        optionMenuIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ProductDetailActivity.this, optionMenuIV);
                popup.getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_shopping_cart:
                                Intent intent = new Intent(ProductDetailActivity.this, ShoppingCartActivity.class);
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


    private void putItemToCart() {
        int samePosition = -1;
        int i = 0;

        while(i < products.size() && samePosition == -1) {
            if (this.product.getId().equals(products.get(i).getId())) {
                products.get(i).setQuantity(quantity);
                samePosition = i;
            }
            i++;
        }

        if (samePosition != -1) {
            products.get(samePosition).setQuantity(quantity);
        } else {
            product.setQuantity(quantity);
            products.add(product);
        }

        Utility.makeSnackBar(this, getString(R.string.success_add_item));
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

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra(CommonConstants.PRODUCTS, products);
        setResult(RESULT_CANCELED, returnIntent);
        super.onBackPressed();
    }
}
