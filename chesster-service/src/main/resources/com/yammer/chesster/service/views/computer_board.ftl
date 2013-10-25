<#-- @ftlvariable name="" type="com.yammer.chesster.service.views.ComputerBoardView" -->
<#setting number_format="computer">
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/assets/board/css/chessboard.css" />
</head>
<body>
    <script src="/assets/board/js/jquery-1.10.1.min.js"></script>
    <script type="text/javascript">
        function onMove(gameId, source, target) {
          console.log("gameId: " + gameId);
          console.log("oldPos: " + source);
          console.log("newPos: " + target);
          $.post("/games/" + gameId + "/moves/" + source + "-" + target, function(data) {
              //window.frames["board"].location.reload();
              var lastMove = data.lastMove;
              window.frames["board"].move(data.lastMove.substring(0, 2), data.lastMove.substring(2, 4));
          }, "json");
        };
    </script>
<iframe name="board" src="/games/${game.id}/board?playerId=${playerId}" height="500" width="500"></iframe>
</body>
