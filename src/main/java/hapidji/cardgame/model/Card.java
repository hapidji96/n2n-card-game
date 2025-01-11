package hapidji.cardgame.model;

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

    public List<Card> getCardList() {
        List<Card> cards = List.of(
                new Card("2@"),
                new Card("2#"),
                new Card("2^"),
                new Card("2*"),
                new Card("3@"),
                new Card("3#"),
                new Card("3^"),
                new Card("3*"),
                new Card("4@"),
                new Card("4#"),
                new Card("4^"),
                new Card("4*"),
                new Card("5@"),
                new Card("5#"),
                new Card("5^"),
                new Card("5*"),
                new Card("6@"),
                new Card("6#"),
                new Card("6^"),
                new Card("6*"),
                new Card("7@"),
                new Card("7#"),
                new Card("7^"),
                new Card("7*"),
                new Card("8@"),
                new Card("8#"),
                new Card("8^"),
                new Card("8*"),
                new Card("9@"),
                new Card("9#"),
                new Card("9^"),
                new Card("9*"),
                new Card("10@"),
                new Card("10#"),
                new Card("10^"),
                new Card("10*"),
                new Card("J@"),
                new Card("J#"),
                new Card("J^"),
                new Card("J*"),
                new Card("Q@"),
                new Card("Q#"),
                new Card("Q^"),
                new Card("Q*"),
                new Card("K@"),
                new Card("K#"),
                new Card("K^"),
                new Card("K*"),
                new Card("A@"),
                new Card("A#"),
                new Card("A^"),
                new Card("A*")
        );

        return cards;
    }
}
