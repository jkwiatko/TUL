package com.example.messagerenderingtool;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Comarch on 2017-07-11.
 */

public class NotificationModel {

    private String id;

    private String title;
    private String content;
    @SerializedName("sound_id")
    private String soundId;
    @SerializedName("small_icon_id")
    private String smallIconId;
    @SerializedName("small_image_url")
    private String smallImageUrl;
    @SerializedName("large_image_url")
    private String largeImageUrl;
    @SerializedName("click_action_custom")
    private ActionModel clickActionCustom;
    private List<ActionModel> actions = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSoundId() {
        return soundId;
    }

    public void setSoundId(String soundId) {
        this.soundId = soundId;
    }

    public String getSmallIconId() {
        return smallIconId;
    }

    public void setSmallIconId(String smallIconId) {
        this.smallIconId = smallIconId;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    public ActionModel getClickActionCustom() {
        return clickActionCustom;
    }

    public void setClickActionCustom(ActionModel clickActionCustom) {
        this.clickActionCustom = clickActionCustom;
    }

    public List<ActionModel> getActions() {
        return actions;
    }

    public void setActions(List<ActionModel> actions) {
        this.actions = actions;
    }
}
