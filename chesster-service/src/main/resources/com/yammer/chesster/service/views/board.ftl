<#-- @ftlvariable name="" type="com.yammer.chesster.service.views.BoardView" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/assets/board/css/chessboard.css" />
</head>
<body>
    <script src="/assets/board/js/json3.min.js"></script>
    <script src="/assets/board/js/jquery-1.10.1.min.js"></script>
    <script src="/assets/board/js/prettify.js"></script>
    <script src="/assets/board/js/chessboard.js"></script>
    <script src="/assets/board/js/chess.js"></script>

    <style type="text/css">
        .highlight-white {
            -webkit-box-shadow: inset 0 0 3px 3px yellow;
            -moz-box-shadow: inset 0 0 3px 3px yellow;
            box-shadow: inset 0 0 3px 3px yellow;
        }
        .highlight-black {
            -webkit-box-shadow: inset 0 0 3px 3px blue;
            -moz-box-shadow: inset 0 0 3px 3px blue;
            box-shadow: inset 0 0 3px 3px blue;
        }
    </style>

    <div id="board" style="width: 60%"></div>

    <script type="text/javascript">
        var init = function() {
        var board,
          boardEl = $('#board'),
          game = new Chess(),
          squareToHighlight;

        var removeHighlights = function(color) {
          boardEl.find('.square-55d63')
            .removeClass('highlight-' + color);
        };

        // do not pick up pieces if the game is over
        // only pick up pieces for White
        var onDragStart = function(source, piece, position, orientation) {
          if (game.in_checkmate() === true || game.in_draw() === true ||
            piece.search(/^b/) !== -1) {
            return false;
          }
        };

        var onDrop = function(source, target) {
          // see if the move is legal
          var move = game.move({
            from: source,
            to: target,
            promotion: 'q' // NOTE: always promote to a pawn for example simplicity
          });

          // illegal move
          if (move === null) return 'snapback';

          // highlight white's move
          removeHighlights('white');
          boardEl.find('.square-' + source).addClass('highlight-white');
          boardEl.find('.square-' + target).addClass('highlight-white');
        };

        //var onMoveEnd = function() {
        //  boardEl.find('.square-' + squareToHighlight)
        //    .addClass('highlight-black');
        //};

        // update the board position after the piece snap
        // for castling, en passant, pawn promotion
        var onSnapEnd = function() {
          board.position(game.fen());
        };

        var cfg = {
          draggable: true,
          position: '${game.fen}',
          onDragStart: onDragStart,
          onDrop: onDrop,
          //onMoveEnd: onMoveEnd,
          onSnapEnd: onSnapEnd,
          pieceTheme: '/assets/board/img/chesspieces/alpha/{piece}.png'
        };
        board = new ChessBoard('board', cfg);
        game.load_pgn('${game.pgnMoves}');
        $(window).resize(board.resize);

        }; // end init()
        $(document).ready(init);
    </script>
</body>
</html>
