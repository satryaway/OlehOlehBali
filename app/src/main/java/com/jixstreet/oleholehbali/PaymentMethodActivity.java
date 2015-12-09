package com.jixstreet.oleholehbali;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jixstreet.oleholehbali.models.Product;
import com.jixstreet.oleholehbali.models.Transaction;
import com.jixstreet.oleholehbali.utils.CommonConstants;
import com.jixstreet.oleholehbali.utils.Seeder;

import java.util.ArrayList;

/**
 * Created by satryaway on 12/9/2015.
 * activity to show the payment method
 */
public class PaymentMethodActivity extends BaseActivity {
    private ArrayList<Product> products;
    private ListView paymentMethodLV;
    private Transaction transaction;

    @Override
    public int setView() {
        return R.layout.payment_method_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleIntent();
        super.onCreate(savedInstanceState);
    }

    private void handleIntent() {
        Intent intent = getIntent();
        products = intent.getParcelableArrayListExtra(CommonConstants.PRODUCTS);
        transaction = intent.getParcelableExtra(CommonConstants.TRANSACTION);
    }

    @Override
    public void initUI() {
        super.initUI();

        paymentMethodLV = (ListView) findViewById(R.id.payment_method_lv);
        optionMenuIV.setVisibility(View.GONE);
        activityTitleTV.setText(R.string.choose_payment_method);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, Seeder.getPaymentMethod());

        paymentMethodLV.setAdapter(adapter);
    }

    @Override
    public void setCallBack() {
        super.setCallBack();

        paymentMethodLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PaymentMethodActivity.this, PreviewActivity.class);
                intent.putParcelableArrayListExtra(CommonConstants.PRODUCTS, products);
                intent.putExtra(CommonConstants.TRANSACTION, transaction);
                intent.putExtra(CommonConstants.PAYMENT_METHOD, position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra(CommonConstants.PRODUCTS, products);
        setResult(RESULT_CANCELED, returnIntent);
        super.onBackPressed();
    }
}
