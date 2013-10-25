package com.yammer.chesster.service.views;

import com.yammer.chesster.service.model.Game;
import com.yammer.dropwizard.views.View;

public class PlayableBoardView extends View {

    private Game game;
    private long playerId;
    private int width;

    public PlayableBoardView(Game game, long playerId, int width) {
        super("playable_board.ftl");
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
