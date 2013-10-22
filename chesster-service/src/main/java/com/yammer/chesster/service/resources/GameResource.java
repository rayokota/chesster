package com.yammer.chesster.service.resources;

import com.yammer.chesster.service.views.BoardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/")
public class GameResource {
    private static final Logger LOG = LoggerFactory.getLogger(GameResource.class);

    public GameResource() {
    }

    @Produces(MediaType.TEXT_HTML)
    @GET
    @Path("/board")
    public BoardView showBoard() {
        return new BoardView();
    }
}
