package com.raymond.wouldyourather.helpers;

import com.raymond.wouldyourather.model.Player;


public class PlayerComparator implements java.util.Comparator<Player>{

    @Override
    public int compare(Player playerOne, Player playerTwo) {

        if(playerOne == null || playerTwo == null)
            return 1;

        var result = String.CASE_INSENSITIVE_ORDER.compare(playerOne.Name, playerTwo.Name);
        if (result == 0)
            result = playerOne.Name.compareTo(playerTwo.Name);

        return result;

    }
    
}
