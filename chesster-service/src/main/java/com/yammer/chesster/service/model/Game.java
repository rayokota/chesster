package com.yammer.chesster.service.model;

import com.alonsoruibal.chess.Board;
import com.alonsoruibal.chess.Config;
import com.alonsoruibal.chess.Move;
import com.alonsoruibal.chess.Pgn;
import com.alonsoruibal.chess.book.FileBook;
import com.alonsoruibal.chess.search.SearchEngine;
import com.alonsoruibal.chess.search.SearchParameters;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {
    private long id = -1;
    private Map<String, String> properties = Maps.newHashMap();
    @JsonIgnore
    private SearchEngine searchEngine;

    public Game() {
        Config config = new Config();
        config.setBook(new FileBook("book_small.bin"));
        searchEngine = new SearchEngine(config);
    }

    public Game(long id, Map<String, String> props) {
        this.id = id;
        this.properties = props;
        Config config = new Config();
        config.setBook(new FileBook("book_small.bin"));
        searchEngine = new SearchEngine(config);
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

    public String getProperty(String name) {
        return properties.get(name);
    }

    public void addProperty(String name, String value) {
        properties.put(name, value);
    }

    public boolean addMove(String moveString) {
        Board board = searchEngine.getBoard();
        int move = Move.getFromString(board, moveString, false);
        return board.doMove(move, true);
    }

    public String getPgn() {
        Pgn pgn = new Pgn();
        return pgn.getPgn(searchEngine.getBoard(), getProperty("white"), getProperty("black"));
    }

    /*
    @JsonIgnore
    public String getPgn() {
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
    */

    @JsonIgnore
    public String getBestMove() {
        searchEngine.go(SearchParameters.get(10000)); // 10 seconds max
        String bestOperation = Move.toString(searchEngine.getBestMove());
        return bestOperation;
    }
}
