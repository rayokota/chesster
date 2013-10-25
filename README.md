# Chesster

A dropwizard service for playing chess.


## REST API Commands

<table>
    <tr>
        <td>Resource</td>
        <td>Description</td>
        <td>Example Payload</td>
    </tr>
    <tr>
        <td><code>POST /games</code></td>
        <td>Creates a new game.<br>  
        Specify a player ID of -1 for a computer opponent.</td>
        <td><pre>
{ 
  "id": 123,
  "properties": {
    "white": "Yokota",
    "black": "Lang", 
    "whiteId": 1,
    "blackId": 2
  }
}</pre></td>
    </tr>
    <tr>
        <td><code>GET /games/{id}</code></td>
        <td>Retrieves an existing game.</td>
    </tr>
    <tr>
        <td><code>DELETE /games/{id}</code></td>
        <td>Deletes an existing game.</td>
    </tr>
    <tr>
        <td><code>POST /games/{id}/moves/{move}</code></td>
        <td>Make a move specified in algebraic notation.<br>
        If playing with a computer, the computer will also move.</td>
    </tr>
    <tr>
        <td><code>POST /games/{id}/undomove</code></td>
        <td>Takes back a move (or 2 moves with a computer opponent).</td>
    </tr>
    <tr>
        <td><code>GET /games/{id}/bestmove</code></td>
        <td>Retrieves a computer hint for the best move.</td>
    </tr>
</table>
<br>

## UI

<table>
    <tr>
        <td>Resource</td>
        <td>Description</td>
    </tr>
    <tr>
        <td><code>GET /games/{id}/play?playerId={playerId}</code></td>
        <td>Displays a playable game from the perspective of the given player ID.</td>
    </tr>
    <tr>
        <td><code>GET /games/{id}/pgn</code></td>
        <td>Displays a game for examination.</td>
    </tr></table>
<br>
