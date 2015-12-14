package com.jixstreet.oleholehbali;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jixstreet.oleholehbali.adapters.ShoppingCartAdapter;
import com.jixstreet.oleholehbali.models.Product;
import com.jixstreet.oleholehbali.models.Transaction;
import com.jixstreet.oleholehbali.utils.APIAgent;
import com.jixstreet.oleholehbali.utils.CommonConstants;
import com.jixstreet.oleholehbali.utils.Seeder;
import com.jixstreet.oleholehbali.utils.Utility;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

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
    private Button finishBtn;

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
        finishBtn = (Button) findViewById(R.id.finish_btn);
    }

    @Override
    public void setCallBack() {
        super.setCallBack();

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyTransaction();
            }
        });
    }

    private void applyTransaction() {
        String url = CommonConstants.SERVICE_POST_TRANSACTION;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.please_wait));

        RequestParams requestParams = new RequestParams();
        requestParams.put(CommonConstants.NAME, transaction.getName());
        requestParams.put(CommonConstants.EMAIL, transaction.getEmail());
        requestParams.put(CommonConstants.PHONE, transaction.getPhone());
        requestParams.put(CommonConstants.ADDRESS, transaction.getAddress());
        requestParams.put(CommonConstants.KEC, transaction.getSub_district());
        requestParams.put(CommonConstants.KAB, transaction.getDistrict());
        requestParams.put(CommonConstants.PROV, transaction.getProvince());
        requestParams.put(CommonConstants.POSTAL_CODE, transaction.getPostal_code());
        requestParams.put(CommonConstants.TOTAL, totalPayout);

        String[] product_ids = new String[products.size()];
        int[] qtys = new int[products.size()];
        for (int i = 0; i < products.size(); i++) {
            product_ids[i] = products.get(i).getId();
            qtys[i] = products.get(i).getQuantity();

            requestParams.put(CommonConstants.PRODUCT_ID + "[" + i + "]", product_ids[i]);
            requestParams.put(CommonConstants.QTY + "[" + i + "]", qtys[i]);
        }

        requestParams.put(CommonConstants.PAYMENT_METHOD, paymentMethod);

        APIAgent.post(url, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                progressDialog.show();
            }

            @Override
            public void onFinish() {
                progressDialog.hide();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    int status = response.getInt(CommonConstants.STATUS);
                    if (status == CommonConstants.RESULT_OK_CODE) {
                        makeAlertDialog(response.getString(CommonConstants.MESSAGE));
                    } else {
                        Utility.makeSnackBar(PreviewActivity.this, response.getString(CommonConstants.MESSAGE));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Utility.makeSnackBar(PreviewActivity.this, getResources().getString(R.string.RTO));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Utility.makeSnackBar(PreviewActivity.this, getResources().getString(R.string.SERVER_DOWN_MESSAGE));
            }
        });

    }

    private void makeAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(CommonConstants.OK, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(PreviewActivity.this, ChooseCategoryActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
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
