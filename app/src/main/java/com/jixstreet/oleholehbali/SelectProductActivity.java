package com.jixstreet.oleholehbali;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.jixstreet.oleholehbali.adapters.ProductListAdapter;
import com.jixstreet.oleholehbali.models.Product;
import com.jixstreet.oleholehbali.utils.APIAgent;
import com.jixstreet.oleholehbali.utils.CommonConstants;
import com.jixstreet.oleholehbali.utils.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by satryaway on 11/23/2015.
 * a page to display products
 */
public class SelectProductActivity extends BaseActivity {
    private ListView productLV;
    private ProductListAdapter mAdapter;
    private String category;
    private List<Product> productList = new ArrayList<>();
    private TextView categoryTV;
    private ImageView categoryIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleIntent();
        super.onCreate(savedInstanceState);
        overridePendingTransition(0,0);
        retrieveData();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        category = intent.getStringExtra(CommonConstants.CATEGORY);
    }

    @Override
    public int setView() {
        return R.layout.select_product_layout;
    }

    @Override
    public void initUI() {
        super.initUI();
        activityTitleTV.setText(R.string.select_product);

        productLV = (ListView) findViewById(R.id.products_lv);
        categoryTV = (TextView) findViewById(R.id.category_tv);
        categoryIV = (ImageView) findViewById(R.id.category_iv);
        mAdapter = new ProductListAdapter(this, new ArrayList<Product>());
        productLV.setAdapter(mAdapter);

        categoryTV.setText(category.substring(0, 1).toUpperCase() + category.substring(1));
        categoryTV.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        if (category.equals(CommonConstants.CULINARY))
            categoryIV.setImageResource(R.drawable.ic_culinary);
    }

    @Override
    public void setCallBack() {
        super.setCallBack();

        productLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectProductActivity.this, ProductDetailActivity.class);
                intent.putExtra(CommonConstants.PRODUCT, productList.get(position));
                startActivity(intent);
            }
        });
    }


    private void retrieveData() {
        String url = CommonConstants.SERVICE_GET_PRODUCT_BY_CATEGORY + category;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.please_wait));

        APIAgent.get(url, null, new JsonHttpResponseHandler(){
            @Override
            public void onStart() {
                progressDialog.show();
            }

            @Override
            public void onFinish() {
                progressDialog.dismiss();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    int status = response.getInt(CommonConstants.STATUS);
                    if (status == CommonConstants.RESULT_OK_CODE) {
                        JSONArray jsonArray = response.getJSONArray(CommonConstants.RETURN_DATA);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject productObj = jsonArray.getJSONObject(i);
                            Product product = Utility.parseProduct(productObj);
                            productList.add(product);
                        }

                        mAdapter.updateContent(productList);
                    }

                    Utility.makeSnackBar(SelectProductActivity.this, response.getString(CommonConstants.MESSAGE));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Utility.makeSnackBar(SelectProductActivity.this, getResources().getString(R.string.RTO));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Utility.makeSnackBar(SelectProductActivity.this, getResources().getString(R.string.SERVER_DOWN_MESSAGE));
            }
        });
    }
}
