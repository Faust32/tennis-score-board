package controller;

import dto.PlayersDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import model.Match;
import service.CurrentMatchService;
import service.NewMatchService;
import utils.ParametersValidity;

@RequiredArgsConstructor
public class NewMatchController {
    private final NewMatchService newMatchService;
    private final CurrentMatchService currentMatchService;
    public void handle(HttpServletRequest request) {
        String firstPlayerName = request.getParameter("first_player_name");
        String secondPlayerName = request.getParameter("second_player_name");
        PlayersDTO playersDTO = new PlayersDTO(firstPlayerName, secondPlayerName);
        ParametersValidity.validateNames(playersDTO);
        newMatchService.savePlayers(playersDTO);
        Match match = newMatchService.createMatch(playersDTO);
        currentMatchService.addMatch(match);
    }
}
