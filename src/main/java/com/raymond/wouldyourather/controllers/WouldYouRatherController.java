package com.raymond.wouldyourather.controllers;

import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.raymond.wouldyourather.helpers.PlayerComparator;
import com.raymond.wouldyourather.helpers.WouldYouRatherComparator;

import com.raymond.wouldyourather.services.PlayerDataService;
import com.raymond.wouldyourather.services.WouldYouRatherDataService;

@Controller
public class WouldYouRatherController {

    final PlayerDataService playerService;
    final WouldYouRatherDataService wouldYouRatherService;

    public WouldYouRatherController(PlayerDataService playerService, WouldYouRatherDataService wouldYouRatherService) {
        this.playerService = playerService;
        this.wouldYouRatherService = wouldYouRatherService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {

        var ply = playerService.getAll();
        var wyr = wouldYouRatherService.getAll(true);
        
        Collections.sort(ply, new PlayerComparator());
        Collections.sort(wyr, new WouldYouRatherComparator());

        model.addAttribute("players", ply);
        model.addAttribute("wouldyourathers", wyr);

        return "index";
    }

    @PostMapping("/submit")
    public String Submit(@RequestParam(required = false) String action, RedirectAttributes redirectAttributes) {
        
        if (action.equals("pickPlayer")) {
            
            var player = playerService.getRandom();
            if (player != null){
                player.WouldYouRather = wouldYouRatherService.getRandom();
            }

            redirectAttributes.addFlashAttribute("player", player);

        } else if (action.equals("refreshPlayers")){
            playerService.refresh();
        } else if (action.equals("refreshWouldYouRathers")){
            wouldYouRatherService.refresh();
        }

        return "redirect:/";
    }
   
}
