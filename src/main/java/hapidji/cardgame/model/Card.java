package hapidji.cardgame.model;

import java.util.Collections;
import java.util.List;

public class Card {
    private String name;

    public Card() {
    }

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
