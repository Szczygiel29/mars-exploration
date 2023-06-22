package com.codecool.marsexploration.mapelements.service.translator;

import com.codecool.marsexploration.mapelements.model.Map;

import static com.codecool.marsexploration.mapelements.model.Symbol.*;

import java.util.HashMap;

public class MapToEmojiTranslator {

    public Map translateMapToEmoji(Map map) {
        java.util.Map<String, String> emojiDictionary = new HashMap<>();
        emojiDictionary.put(MOUNTAIN.getSymbol(), MOUNTAIN.getEmojiCode());
        emojiDictionary.put(PIT.getSymbol(), PIT.getEmojiCode());
        emojiDictionary.put(MINERAL.getSymbol(), MINERAL.getEmojiCode());
        emojiDictionary.put(WATER.getSymbol(), WATER.getEmojiCode());
        emojiDictionary.put(SPACE.getSymbol(), SPACE.getEmojiCode());

        String[][] representation = map.getRepresentation();

        for (int i = 0; i < representation.length; i++) {
            for (int j = 0; j < representation[i].length; j++) {
                representation[i][j] = emojiDictionary.get(representation[i][j]);
            }
        }
        return new Map(representation);
    }
}
