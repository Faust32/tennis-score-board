<%
    String errorMessage = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Exception occurred</title>
    <link rel="stylesheet" href="css/body-style.css">
    <link rel="stylesheet" href="css/button-style.css">
</head>

<style>
  p {
    text-align: center;
    border-radius: 10px;
    box-shadow: 0 0 0 4px #059669;
    text-indent: 30px;
    padding: 10px 20px;
    margin: 20px auto;
    width: auto;
    max-width: 60%;
    word-wrap: break-word;
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
<h2>An exception occurred with the following message:</h2>
<p><%=errorMessage%></p>

</body>
</html>