package com.jixstreet.oleholehbali.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by satryaway on 12/9/2015.
 * a model to handle the transaction
 */
public class Transaction implements Parcelable {
    private String name, email, phone, address, sub_district, district, province, postal_code;
    private int total;

    protected Transaction(Parcel in) {
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        address = in.readString();
        sub_district = in.readString();
        district = in.readString();
        province = in.readString();
        postal_code = in.readString();
        total = in.readInt();
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public Transaction (){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSub_district() {
        return sub_district;
    }

    public void setSub_district(String sub_district) {
        this.sub_district = sub_district;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(sub_district);
        dest.writeString(district);
        dest.writeString(province);
        dest.writeString(postal_code);
        dest.writeInt(total);
    }
}
