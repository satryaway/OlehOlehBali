package com.jixstreet.oleholehbali.utils;

import android.app.Activity;

import com.github.mrengineer13.snackbar.SnackBar;
import com.jixstreet.oleholehbali.R;
import com.jixstreet.oleholehbali.models.Product;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by satryaway on 12/6/2015.
 * utilitiy class
 */
public class Utility {

    public static void makeSnackBar(Activity activity, String message) {
        new SnackBar.Builder(activity)
                .withMessage(message)
                .withTextColorId(R.color.colorAccent)
                .withBackgroundColorId(R.color.colorPrimary)
                .withDuration((short) 5000)
                .show();
    }

    public static Product parseProduct(JSONObject productObj) {
        Product product = new Product();

        try {
            product.setId(productObj.getString(CommonConstants.ID));
            product.setCategory(productObj.getString(CommonConstants.CATEGORY));
            product.setName(productObj.getString(CommonConstants.NAME));
            product.setDescription(productObj.getString(CommonConstants.DESCRIPTION));
            product.setSpecification(productObj.getString(CommonConstants.SPECIFICATION));
            product.setPrice(productObj.getString(CommonConstants.PRICE));
            product.setStock(productObj.getString(CommonConstants.STOCK));
            product.setImageURL(productObj.getString(CommonConstants.IMAGE));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return product;
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
