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

[Date "1985.10.15"]
[White "Karpov"]
[Black "Kasparov"]
1. e4 e5 2. Nf3 Nc6 3. Bb5 {This opening is called the Ruy Lopez.} 3... a6
4. Ba4 Nf6 5. O-O Be7 6. Re1 b5 7. Bb3 d6 8. c3 O-O 9. h3 Nb8  10. d4 Nbd7
11. c4 c6 12. cxb5 axb5 13. Nc3 Bb7 14. Bg5 b4 15. Nb1 h6 16. Bh4 c5 17. dxe5
Nxe4 18. Bxe7 Qxe7 19. exd6 Qf6 20. Nbd2 Nxd6 21. Nc4 Nxc4 22. Bxc4 Nb6
23. Ne5 Rae8 24. Bxf7+ Rxf7 25. Nxf7 Rxe1+ 26. Qxe1 Kxf7 27. Qe3 Qg5 28. Qxg5
hxg5 29. b3 Ke6 30. a3 Kd6 31. axb4 cxb4 32. Ra5 Nd5 33. f3 Bc8 34. Kf2 Bf5
35. Ra7 g6 36. Ra6+ Kc5 37. Ke1 Nf4 38. g3 Nxh3 39. Kd2 Kb5 40. Rd6 Kc5 41. Ra6
Nf2 42. g4 Bd3 43. Re6

    </textarea></form>

    <center>
        <b><span id="GameWhite"></span>&nbsp;-&nbsp;<span id="GameBlack"></span>&nbsp;&nbsp;<span id="GameResult"></span></b>
        <p></p>
        <div id="GameBoard"></div>
        <p></p>
        <div id="GameButtons"></div>
    </center>

</body>
</html>
