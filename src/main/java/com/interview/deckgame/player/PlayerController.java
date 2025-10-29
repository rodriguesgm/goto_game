package com.interview.deckgame.player;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.deckgame.player.internal.PlayerServiceImpl;
import com.interview.deckgame.player.model.PlayerDto;
import com.interview.deckgame.player.model.PlayerMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerController {

    private final   PlayerMapper playerMapper;
    private final PlayerServiceImpl playerService;

    @PostMapping
    public PlayerDto create(@RequestBody PlayerCreateRequest request) {
        return playerMapper.toDto(playerService.create(request.name));
    }

    public record PlayerCreateRequest(String name) {
    }
}
