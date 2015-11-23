package com.samstudio.oleholehbali;

import android.widget.ListView;

import com.samstudio.oleholehbali.adapters.ProductListAdapter;
import com.samstudio.oleholehbali.utils.Seeder;

/**
 * Created by satryaway on 11/23/2015.
 * a page to display products
 */
public class SelectProductActivity extends BaseActivity {
    private ListView productLV;
    private ProductListAdapter mAdapter;

    @Override
    public int setView() {
        return R.layout.select_product_layout;
    }

    @Override
    public void initUI() {
        super.initUI();
        activityTitleTV.setText(R.string.select_product);

        productLV = (ListView) findViewById(R.id.products_lv);
        mAdapter = new ProductListAdapter(this, Seeder.getProductList());
        productLV.setAdapter(mAdapter);
    }

    @Override
    public void setCallBack() {
        super.setCallBack();
    }
}
