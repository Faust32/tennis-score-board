<%@ page import="dto.MatchesListDTO" %>
<%@ page import="model.Match" %>
<%@ page import="java.util.List" %>
<%@ page import="service.PageDistributionService" %>
<%
    MatchesListDTO matchesListDTO = (MatchesListDTO) request.getAttribute( "matchesListOnPage");
    Long pageNumber = matchesListDTO.matchPageDTO().getPageNumber();
    List<Match> matches = matchesListDTO.matches();
    Long lastPageNumber = matchesListDTO.matchPageDTO().getLastPageNumber();
    String filterByName = matchesListDTO.matchPageDTO().getFilterByName();
    if (filterByName == null || filterByName.isEmpty()) {
        filterByName = "";
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Matches</title>
    <link rel="stylesheet" href="css/body-style.css">
    <link rel="stylesheet" href="css/matches-table-style.css">
    <link rel="stylesheet" href="css/button-style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200&icon_names=search">
</head>
<body>
<div class="header">
    <h1>Tennis Score Board</h1>
    <div class="navigation-buttons">
        <a href="${pageContext.request.contextPath}/" class="navigation-button">Main Menu</a>
        <a href="${pageContext.request.contextPath}/new-match" class="navigation-button">New Match</a>
    </div>
</div>

<form class="search-bar" action="${pageContext.request.contextPath}/matches" method="GET">
    <input
            class="search-bar-input"
            type="search"
            name="filter_by_player_name"
            id="search-input"
            placeholder="Search..."
    >
    <input type="hidden" name="page" value="<%= pageNumber %>">
    <button type="submit" class="search-icon material-symbols-outlined">search</button>
</form>

<br>
<table>
    <tr>
        <th>Number</th>
        <th>First Player</th>
        <th>Second Player</th>
        <th>Winner</th>
    </tr>
    <%
    for (long i = 0; i < matches.size(); i++) {
    Match match = matches.get((int) i);
    %>
    <tr>
        <td><%= 1 + i + PageDistributionService.NUMBER_OF_MATCHES_IN_PAGE * (pageNumber - 1) %></td>
        <td><%= match.getPlayer1().getName() %></td>
        <td><%= match.getPlayer2().getName() %></td>
        <td><%= match.getWinner() == null ? "Match was not finished" : match.getWinner().getName() %></td>
    </tr>
    <%
    }
    %>
</table>
<div class="center-pagination">
    <div class="pagination">
        <a href="${pageContext.request.contextPath}/matches?page=1&filter_by_player_name=<%= filterByName %>">&laquo;</a>
        <% for (int i = 1; i <= lastPageNumber; i++) { %>
        <a href="${pageContext.request.contextPath}/matches?page=<%= i %>&filter_by_player_name=<%= filterByName %>"
           class="<%= i == pageNumber ? "active" : "" %>">
        <%= i %>
        </a>
        <% } %>
        <a href="${pageContext.request.contextPath}/matches?page=<%= lastPageNumber %>&filter_by_player_name=<%= filterByName %>">&raquo;</a>
    </div>
</div>
</body>
</html>
