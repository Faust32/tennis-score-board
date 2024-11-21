package utils;

import dto.PlayersDTO;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@UtilityClass
public class ParametersValidity {
    private static final Pattern NAME_PATTERN = Pattern.compile("^(?!.* {2})([a-zA-Z-]+(?: [a-zA-Z-]+){0,2})$\n");
    private static final Set<String> BAD_WORDS = new HashSet<>();

    static {
        try {
            loadDictionary();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load bad words list", e);
        }
    }

    private void loadDictionary() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("profanity/dictionary.en"))) {
            String line;
            while ((line = br.readLine()) != null) {
                BAD_WORDS.add(line.toLowerCase());
            }
        }
    }
    public void validateNames(PlayersDTO playersDTO) {
        validateName(playersDTO.firstPlayerName());
        validateName(playersDTO.secondPlayerName());
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty() || !NAME_PATTERN.matcher(name).matches()) {
            throw new InvalidParameterException("Invalid name: " + name);
        }
        if (containsBadWords(name)) {
            throw new InvalidParameterException("Name contains prohibited words: " + name);
        }
    }

    private boolean containsBadWords(String name) {
        String[] words = name.toLowerCase().split("\\s+"); // Разделяем имя на слова.
        for (String word : words) {
            if (BAD_WORDS.contains(word) || BAD_WORDS.contains(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
