package hapidji.cardgame.model;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class CharacterWeightage {
    private char character;
    private long charWeightage;
    private String[] prefix = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private String[] postfix = {"@", "#", "^", "*"};

    public CharacterWeightage(char character, long charWeightage) {
        this.character = character;
        this.charWeightage = charWeightage;
    }

    public CharacterWeightage() {

    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public long getCharWeightage() {
        return charWeightage;
    }

    public void setCharWeightage(long charWeightage) {
        this.charWeightage = charWeightage;
    }

    public int getCharWeightage(String character, String position) {
        if(position.equalsIgnoreCase("prefix")) {
            return getCharacterIndex(prefix, character);
        } else if(position.equalsIgnoreCase("postfix")) {
            return getCharacterIndex(postfix, character);
        }

        return -1;
    }

    public int getCharacterIndex(String[] array, String character) {
        return IntStream.range(0, array.length)
                .filter(i -> array[i].equalsIgnoreCase(character))
                .findFirst()
                .orElse(-1);
    }
}
