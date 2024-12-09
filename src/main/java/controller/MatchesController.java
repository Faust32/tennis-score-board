package controller;

import dto.MatchPageDTO;
import dto.MatchesListDTO;
import jakarta.servlet.http.HttpServletRequest;
import model.Match;
import service.MatchesByNameService;
import service.PageDistributionService;
import utils.ParametersValidity;

import java.util.List;

public class MatchesController {
    private final PageDistributionService pageDistributionService = new PageDistributionService();
    private final MatchesByNameService matchesByNameService = new MatchesByNameService();
    public void handle(HttpServletRequest request) {
        Long pageNumber = ParametersValidity.validatePageNumber(request.getParameter("page"));
        String playerName = request.getParameter("filter_by_player_name");
        List<Match> filteredMatches = matchesByNameService.filterByName(playerName);
        MatchesListDTO matchesListDTO = new MatchesListDTO(new MatchPageDTO(pageNumber, 0L, playerName), filteredMatches);
        setAttributes(request, matchesListDTO);
    }

    private void setAttributes(HttpServletRequest request, MatchesListDTO matchesListDTO) {
        MatchesListDTO displayableMatches = pageDistributionService.getMatchesOnPage(matchesListDTO);
        request.setAttribute("matchesListOnPage", displayableMatches);
    }
}
