package com.jixstreet.oleholehbali;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.jixstreet.oleholehbali.adapters.ShoppingCartAdapter;
import com.jixstreet.oleholehbali.models.Product;
import com.jixstreet.oleholehbali.utils.CommonConstants;

import java.util.ArrayList;

/**
 * Created by satryaway on 12/6/2015.
 * shopping cart activity
 */
public class ShoppingCartActivity extends BaseActivity {

    private ArrayList<Product> products;
    private Button checkOutBtn;
    private ListView cartLV;
    private ShoppingCartAdapter mAdapter;
    private int totalPayout = 0;
    private TextView payoutTotalTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleIntent();
        super.onCreate(savedInstanceState);
        putData();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        products = intent.getParcelableArrayListExtra(CommonConstants.PRODUCTS);
    }

    @Override
    public int setView() {
        return R.layout.cart_layout;
    }

    @Override
    public void initUI() {
        super.initUI();

        cartLV = (ListView) findViewById(R.id.cart_lv);
        checkOutBtn = (Button) findViewById(R.id.check_out_btn);
        payoutTotalTV = (TextView) findViewById(R.id.payout_total_tv);

        activityTitleTV.setText(R.string.shopping_cart);
        optionMenuIV.setVisibility(View.GONE);

        mAdapter = new ShoppingCartAdapter(this, products);
        cartLV.setAdapter(mAdapter);
    }

    @Override
    public void setCallBack() {
        super.setCallBack();

        /*cartLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShoppingCartActivity.this, ProductDetailActivity.class);
                intent.putParcelableArrayListExtra(CommonConstants.PRODUCTS, products);
                intent.putExtra(CommonConstants.PRODUCT, products.get(position));
                startActivityForResult(intent, CommonConstants.PRODUCT_CODE);
            }
        });*/
    }

    private void putData() {
        for (Product product : products) {
            int price = Integer.valueOf(product.getPrice());
            int subTotal = price * product.getQuantity();
            totalPayout = totalPayout + subTotal;
        }

        payoutTotalTV.setText(totalPayout + "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        products = data.getParcelableArrayListExtra(CommonConstants.PRODUCTS);
        mAdapter.updateContent(products);
    }


    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra(CommonConstants.PRODUCTS, products);
        setResult(RESULT_CANCELED, returnIntent);
        super.onBackPressed();
    }
}
