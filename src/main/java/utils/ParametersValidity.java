package utils;

import dto.PlayersDTO;
import exceptions.InvalidParametersException;
import exceptions.UtilityFilesLoadingException;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

@UtilityClass
public class ParametersValidity {
    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 30;
    private static final Pattern NAME_PATTERN = Pattern.compile("^(?!.* {2})([a-zA-Z-']+(?: [a-zA-Z-']+){0,2})$");
    private static final Set<String> BAD_WORDS = new HashSet<>();

    static {
        try {
            loadDictionary();
        } catch (IOException e) {
            throw new UtilityFilesLoadingException("An error occurred while trying to load profanity dictionary. Please contact the developer.");
        }
    }

    private void loadDictionary() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(ParametersValidity.class.getClassLoader().getResourceAsStream("profanity/dictionary.en"))))) {
            String line;
            while ((line = br.readLine()) != null) {
                BAD_WORDS.add(line.toLowerCase());
            }
        }
    }

    public void validateNames(PlayersDTO playersDTO) {
        String firstPlayerName = playersDTO.firstPlayerName();
        String secondPlayerName = playersDTO.secondPlayerName();
        if (firstPlayerName.equals(secondPlayerName)) {
            throw new InvalidParametersException("First player name cannot be the same as second player name");
        }
        validateName(firstPlayerName);
        validateName(secondPlayerName);
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty() || !NAME_PATTERN.matcher(name).matches()
                || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new InvalidParametersException("Invalid name: " + name);
        }
        if (containsBadWords(name)) {
            throw new InvalidParametersException("Name contains prohibited words: " + name);
        }
    }

    private boolean containsBadWords(String name) {
        String lowerCaseName = name.toLowerCase();
        for (String badWord : BAD_WORDS) {
            if (lowerCaseName.equals(badWord) || lowerCaseName.contains(badWord)) {
                return true;
            }
        }
        return false;
    }

    public Long validatePageNumber(String parameter) {
        if (parameter == null || parameter.isEmpty()) {
            return 1L;
        }
        long pageNumber = Long.parseLong(parameter);
        if (pageNumber <= 0) {
            return 1L;
        }
        return pageNumber;
    }

}
