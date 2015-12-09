package com.jixstreet.oleholehbali;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jixstreet.oleholehbali.models.Product;
import com.jixstreet.oleholehbali.models.Transaction;
import com.jixstreet.oleholehbali.utils.CommonConstants;
import com.jixstreet.oleholehbali.utils.Utility;

import java.util.ArrayList;

/**
 * Created by satryaway on 12/9/2015.
 * a class to handle the buyer information
 */
public class BuyerInformationActivity extends BaseActivity {
    private ArrayList<Product> products = new ArrayList<>();
    private Transaction transaction = new Transaction();
    private Button nextBtn;
    private EditText nameET, phoneET, emailET, addressET, subDistrictET, districtET, provinceET, postalCodeET;

    @Override
    public int setView() {
        return R.layout.buyer_information_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleIntent();
        super.onCreate(savedInstanceState);
    }

    private void handleIntent() {
        Intent intent = getIntent();
        products = intent.getParcelableArrayListExtra(CommonConstants.PRODUCTS);
    }

    @Override
    public void initUI() {
        super.initUI();

        activityTitleTV.setText(R.string.input_buyer_information);
        optionMenuIV.setVisibility(View.GONE);
        nextBtn = (Button) findViewById(R.id.next_btn);
        nameET = (EditText) findViewById(R.id.name_et);
        addressET = (EditText) findViewById(R.id.address_et);
        emailET = (EditText) findViewById(R.id.email_et);
        phoneET = (EditText) findViewById(R.id.phone_et);
        subDistrictET = (EditText) findViewById(R.id.sub_district_et);
        districtET = (EditText) findViewById(R.id.district_et);
        provinceET = (EditText) findViewById(R.id.province_et);
        postalCodeET = (EditText) findViewById(R.id.postal_code_et);
    }

    @Override
    public void setCallBack() {
        super.setCallBack();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormFilled()) {
                    Intent intent = new Intent(BuyerInformationActivity.this, PaymentMethodActivity.class);
                    intent.putParcelableArrayListExtra(CommonConstants.PRODUCTS, products);
                    intent.putExtra(CommonConstants.TRANSACTION, transaction);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isFormFilled() {
        int totalFilled = 0;

        if (nameET.getText().toString().isEmpty())
            nameET.setError(getString(R.string.should_not_be_empty));
        else
            totalFilled++;

        if (!Utility.isEmailValid(emailET.getText().toString()))
            emailET.setError(getString(R.string.please_input_a_valid_email));
        else
            totalFilled++;

        if (phoneET.getText().toString().isEmpty())
            phoneET.setError(getString(R.string.should_not_be_empty));
        else
            totalFilled++;

        if (addressET.getText().toString().isEmpty())
            addressET.setError(getString(R.string.should_not_be_empty));
        else
            totalFilled++;

        if (subDistrictET.getText().toString().isEmpty())
            subDistrictET.setError(getString(R.string.should_not_be_empty));
        else
            totalFilled++;

        if (districtET.getText().toString().isEmpty())
            districtET.setError(getString(R.string.should_not_be_empty));
        else
            totalFilled++;

        if (provinceET.getText().toString().isEmpty())
            provinceET.setError(getString(R.string.should_not_be_empty));
        else
            totalFilled++;

        if (postalCodeET.getText().toString().isEmpty())
            postalCodeET.setError(getString(R.string.should_not_be_empty));
        else
            totalFilled++;

        return totalFilled == 8;
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra(CommonConstants.PRODUCTS, products);
        setResult(RESULT_CANCELED, returnIntent);
        super.onBackPressed();
    }
}
