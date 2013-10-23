<#-- @ftlvariable name="" type="com.yammer.chesster.service.views.BoardView" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="/assets/mini.css" type="text/css" rel="stylesheet" />
    <link rel="shortcut icon" href="/assets/pawn.ico" />
    <script src="/assets/pgn4web.js" type="text/javascript"></script>
    <script type="text/javascript">
        "use strict";

        SetImagePath("/assets/images");
        SetImageType("png");
        SetHighlightOption(true); // true or false
        SetCommentsIntoMoveText(false);
        SetCommentsOnSeparateLines(false);
        SetAutoplayDelay(1000); // milliseconds
        SetAutostartAutoplay(false);
        SetAutoplayNextGame(false); // if set, move to the next game at the end of the current game during autoplay
        SetInitialGame(1); // number of game to be shown at load, from 1 (default); values (keep the quotes) of "first", "last", "random" are accepted; other string values assumed as PGN search string
        SetInitialVariation(0); // number for the variation to be shown at load, 0 (default) for main variation
        SetInitialHalfmove("end",false); // halfmove number to be shown at load, 0 (default) for start position; values (keep the quotes) of "start", "end", "random", "comment" (go to first comment or variation), "variation" (go to the first variation) are also accepted. Second parameter if true applies the setting to every selected game instead of startup only (default)
        SetShortcutKeysEnabled(false);
    </script>
</head>
<body>
    <form style="display: none;"><textarea style="display: none;" id="pgnText">

        <#list game.propertyEntrySet as prop>
        [${prop.key} "${prop.value}"]
        </#list>
        ${game.pgn}

    </textarea></form>

    <center>
        <b><span id="GameWhite"></span>&nbsp;-&nbsp;<span id="GameBlack"></span></b>
        <p></p>
        <div id="GameBoard"></div>
        <p></p>
        <div id="GameButtons"></div>
    </center>

</body>
</html>
