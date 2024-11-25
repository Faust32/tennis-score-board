<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    //TODO: add a thing that takes from Game class current score
    String firstPlayerName = request.getParameter("first_player_name");
    String secondPlayerName = request.getParameter("second_player_name");

    session.setAttribute("firstPlayerName", firstPlayerName);
    session.setAttribute("secondPlayerName", secondPlayerName);
    session.setAttribute();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Score</title>
    <link rel="stylesheet" href="body-style.css">
</head>
<style>
    .square {
        border: 3px solid black;
        border-radius: 50px;
        box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.4);
        height: 300px;
        width: 800px;
    }
    .scoreboard {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        border-radius: 47px;
        width: 100%;
        height: 100%;
    }

    .top-row {
        display: flex;
        justify-content: flex-start;
        width: 100%;
        height: 50%;
        border-bottom: 2px darkblue solid;
    }
    .bottom-row {
        display: flex;
        justify-content: flex-start;
        width: 100%;
        height: 50%;
        border-top: 2px darkblue solid;
    }
    .player-name {
        text-transform: uppercase;
        font-size: 40px;
        font-weight: bold;
        vertical-align: center;
        text-align: center;
        overflow: hidden;
        word-wrap: break-word;
        font-family: "Arial Black",Gadget,sans-serif;
        height: 100%;
        width: 55%;
        background-color: #4682B4;
        padding: 10px;
        box-sizing: border-box;
    }
    .match-score {
        text-transform: uppercase;
        font-size: 40px;
        font-weight: bold;
        vertical-align: center;
        text-align: center;
        overflow: hidden;
        font-family: "Arial Black",Gadget,sans-serif;
        height: 100%;
        width: 17%;
        background-color: #AFEEEE;
        cursor: pointer;
    }
    .set-score {
        text-transform: uppercase;
        font-size: 40px;
        font-weight: bold;
        vertical-align: center;
        text-align: center;
        overflow: hidden;
        font-family: "Arial Black",Gadget,sans-serif;
        height: 100%;
        width: 17%;
        background-color: #40E0D0;
        cursor: pointer;
    }
    .game-score {
        text-transform: uppercase;
        font-size: 40px;
        font-weight: bold;
        vertical-align: center;
        text-align: center;
        overflow: hidden;
        font-family: "Arial Black",Gadget,sans-serif;
        height: 100%;
        width: 19%;
        background-color: #00CED1;
        cursor: pointer;
    }
    .top-row .player-name {
        border-radius: 46px 0 0 0;
    }

    .top-row .game-score {
        border-radius: 0 46px 0 0;
    }

    .bottom-row .player-name {
        border-radius: 0 0 0 46px;
    }

    .bottom-row .game-score {
        border-radius: 0 0 46px 0;
    }

</style>
<body>
<div class="header">
    <h1>Tennis Score Board</h1>
</div>
    <div class="square">
        <div class="scoreboard">
            <div class="top-row">
                <div id="first-player-name" class="player-name">
                    ${firstPlayerName}
                </div>
                <div id="wrap-match1" class="match-score">
                    ${matchScore1}
                </div>
                <div id="first-player-set-score" class="set-score">
                    ${setScore1}
                </div>
                <div id="first-player-game-score" class="game-score">
                    ${gameScore1}
                </div>
            </div>
            <div class="bottom-row">
                <div id="second-player-name" class="player-name">
                    ${secondPlayerName}
                </div>
                <div id="second-player-match-score" class="match-score">
                    ${matchScore2}
                </div>
                <div id="second-player-set-score" class="set-score">
                    ${setScore2}
                </div>
                <div id="second-player-game-score" class="game-score">
                    ${gameScore2}
                </div>
            </div>
        </div>
    </div>
</body>
</html>