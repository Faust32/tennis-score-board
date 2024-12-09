package model.score;

public enum GamePoints {
    NONE("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FOURTY("40"),
    ADVANTAGE("AD");

    private final String value;

    GamePoints(String value) {
        this.value = value;
    }

    static String getValue(int index) {
        return values()[index].value;
    }

}