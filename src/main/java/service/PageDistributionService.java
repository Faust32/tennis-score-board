package service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import model.Match;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PageDistributionService {
    @Getter
    private static final int NUMBER_OF_MATCHES_IN_PAGE = 8;
    public List<Match> getMatchesOnPage(Long pageNumber, List<Match> matches) {
        int numberOfMatches = matches.size();
        long lastPage = numberOfMatches / PageDistributionService.getNUMBER_OF_MATCHES_IN_PAGE();
        if (pageNumber <= 0) {
            pageNumber = 1L;
        }
        if (pageNumber >= lastPage) {
            pageNumber = lastPage;
        }
        List<Match> displayableMatches = new ArrayList<>();
        int startIterationValue = NUMBER_OF_MATCHES_IN_PAGE * pageNumber.intValue();
        for (int i = startIterationValue; i < numberOfMatches; i++) {
            displayableMatches.add(matches.get(i));
        }
        return displayableMatches;
    }
}
