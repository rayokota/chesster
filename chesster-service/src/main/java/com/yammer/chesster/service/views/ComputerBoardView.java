package com.yammer.chesster.service.views;

import com.yammer.chesster.service.model.Game;
import com.yammer.dropwizard.views.View;

public class ComputerBoardView extends View {

    private Game game;
    private long playerId;
    private int width;

    public ComputerBoardView(Game game, long playerId, int width) {
        super("computer_board.ftl");
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
}
