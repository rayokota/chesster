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
        .highlight-bottom {
            -webkit-box-shadow: inset 0 0 2px 2px yellow;
            -moz-box-shadow: inset 0 0 2px 2px yellow;
            box-shadow: inset 0 0 2px 2px yellow;
        }
        .highlight-top {
            -webkit-box-shadow: inset 0 0 2px 2px blue;
            -moz-box-shadow: inset 0 0 2px 2px blue;
            box-shadow: inset 0 0 2px 2px blue;
        }
    </style>

    <div id="board" style="width: ${width}%"></div>

    <script type="text/javascript">
//        var init = function() {
        var board,
          boardEl = $('#board'),
          game = new Chess(),
          gameId = #{game.id},
          squareToHighlight;

        var showHighlights = function(last_move) {
            if (last_move) {
                if (last_move.turn === '${orientation?substring(0,1)}') {
                    removeHighlights('bottom');
                    boardEl.find('.square-' + last_move.from).addClass('highlight-bottom');
                    boardEl.find('.square-' + last_move.to).addClass('highlight-bottom');
                } else {
                    removeHighlights('top');
                    boardEl.find('.square-' + last_move.from).addClass('highlight-top');
                    boardEl.find('.square-' + last_move.to).addClass('highlight-top');
                }
            }
        };

        var removeHighlights = function(color) {
          boardEl.find('.square-55d63')
            .removeClass('highlight-' + color);
        };

        // do not pick up pieces if the game is over
        // only pick up pieces for White
        var onDragStart = function(source, piece, position, orientation) {
          if (game.in_checkmate() === true || game.in_draw() === true ||
            piece.search(/^${opponentOrientation?substring(0,1)}/) !== -1) {
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
          removeHighlights('bottom');
          boardEl.find('.square-' + source).addClass('highlight-bottom');
          boardEl.find('.square-' + target).addClass('highlight-bottom');

          if (parent.onMove) {
            parent.onMove(gameId, source, target);
          }
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
          onSnapEnd: onSnapEnd,
          orientation: '${orientation}',
          showNotation: false,
          pieceTheme: '/assets/board/img/chesspieces/alpha/{piece}.png'
        };
        board = new ChessBoard('board', cfg);
        game.load_pgn('${game.pgnMoves}');
        showHighlights(game.last_move());
        $(window).resize(board.resize);

        function move(source, target, side) {
            // see if the move is legal
            var move = game.move({
                from: source,
                to: target,
                promotion: 'q' // NOTE: always promote to a pawn for example simplicity
            });

            // illegal move
            if (move === null) return;

            // set move on board
            board.move(source + "-" + target);

            // highlight white's move
            removeHighlights(side);
            boardEl.find('.square-' + source).addClass('highlight-' + side);
            boardEl.find('.square-' + target).addClass('highlight-' + side);

            if (parent.onMove) {
                parent.onMove(gameId, source, target);
            }
        };

//        }; // end init()
//        $(document).ready(init);
    </script>
</body>
</html>
