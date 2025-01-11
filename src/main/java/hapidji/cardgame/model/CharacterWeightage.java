package hapidji.cardgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class CharacterWeightage {
    private String character;
    private int charWeightage;

    public CharacterWeightage(String character, int charWeightage) {
        this.character = character;
        this.charWeightage = charWeightage;
    }

    public CharacterWeightage() {

    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getCharWeightage() {
        return charWeightage;
    }

    public void setCharWeightage(int charWeightage) {
        this.charWeightage = charWeightage;
    }
}
