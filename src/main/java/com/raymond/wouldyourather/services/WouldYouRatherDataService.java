package com.raymond.wouldyourather.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.raymond.wouldyourather.WouldyouratherApplication;
import com.raymond.wouldyourather.interfaces.DataService;
import com.raymond.wouldyourather.model.WouldYouRather;

import java.io.IOException;

import jakarta.annotation.PostConstruct;

@Service
public class WouldYouRatherDataService implements DataService<WouldYouRather> {

    private List<WouldYouRather> _WouldYouRathers = new ArrayList<>();
    private List<WouldYouRather> _NotUsedRathers = new ArrayList<>();
    
    @Override
    public WouldYouRather getRandom() {
        
        _NotUsedRathers = _getAll(false);

        if (_NotUsedRathers == null || _NotUsedRathers.size() == 0) {
            this.refresh();
            _NotUsedRathers = _getAll(false);
        }

        var idx = new Random().nextInt(_NotUsedRathers.size());
        var wouldyourather = _NotUsedRathers.get(idx);
        if (wouldyourather != null) {
            _updateWouldYouRather(wouldyourather);
            return wouldyourather;
        }

        return wouldyourather;
    }

    @Override
    public List<WouldYouRather> getAll() {
        return _getAll(true);
    }

    @Override
    public List<WouldYouRather> getAll(Boolean includeUsed) {
        return _getAll(includeUsed);
    }

    @Override
    public void refresh() {
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void _updateWouldYouRather(WouldYouRather wouldyourather) {
        var idx = _findWouldYouRather(wouldyourather.Id);
        wouldyourather.setIsUsed(true);
        wouldyourather.setLastUsed(System.currentTimeMillis());
        _WouldYouRathers.set(idx, wouldyourather);
    }

    private Integer _findWouldYouRather(String id) {

        for (Integer i = 0; i < _WouldYouRathers.size(); i++) {
            if (_WouldYouRathers.get(i).Id.equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private List<WouldYouRather> _getAll(Boolean includeUsed) {
        var wyr = _WouldYouRathers.stream()
                .filter(x -> includeUsed ? (x.getIsUsed().equals(true) | x.getIsUsed().equals(false)) : x.getIsUsed().equals(false))
                .collect(Collectors.toList());

        Collections.shuffle(wyr);

        return wyr;
    }

    @PostConstruct
    private void loadData() throws IOException {
        var resource = new ClassPathResource("/static/wouldyourather.txt", WouldyouratherApplication.class);
        try (var inputStream = resource.getInputStream()) {
            _WouldYouRathers.clear();
            var br = new BufferedReader(new InputStreamReader(inputStream));
            for (var line = br.readLine(); line != null; line = br.readLine()) {
                var split = line.split(" or ");
                _WouldYouRathers.add(new WouldYouRather(split[0], split[1]));
            }
        }
    }
}
