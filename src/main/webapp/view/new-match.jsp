<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create a new match</title>
    <link rel="stylesheet" href="body-style.css">
    <link rel="stylesheet" href="button-style.css">
</head>

<style>
    form {
        text-align: center;
        font-size: 24px;
    }
    input {
        background: linear-gradient(45deg, #34D399, #059669);
        text-transform: uppercase;
        font-family: "Arial Black",Gadget,sans-serif;
        font-weight: bold;
        text-align: center;
        font-size: 20px;
        padding: 25px 100px;
        margin: 10px 0;
        border-radius: 5%;
        border: 2px solid transparent;
    }
</style>

<body>
<div class="header">
    <h1>Tennis Score Board</h1>
</div>
<h2>Enter the players' names in the appropriate fields below:</h2>
<form>
    <label for="first_player_name">First player:</label><br>
    <input type="text" id="first_player_name" name="first_player_name" required><br>
    <label for="second_player_name">Second player:</label><br>
    <input type="text" id="second_player_name" name="second_player_name" required>
</form>

<div class="button-container">
    <br>
    <a href="match-score.jsp" class="button gradient-button">Start match!</a>
</div>

</body>
</html>