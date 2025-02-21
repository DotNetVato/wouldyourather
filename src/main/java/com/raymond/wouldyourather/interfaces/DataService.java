package com.raymond.wouldyourather.interfaces;

import java.util.List;

public interface DataService<T> {
    
    List<T> getAll();

    List<T> getAll(Boolean includeUsed);

    T getRandom();

    void refresh();

}
