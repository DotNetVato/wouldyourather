package com.raymond.wouldyourather.model;

import java.util.UUID;

public class Player {

    public String Id;
    public String Name;
    public String Title;
    public String Photo;

    public WouldYouRather WouldYouRather;

    public void setId(String id) {
        this.Id = id;
    }

    public WouldYouRather getWouldYouRather() {
        return this.WouldYouRather;
    }

    public void setWouldYouRather(WouldYouRather wouldYouRather) {
        this.WouldYouRather = wouldYouRather;
    }

    public String getId() {
        return this.Id;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPhoto() {
        return String.format("images/%s.jpg", this.Name);
    }

    public Player(String name, String title) {
        this.Id = UUID.randomUUID().toString();
        this.Name = name;
        this.Title = title;
    }

}
