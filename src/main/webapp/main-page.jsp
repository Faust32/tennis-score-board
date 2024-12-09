<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8"/>
    <title>Tennis Scoreboard</title>
    <link href="css/body-style.css" rel="stylesheet" type="text/css">
    <link href="css/button-style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="header">
    <h1>Tennis Score Board</h1>
</div>

<img src="images/main-menu.jpg" alt="" width="539" height="360" class="center">

<div class="button-container">
    <br>
    <a href="${pageContext.request.contextPath}/new-match" class="button gradient-button">New Match</a>
    <a href="${pageContext.request.contextPath}/matches?page=1&filter_by_player_name=" class="button gradient-button">All Matches</a>
</div>

</body>
</html>