package com.jixstreet.oleholehbali;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListView;
import android.widget.TextView;

import com.jixstreet.oleholehbali.adapters.ProductListAdapter;
import com.jixstreet.oleholehbali.adapters.ShoppingCartAdapter;
import com.jixstreet.oleholehbali.models.Product;
import com.jixstreet.oleholehbali.models.Transaction;
import com.jixstreet.oleholehbali.utils.CommonConstants;
import com.jixstreet.oleholehbali.utils.Seeder;

import java.util.ArrayList;

/**
 * Created by satryaway on 12/9/2015.
 * preview the cart
 */
public class PreviewActivity extends BaseActivity {
    private ArrayList<Product> products;
    private Transaction transaction;
    private TextView nameTV, emailTV, addressTV, phoneTV, subDistrictTV, districtTV, postalCodeTV, provinceTV;
    private int paymentMethod;
    private TextView paymentMethodTV;
    private int totalPayout;
    private TextView payoutTotalTV;

    @Override
    public int setView() {
        return R.layout.preview_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleIntent();
        super.onCreate(savedInstanceState);
        putData();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        products = intent.getParcelableArrayListExtra(CommonConstants.PRODUCTS);
        transaction = intent.getParcelableExtra(CommonConstants.TRANSACTION);
        paymentMethod = intent.getIntExtra(CommonConstants.PAYMENT_METHOD, 0);
    }

    @Override
    public void initUI() {
        super.initUI();

        ListView productsLV = (ListView) findViewById(R.id.products_lv);
        ShoppingCartAdapter adapter = new ShoppingCartAdapter(this, products);
        productsLV.setAdapter(adapter);

        nameTV = (TextView) findViewById(R.id.name_tv);
        emailTV = (TextView) findViewById(R.id.email_tv);
        phoneTV = (TextView) findViewById(R.id.phone_tv);
        addressTV = (TextView) findViewById(R.id.address_tv);
        subDistrictTV = (TextView) findViewById(R.id.sub_district_tv);
        districtTV = (TextView) findViewById(R.id.district_tv);
        provinceTV = (TextView) findViewById(R.id.province_tv);
        postalCodeTV = (TextView) findViewById(R.id.postal_code_tv);
        paymentMethodTV = (TextView) findViewById(R.id.payment_method_tv);
        payoutTotalTV = (TextView) findViewById(R.id.payout_total_tv);
    }

    @Override
    public void setCallBack() {
        super.setCallBack();
    }

    private void putData() {
        nameTV.setText(transaction.getName());
        emailTV.setText(transaction.getEmail());
        phoneTV.setText(transaction.getPhone());
        addressTV.setText(transaction.getAddress());
        subDistrictTV.setText(transaction.getSub_district());
        districtTV.setText(transaction.getDistrict());
        provinceTV.setText(transaction.getPhone());
        postalCodeTV.setText(transaction.getPostal_code());
        paymentMethodTV.setText(Seeder.getPaymentMethod()[paymentMethod]);

        for (Product product : products) {
            int price = Integer.valueOf(product.getPrice());
            int subTotal = price * product.getQuantity();
            totalPayout = totalPayout + subTotal;
        }

        payoutTotalTV.setText(totalPayout + "");
    }
}
