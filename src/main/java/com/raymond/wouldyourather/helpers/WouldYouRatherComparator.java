package com.raymond.wouldyourather.helpers;

import com.raymond.wouldyourather.model.WouldYouRather;

public class WouldYouRatherComparator implements java.util.Comparator<WouldYouRather> {

    @Override
    public int compare(WouldYouRather wyr1, WouldYouRather wyr2){

        return Long.compare(wyr2.LastUsed, wyr1.LastUsed);

    }
    
}
