package com.interview.deckgame.player;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @PostMapping
    public void create() {
        // TODO - create a player
    }

    @GetMapping("/{playerId}/cards")
    public void getCards() {
        // Get the cards of a player
    }
}
