package com.yammer.chesster.service.resources;

import com.alonsoruibal.chess.Config;
import com.alonsoruibal.chess.Move;
import com.alonsoruibal.chess.Pgn;
import com.alonsoruibal.chess.book.FileBook;
import com.alonsoruibal.chess.search.SearchEngine;
import com.alonsoruibal.chess.search.SearchParameters;
import com.google.common.base.Optional;
import com.yammer.chesster.service.model.Game;
import com.yammer.chesster.service.store.GameStore;
import com.yammer.chesster.service.views.BoardView;
import com.yammer.chesster.service.views.PgnView;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Game getGame(@PathParam("id") long id) {
        Optional<Game> game = store.getGame(id);
        if (!game.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return game.get();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/moves/{move}")
    public Game addMove(@PathParam("id") long id, @PathParam("move") String move) {
        Optional<Game> game = store.getGame(id);
        if (!game.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (!game.get().addMove(move)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return game.get();
    }

    @GET
    @Path("/{id}/bestmove")
    public String getBestMove(@PathParam("id") long id) {
        Optional<Game> game = store.getGame(id);
        if (!game.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return game.get().getBestMove();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/bestmove")
    public Game makeBestMove(@PathParam("id") long id) {
        return addMove(id, getBestMove(id));
    }


    @Produces(MediaType.TEXT_HTML)
    @GET
    @Path("/{id}/pgn")
    public PgnView showPgnViewer(@PathParam("id") long id) {
        Optional<Game> game = store.getGame(id);
        if (!game.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return new PgnView(game.get());
    }

    @Produces(MediaType.TEXT_HTML)
    @GET
    @Path("/{id}/board")
    public BoardView showBoard(@PathParam("id") long id,
                               @DefaultValue("0") @QueryParam("playerId") long playerId) {
        Optional<Game> game = store.getGame(id);
        if (!game.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return new BoardView(game.get(), playerId);
    }
}
