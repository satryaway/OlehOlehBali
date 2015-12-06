package com.jixstreet.oleholehbali.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jixstreet.oleholehbali.R;
import com.jixstreet.oleholehbali.models.Product;
import com.jixstreet.oleholehbali.utils.CommonConstants;
import com.jixstreet.oleholehbali.utils.UniversalImageLoader;

import java.util.ArrayList;

/**
 * Created by satryaway on 12/6/2015.
 */
public class ShoppingCartAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Product> products = new ArrayList<>();
    private UniversalImageLoader imageLoader;

    public ShoppingCartAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
        imageLoader = new UniversalImageLoader(context);
        imageLoader.initImageLoader();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.cart_lv_item_layout, parent, false);
            holder.nameTV = (TextView) convertView.findViewById(R.id.product_name_tv);
            holder.priceTV = (TextView) convertView.findViewById(R.id.price_tv);
            holder.quantityTV = (TextView) convertView.findViewById(R.id.quantity_tv);
            holder.imageIV = (ImageView) convertView.findViewById(R.id.product_image_iv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTV.setText(products.get(position).getName());
        holder.priceTV.setText(products.get(position).getPrice());
        holder.quantityTV.setText(products.get(position).getQuantity() + "");
        imageLoader.display(holder.imageIV, CommonConstants.SERVICE_GET_PRODUCT_IMAGE + products.get(position).getImageURL());

        return convertView;
    }

    public void updateContent(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView nameTV, priceTV, quantityTV;
        ImageView imageIV;
    }
}
