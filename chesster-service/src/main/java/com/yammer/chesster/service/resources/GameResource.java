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
import com.yammer.dropwizard.hibernate.UnitOfWork;
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
    @UnitOfWork
    public Game createGame(Game newGame) {
        if (newGame.getId() > 0) {
            Optional<Game> game = store.getGame(newGame.getId());
            if (game.isPresent()) {
                throw new WebApplicationException(Response.Status.CONFLICT);
            }
        }
        return store.createGame(newGame);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @UnitOfWork
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
    @UnitOfWork
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

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void deleteGame(@PathParam("id") long id) {
        Optional<Game> game = store.getGame(id);
        if (!game.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        store.deleteGame(game.get());
    }

    @GET
    @Path("/{id}/bestmove")
    @UnitOfWork
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
    @UnitOfWork
    public Game makeBestMove(@PathParam("id") long id) {
        return addMove(id, getBestMove(id));
    }


    @Produces(MediaType.TEXT_HTML)
    @GET
    @Path("/{id}/pgn")
    @UnitOfWork
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
    @UnitOfWork
    public BoardView showBoard(@PathParam("id") long id,
                               @DefaultValue("0") @QueryParam("playerId") long playerId) {
        Optional<Game> game = store.getGame(id);
        if (!game.isPresent()) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return new BoardView(game.get(), playerId);
    }
}
