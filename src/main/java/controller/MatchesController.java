package controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import model.Match;
import service.MatchesByNameService;
import service.PageDistributionService;

import java.util.List;

@RequiredArgsConstructor
public class MatchesController {

    private final PageDistributionService pageDistributionService;
    private final MatchesByNameService matchesByNameService;
    public List<Match> handle(HttpServletRequest request) {
        long pageNumber = Long.parseLong(request.getParameter("page"));
        String playerName = request.getParameter("filter_by_player_name");
        List<Match> filteredMatches = matchesByNameService.filterByName(playerName);
        return pageDistributionService.getMatchesOnPage(pageNumber, filteredMatches);
    }
}
