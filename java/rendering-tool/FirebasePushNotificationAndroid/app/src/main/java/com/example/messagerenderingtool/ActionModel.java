package com.example.messagerenderingtool;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Comarch on 2017-07-12.
 */
@Parcel(Parcel.Serialization.BEAN)
public class ActionModel {

    public static final String ACTION_OFFER = "OFFERS";
    public static final String ACTION_LOCATIONS = "LOCATIONS";
    public static final String ACTION_COUPONS = "COUPONS";
    public static final String ACTION_ACCOUNT = "ACCOUNT";
    public static final String ACTION_CONDITIONS = "CONDITIONS";
    public static final String ACTION_QUESTIONNAIRES = "QUESTIONNAIRES";

    private String action;
    private String name;
    private String id;
    @SerializedName("img_id")
    private String imgId;

    public ActionModel() {

    }

    public ActionModel(String action, String name, String id, String imgId) {
        this.action = action;
        this.name = name;
        this.id = id;
        this.imgId = imgId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }
}