package com.yammer.chesster.service.store;

import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.yammer.chesster.service.model.Game;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class GameStore {

    private AtomicLong ids = new AtomicLong();
    private Map<Long, Game> games = Maps.newConcurrentMap();

    public GameStore() {
    }

    public Game createGame(Game game) {
        long id = game.getId();
        if (id < 0) id = ids.incrementAndGet();
        game.setId(id);
        games.put(id, game);
        return game;
    }

    public Optional<Game> getGame(long id) {
        return Optional.fromNullable(games.get(id));
    }
}
