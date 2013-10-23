package com.yammer.chesster.service.resources;

import com.google.common.base.Optional;
import com.yammer.chesster.service.model.Game;
import com.yammer.chesster.service.store.GameStore;
import com.yammer.chesster.service.views.BoardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/games")
public class GameResource {
    private static final Logger LOG = LoggerFactory.getLogger(GameResource.class);

    private GameStore store;

    public GameResource(GameStore store) {
        this.store = store;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Game createGame(Game newGame) {
        return store.createGame(newGame);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/moves/{move}")
    public Game addMove(@PathParam("id") long id, @PathParam("move") String move) {
        Optional<Game> game = store.getGame(id);
        if (!game.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        game.get().addMove(move);
        return game.get();
    }

    @Produces(MediaType.TEXT_HTML)
    @GET
    @Path("/{id}/board")
    public BoardView showBoard(@PathParam("id") long id) {
        Optional<Game> game = store.getGame(id);
        if (!game.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return new BoardView(game.get());
    }
}
