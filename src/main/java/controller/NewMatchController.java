package controller;

import dto.PlayersDTO;
import exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import model.Match;
import model.Player;
import service.CurrentMatchService;
import service.NewMatchService;
import utils.ParametersValidity;

import java.util.UUID;

public class NewMatchController {

    private final NewMatchService newMatchService = new NewMatchService();

    private final CurrentMatchService currentMatchService = CurrentMatchService.getInstance();

    public void handle(HttpServletRequest request) {
        String firstPlayerName = request.getParameter("first_player_name");
        String secondPlayerName = request.getParameter("second_player_name");
        PlayersDTO playersDTO = new PlayersDTO(firstPlayerName, secondPlayerName);
        ParametersValidity.validateNames(playersDTO);
        Player[] players = newMatchService.savePlayers(playersDTO);
        Match match = newMatchService.createMatch(players);
        currentMatchService.addMatch(match);
    }

    public UUID getCurrentMatchId() {
        try {
            return currentMatchService.getCurrentMatch().match().getId();
        } catch (Exception e) {
            throw new NotFoundException("Couldn't find current match by his id.");
        }
    }

}
