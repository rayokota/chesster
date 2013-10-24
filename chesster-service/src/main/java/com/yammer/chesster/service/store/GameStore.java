package com.yammer.chesster.service.store;

import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.yammer.chesster.service.model.Game;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class GameStore extends AbstractDAO<Game> {

    private boolean persistent = true;
    private AtomicLong ids = new AtomicLong();
    private Map<Long, Game> games = Maps.newConcurrentMap();

    public GameStore(SessionFactory provider, boolean persistent) {
        super(provider);
        this.persistent = persistent;
    }

    public Game createGame(Game game) {
        long id = game.getId();
        if (id < 0) id = ids.incrementAndGet();
        game.setId(id);
        if (persistent) {
            persist(game);
        } else {
            games.put(id, game);
        }
        return game;
    }

    public Optional<Game> getGame(long id) {
        if (persistent) {
            return Optional.fromNullable(get(id));
        } else {
            return Optional.fromNullable(games.get(id));
        }
    }

    public void deleteGame(Game game) {
        if (persistent) {
            currentSession().delete(game);
        } else {
            games.remove(game.getId());
        }
    }
}
