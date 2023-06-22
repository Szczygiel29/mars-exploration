package com.codecool.marsexploration.mapelements.model;

public enum Symbol {
    MOUNTAIN("#", "\uD83D\uDDFB"),
    PIT("&", "\uD83C\uDF11"),
    MINERAL("%", "\uD83D\uDC8E"),
    WATER("*", "\uD83C\uDF0A"),
    SPACE(" ", "\uD83D\uDFE8"),
    NOSYMBOL("", "");

    private final String symbol;

    private final String emojiCode;

    Symbol(String symbol, String emojiCode) {
        this.symbol = symbol;
        this.emojiCode = emojiCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getEmojiCode() {
        return emojiCode;
    }

}
