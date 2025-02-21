package com.raymond.wouldyourather.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.raymond.wouldyourather.WouldyouratherApplication;
import com.raymond.wouldyourather.interfaces.DataService;
import com.raymond.wouldyourather.model.Player;

import jakarta.annotation.PostConstruct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PlayerDataService implements DataService<Player> {

    List<Player> _Players = new ArrayList<>();
    
    @Override
    public Player getRandom() {

        if(_Players.size() == 0)
            return null;

        var idx = new Random().nextInt(_Players.size());

        var player = _Players.get(idx);

        if(player != null)
            _Players.remove(player);

        return player;
    }

    @Override
    public List<Player> getAll(){
        return _Players;
    }

    @Override
    public List<Player> getAll(Boolean includeUsed){
        return _Players;
    }

    @Override
    public void refresh(){
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    private void loadData() throws IOException {

        var resource = new ClassPathResource("/static/players.txt", WouldyouratherApplication.class);
        try (var inputStream = resource.getInputStream()) {
            _Players.clear();
            var br = new BufferedReader(new InputStreamReader(inputStream));
            for (var line = br.readLine(); line != null; line = br.readLine()) {
                var split = line.split("\\|");
                _Players.add(new Player(split[0], split[1]));
            }
        }
    }
}