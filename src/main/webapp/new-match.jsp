<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8"/>
    <title>Create a new match</title>
    <link rel="stylesheet" href="css/body-style.css" type="text/css">
    <link rel="stylesheet" href="css/button-style.css" type="text/css">
</head>

<style>
    form {
        text-align: center;
        font-size: 24px;
    }
    input {
        background: linear-gradient(45deg, #34D399, #059669);
        font-family: "Arial Black", Gadget, sans-serif;
        font-weight: bold;
        text-align: center;
        font-size: 35px;
        padding: 15px;
        width: 100%;
        margin: 5px;
        box-sizing: border-box;
        border-radius: 5%;
        border: 2px solid transparent;
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
<h2>Enter the players' names in the appropriate fields below:</h2>
<form action="new-match" method="POST">
    <label for="first_player_name">First player:</label><br>
    <input type="text" id="first_player_name" name="first_player_name" required><br>
    <label for="second_player_name">Second player:</label><br>
    <input type="text" id="second_player_name" name="second_player_name" required>
    <br>
    <button type="submit" class="button gradient-button">Start Match!</button>
</form>
</body>
</html>