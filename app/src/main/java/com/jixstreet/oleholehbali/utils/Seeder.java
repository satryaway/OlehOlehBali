package com.jixstreet.oleholehbali.utils;

import com.jixstreet.oleholehbali.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by satryaway on 11/23/2015.
 * a class to collect dummy data
 */
public class Seeder {

    public static List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("1", "Gantungan Kunci", "Gantungan kunci biasa", "https://ecs7.tokopedia.net/img/product-1/2015/7/28/158166/158166_a1204d85-521a-47cd-a377-a919599def5f.jpg"));
        productList.add(new Product("2", "Baju Bali", "Baju Bali Biasa", "https://grosirbajubali.files.wordpress.com/2009/04/setelangables.jpg"));
        productList.add(new Product("3", "Sendal Unik", "Sendal unik biasa", "https://ecs4.tokopedia.net/newimg/product-1/2013/7/6/2172370/2172370_cee5d8b4-e611-11e2-922c-b3da3284bbc6.jpg"));
        productList.add(new Product("4", "Patung Totem", "Patung totem biasa", "http://borneoartifact.com/images/HP0293a.JPG"));
        productList.add(new Product("5", "Gelas Unik", "Gelas unik biasa", "https://ecs4.tokopedia.net/newimg/product-1/2013/4/29/1857771/1857771_9b9d314e-b0da-11e2-86f4-6a722523fab8.jpg"));
        productList.add(new Product("6", "Bed Cover", "Bed cover biasa", "http://www.bedcoverlover.com/product_files/Angela%20Kids/New%20Chelsea.jpg"));
        productList.add(new Product("7", "Action Figure", "Action Figure biasa", "http://gosocio.co.id/wp-content/uploads/2014/08/terminator-action-figures.jpg"));

        return productList;
    }
}
