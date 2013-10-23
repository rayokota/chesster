package com.yammer.chesster.service.views;

import com.yammer.chesster.service.model.Game;
import com.yammer.dropwizard.views.View;

public class PgnView extends View {

    private Game game;

    public PgnView(Game game) {
        super("pgn.ftl");
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
