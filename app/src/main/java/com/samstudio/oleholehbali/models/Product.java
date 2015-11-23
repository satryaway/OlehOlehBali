package com.samstudio.oleholehbali.models;

/**
 * Created by satryaway on 11/23/2015.
 * an entity to represent product
 */
public class Product {
    private String id, name, description, imageURL;

    public Product(String id, String name, String description, String imageURL) {
        setId(id);
        setName(name);
        setDescription(description);
        setImageURL(imageURL);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
