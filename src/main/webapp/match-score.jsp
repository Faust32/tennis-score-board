<%@ page import="dto.MatchScoreDTO" %>
<%@ page import="dto.PlayersScoreDTO" %>
<%@ page import="model.score.Score" %>
<%@ page import="model.Player" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    MatchScoreDTO matchScore = (MatchScoreDTO) request.getAttribute("matchScore");
    PlayersScoreDTO playersScoreDTO = matchScore.playersScoreDTO();
    Score score = playersScoreDTO.getScore();
    Player winner = matchScore.match().getWinner();
    boolean isFirstPlayerWon = false;
    boolean isSecondPlayerWon = false;
    if (winner != null) {
        isFirstPlayerWon = winner.getName().equals(matchScore.match().getPlayer1().getName());
        isSecondPlayerWon = winner.getName().equals(matchScore.match().getPlayer2().getName());
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Score</title>
    <link rel="stylesheet" href="css/body-style.css">
    <link rel="stylesheet" href="css/button-style.css">
    <link rel="stylesheet" href="css/score-board-style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0&icon_names=trophy" />
</head>
<style>
    button[disabled] {
        opacity: 0.6;
        cursor: default;
    }
</style>
<body>
<div class="header">
    <h1>Tennis Score Board</h1>
    <div class="navigation-buttons">
        <a href="${pageContext.request.contextPath}/" class="navigation-button">Main Menu</a>
        <a href="${pageContext.request.contextPath}/matches?page=1&filter_by_player_name=" class="navigation-button">All Matches</a>
    </div>
</div>
<h2><%= playersScoreDTO.getFirstPlayerName()%> VS <%=playersScoreDTO.getSecondPlayerName()%></h2>
<div class="square">
    <div class="scoreboard">
        <div class="top-row">

            <div id="first-player-name" class="player-name">
                <%= playersScoreDTO.getFirstPlayerName()%>
                <% if (isFirstPlayerWon) {%>
                    <span class="material-symbols-outlined" style="user-select: none;">trophy</span>
                <% }%>
            </div>

            <form method="POST" action="${pageContext.request.contextPath}/match-score?uuid=<%= matchScore.match().getId() %>">
                <input type="hidden" name="round_winner" value="<%=matchScore.match().getPlayer1().getId()%>">
                <button id="wrap-match1" class="match-score" type="submit" <%= isFirstPlayerWon || isSecondPlayerWon ? "disabled" : "" %>>
                    <%= score.getPlayerMatchPoints(1)%>
                </button>
            </form>

            <form method="POST" action="${pageContext.request.contextPath}/match-score?uuid=<%= matchScore.match().getId() %>">
                <input type="hidden" name="round_winner" value="<%=matchScore.match().getPlayer1().getId()%>">
                <button id="first-player-set-score" class="set-score" type="submit" <%= isFirstPlayerWon || isSecondPlayerWon ? "disabled" : "" %>>
                    <%= score.getPlayerSetPoints(1)%>
                </button>
            </form>

            <form method="POST" action="${pageContext.request.contextPath}/match-score?uuid=<%= matchScore.match().getId() %>">
                <input type="hidden" name="round_winner" value="<%=matchScore.match().getPlayer1().getId()%>">
                <button id="first-player-game-score" class="game-score" type="submit" <%= isFirstPlayerWon || isSecondPlayerWon ? "disabled" : "" %>>
                    <%=score.getPlayerGamePoints(1)%>
                </button>
            </form>

        </div>
        <div class="bottom-row">

            <div id="second-player-name" class="player-name">
                <%= playersScoreDTO.getSecondPlayerName()%>
                <% if (isSecondPlayerWon) {%>
                <span class="material-symbols-outlined" style="user-select: none;">trophy</span>
                <% }%>
            </div>

            <form method="POST" action="${pageContext.request.contextPath}/match-score?uuid=<%= matchScore.match().getId() %>">
                <input type="hidden" name="round_winner" value="<%=matchScore.match().getPlayer2().getId()%>">
                <button id="second-player-match-score" class="match-score" type="submit" <%= isFirstPlayerWon || isSecondPlayerWon ? "disabled" : "" %>>
                    <%= score.getPlayerMatchPoints(2)%>
                </button>
            </form>

            <form method="POST" action="${pageContext.request.contextPath}/match-score?uuid=<%= matchScore.match().getId() %>">
                <input type="hidden" name="round_winner" value="<%=matchScore.match().getPlayer2().getId()%>">
                <button id="second-player-set-score" class="set-score" type="submit" <%= isFirstPlayerWon || isSecondPlayerWon ? "disabled" : "" %>>
                    <%= score.getPlayerSetPoints(2)%>
                </button>
            </form>

            <form method="POST" action="${pageContext.request.contextPath}/match-score?uuid=<%= matchScore.match().getId() %>">
                <input type="hidden" name="round_winner" value="<%=matchScore.match().getPlayer2().getId()%>">
                <button id="second-player-game-score" class="game-score" type="submit" <%= isFirstPlayerWon || isSecondPlayerWon     ? "disabled" : "" %>>
                    <%= score.getPlayerGamePoints(2)%>
                </button>
            </form>

        </div>
    </div>
</div>

</body>
</html>