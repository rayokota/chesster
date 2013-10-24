package com.yammer.chesster.service.store;

import com.google.common.base.Optional;
import com.google.common.collect.*;
import com.yammer.chesster.service.model.Game;
import com.yammer.chesster.service.model.GameProperty;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class GameStore extends AbstractDAO<Game> {

    private GamePropertyStore gamePropertyStore;
    private boolean persistent = true;
    private AtomicLong ids = new AtomicLong();
    private Map<Long, Game> games = Maps.newConcurrentMap();

    public GameStore(SessionFactory provider, boolean persistent) {
        super(provider);
        this.gamePropertyStore = new GamePropertyStore(provider);
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

    public Set<Game> getGames(String propertyKey, String propertyValue) {
        Set<Game> result = Sets.newHashSet();
        if (persistent) {
            List<GameProperty> properties = gamePropertyStore.getGameProperties(propertyKey, propertyValue);
            for (GameProperty property : properties) {
                Game game = get(property.getGameId());
                if (game != null) {
                    result.add(game);
                }
            }
        } else {
            for (Map.Entry<Long, Game> entry : games.entrySet()) {
                Game game = entry.getValue();
                for (Map.Entry<String, String> prop : game.getProperties().entrySet()) {
                    if (prop.getKey().equals(propertyKey) && prop.getValue().equals(propertyValue)) {
                        result.add(game);
                    }
                }
            }
        }
        return result;
    }
}
