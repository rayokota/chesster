package com.yammer.chesster.service.model;

import javax.persistence.*;
import java.util.Objects;

public class GamePropertyKey implements java.io.Serializable {
    private long gameId;
    private String propertyKey;

    public GamePropertyKey() {
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GamePropertyKey)) return false;
        GamePropertyKey that = (GamePropertyKey) o;
        return Objects.equals(gameId, that.gameId) &&
                Objects.equals(propertyKey, that.propertyKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, propertyKey);
    }
}
