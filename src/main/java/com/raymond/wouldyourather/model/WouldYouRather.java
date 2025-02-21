package com.raymond.wouldyourather.model;

import java.util.UUID;

public class WouldYouRather {
    public String Id;
    public String OptionOne;
    public String OptionTwo;
    public Boolean IsUsed;
    public long LastUsed;
    public void setId(String Id) {
        this.Id = Id;
    }

    public Boolean isIsUsed() {
        return this.IsUsed;
    }

    public long getLastUsed() {
        return this.LastUsed;
    }

    public void setLastUsed(long LastUsed) {
        this.LastUsed = LastUsed;
    }

    public String getId() {
        return this.Id;
    }

    public Boolean isUsed() {
        return this.IsUsed;
    }

    public Boolean getIsUsed() {
        return this.IsUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.IsUsed = isUsed;
    }

    public String getOptionOne() {
        return this.OptionOne;
    }

    public void setOptionOne(String OptionOne) {
        this.OptionOne = OptionOne;
    }

    public String getOptionTwo() {
        return this.OptionTwo;
    }

    public void setOptionTwo(String OptionTwo) {
        this.OptionTwo = OptionTwo;
    }

    public WouldYouRather() {
        this.Id = UUID.randomUUID().toString();
        this.IsUsed = false;
    }

    public WouldYouRather(String OptionOne, String OptionTwo) {
        this.Id = UUID.randomUUID().toString();
        this.OptionOne = OptionOne;
        this.OptionTwo = OptionTwo;
        this.IsUsed = false;
    }
}
