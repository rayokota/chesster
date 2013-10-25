package com.yammer.chesster.service.model;

import com.alonsoruibal.chess.Board;
import com.alonsoruibal.chess.Config;
import com.alonsoruibal.chess.Move;
import com.alonsoruibal.chess.Pgn;
import com.alonsoruibal.chess.book.FileBook;
import com.alonsoruibal.chess.search.SearchEngine;
import com.alonsoruibal.chess.search.SearchParameters;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    private long id;
    private Map<String, String> properties = Maps.newHashMap();

    @JsonIgnore
    private transient SearchEngine searchEngine;

    public Game() {
        this.id = -1;
        this.properties = Maps.newHashMap();
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

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="game_properties", joinColumns=@JoinColumn(name="game_id"))
    @Column(name="properties")
    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> props) {
        this.properties = props;
    }

    @Transient
    public String getProperty(String name) {
        return properties.get(name);
    }

    public void addProperty(String name, String value) {
        properties.put(name, value);
    }

    @JsonProperty
    @Transient
    public GameState getGameState() {
        switch (searchEngine.getBoard().isEndGame()) {
            case 1:
                return GameState.WHITE_WON;
            case -1:
                return GameState.BLACK_WON;
            case 99:
                return GameState.DRAW;
            default:
                return GameState.IN_PROGRESS;
        }
    }

    @JsonProperty
    @Transient
    public String getPgn() {
        Pgn pgn = new Pgn();
        return pgn.getPgn(searchEngine.getBoard(), getProperty("white"), getProperty("black"));
    }

    @JsonIgnore
    @Column(name="pgn")
    public String getPgnMoves() {
        Pgn pgn = new Pgn();
        return pgn.getPgnMoves(searchEngine.getBoard());
    }

    public void setPgnMoves(String pgnMoves) {
        Pgn pgn = new Pgn();
        pgn.setBoard(searchEngine.getBoard(), pgnMoves);
    }

    @JsonIgnore
    @Transient
    public String getFen() {
        return searchEngine.getBoard().getFen();
    }

    public boolean addMove(String moveString) {
        Board board = searchEngine.getBoard();
        int move = Move.getFromString(board, moveString, false);
        return board.doMove(move, true);
    }

    public void undoMove() {
        Board board = searchEngine.getBoard();
        board.undoMove();
    }

    @JsonIgnore
    @Transient
    public String getBestMove(int computerMoveTime) {
        searchEngine.go(SearchParameters.get(computerMoveTime));
        return Move.toString(searchEngine.getBestMove());
    }

    @JsonProperty
    @Transient
    public String getLastMove() {
        Board board = searchEngine.getBoard();
        return Move.toString(board.getLastMove());
    }

    @JsonProperty
    @Transient
    public boolean isComputer() {
        String whiteId = getProperty("whiteId");
        String blackId = getProperty("blackId");
        boolean isWhiteComputer = whiteId == null || Long.parseLong(whiteId) < 0;
        boolean isBlackComputer = blackId == null || Long.parseLong(blackId) < 0;
        return isWhiteComputer || isBlackComputer;
    }

    @JsonIgnore
    @Transient
    public boolean isComputersTurn() {
        String whiteId = getProperty("whiteId");
        String blackId = getProperty("blackId");
        boolean isWhiteComputer = whiteId == null || Long.parseLong(whiteId) < 0;
        boolean isBlackComputer = blackId == null || Long.parseLong(blackId) < 0;
        boolean isWhitesTurn = searchEngine.getBoard().getTurn();
        return (isWhitesTurn && isWhiteComputer) || (!isWhitesTurn && isBlackComputer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game that = (Game) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
