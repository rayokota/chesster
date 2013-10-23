package com.yammer.chesster.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {
    private long id = -1;
    private Map<String, String> properties = Maps.newHashMap();
    private List<String> moves = Lists.newArrayList();

    public Game() {
    }

    public Game(long id, Map<String, String> props) {
        this.id = id;
        this.properties = props;
        this.moves = Lists.newArrayList();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> props) {
        this.properties = props;
    }

    public void addProperty(String name, String value) {
        properties.put(name, value);
    }

    public List<String> getMoves() {
        return moves;
    }

    public void setMoves(List<String> moves) {
        this.moves = moves;
    }

    public void addMove(String move) {
        moves.add(move);
    }

    @JsonIgnore
    public Set<Map.Entry<String, String>> getPropertyEntrySet() {
        return properties.entrySet();
    }

    @JsonIgnore
    public String getMovesAsString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String move : moves) {
            if (i % 2 == 0) {
                sb.append(i / 2 + 1);
                sb.append(". ");
            }
            sb.append(move);
            sb.append(" ");
            i++;
        }
        return sb.toString();
    }
}
