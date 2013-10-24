package com.yammer.chesster.service.views;

import com.yammer.chesster.service.model.Game;
import com.yammer.dropwizard.views.View;

public class BoardView extends View {

    private Game game;
    private long playerId;
    private int width;

    public BoardView(Game game, long playerId, int width) {
        super("board.ftl");
        this.game = game;
        this.playerId = playerId;
        this.width = width;
    }

    public Game getGame() {
        return game;
    }

    public long getPlayerId() {
        return playerId;
    }

    public int getWidth() {
        return width;
    }

    public String getOrientation() {
        String whitePlayerId = game.getProperty("whiteId");
        String blackPlayerId = game.getProperty("blackId");
        try {
            if (whitePlayerId != null && Long.parseLong(whitePlayerId) == playerId) {
                return "white";
            }
            if (blackPlayerId != null && Long.parseLong(blackPlayerId) == playerId) {
                return "black";
            }
        } catch (NumberFormatException e) {}
        return "white";
    }

    public String getOpponentOrientation() {
        return "white".equals(getOrientation()) ? "black" : "white";
    }
}
