package com.yammer.chesster.service.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(GamePropertyKey.class)
@Table(name = "game_properties")
public class GameProperty {
    private long gameId;
    private String propertyKey;
    private String propertyValue;

    public GameProperty() {
    }

    @Id
    @Column(name="game_id")
    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    @Id
    @Column(name="properties_key")
    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    @Column(name="properties")
    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameProperty)) return false;
        GameProperty that = (GameProperty) o;
        return Objects.equals(propertyKey, that.propertyKey) &&
                Objects.equals(propertyValue, that.propertyValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyKey, propertyValue);
    }
}
