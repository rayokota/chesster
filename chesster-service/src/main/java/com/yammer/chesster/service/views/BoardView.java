package com.yammer.chesster.service.views;

import com.yammer.chesster.service.model.Game;
import com.yammer.dropwizard.views.View;

public class BoardView extends View {

    private Game game;

    public BoardView(Game game) {
        super("board.ftl");
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
