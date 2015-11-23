package com.samstudio.oleholehbali.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.samstudio.oleholehbali.R;
import com.samstudio.oleholehbali.models.Product;
import com.samstudio.oleholehbali.utils.UniversalImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by satryaway on 11/23/2015.
 * adapter to show the image list
 */
public class ProductListAdapter extends BaseAdapter {
    private List<Product> productList = new ArrayList<>();
    private Context context;
    private UniversalImageLoader imageLoader;

    public ProductListAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        imageLoader = new UniversalImageLoader(context);
        imageLoader.initImageLoader();
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.product_lv_item_layout, parent, false);
            holder.nameTV = (TextView) convertView.findViewById(R.id.product_name_tv);
            holder.imageIV= (ImageView) convertView.findViewById(R.id.product_image_iv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameTV.setText(productList.get(position).getName());
        imageLoader.display(holder.imageIV, productList.get(position).getImageURL());

        return convertView;
    }

    class ViewHolder {
        TextView nameTV;
        ImageView imageIV;
    }
}
