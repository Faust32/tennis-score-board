package service;

import dto.MatchPageDTO;
import dto.MatchesListDTO;
import model.Match;

import java.util.ArrayList;
import java.util.List;

public class PageDistributionService {
    public static final int NUMBER_OF_MATCHES_IN_PAGE = 8;
    public MatchesListDTO getMatchesOnPage(MatchesListDTO matchesList) {
        List<Match> matches = matchesList.matches();
        Long pageNumber = matchesList.matchPageDTO().getPageNumber();
        int numberOfMatches = matches.size();
        long lastPage = Math.max(1, (long) Math.ceil((double) numberOfMatches / NUMBER_OF_MATCHES_IN_PAGE));
        pageNumber = Math.min(pageNumber, lastPage);
        List<Match> displayableMatches = new ArrayList<>();
        int startIterationValue = NUMBER_OF_MATCHES_IN_PAGE * (pageNumber.intValue() - 1);
        for (int i = startIterationValue; i < startIterationValue + NUMBER_OF_MATCHES_IN_PAGE; i++) {
            if (i >= numberOfMatches) {
                break;
            }
            displayableMatches.add(matches.get(i));
        }
        return new MatchesListDTO(
                new MatchPageDTO(pageNumber, lastPage, matchesList.matchPageDTO().getFilterByName()),
                displayableMatches
        );
    }
}
