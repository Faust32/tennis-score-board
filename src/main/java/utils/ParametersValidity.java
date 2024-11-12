package utils;

import dto.PlayersDTO;
import lombok.experimental.UtilityClass;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

@UtilityClass
public class ParametersValidity {
    private static final Pattern NAME_PATTERN = Pattern.compile("^(?!.* {2})[a-zA-Zа-яА-ЯёЁ-]+(?: [a-zA-Zа-яА-ЯёЁ-]+){0,2}$\n");

    public void validateNames(PlayersDTO playersDTO) {
        validateName(playersDTO.firstPlayerName());
        validateName(playersDTO.secondPlayerName());
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty() || !NAME_PATTERN.matcher(name).matches()) {
            throw new InvalidParameterException("Invalid name: " + name);
        }
    }
}
