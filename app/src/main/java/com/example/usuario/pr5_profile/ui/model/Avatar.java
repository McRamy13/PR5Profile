package com.example.usuario.pr5_profile.ui.model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jannu on 30/10/17.
 */
public class Avatar implements Parcelable {

    private String catName;
    private int catTag;
    private String web;
    private String address;
    private String phone;
    private String name;
    private String email;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.catName);
        dest.writeInt(this.catTag);
        dest.writeString(this.web);
        dest.writeString(this.address);
        dest.writeString(this.phone);
        dest.writeString(this.name);
        dest.writeString(this.email);
    }

    public Avatar() {
    }

    protected Avatar(Parcel in) {
        this.catName = in.readString();
        this.catTag = in.readInt();
        this.web = in.readString();
        this.address = in.readString();
        this.phone = in.readString();
        this.name = in.readString();
        this.email = in.readString();
    }

    public static final Parcelable.Creator<Avatar> CREATOR = new Parcelable.Creator<Avatar>() {
        @Override
        public Avatar createFromParcel(Parcel source) {
            return new Avatar(source);
        }

        @Override
        public Avatar[] newArray(int size) {
            return new Avatar[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getCatTag() {
        return catTag;
    }

    public void setCatTag(int catTag) {
        this.catTag = catTag;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
