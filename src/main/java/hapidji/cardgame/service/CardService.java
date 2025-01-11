package hapidji.cardgame.service;

import hapidji.cardgame.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CardService {
    public List<Card> getCardList() {
        List<Card> cards = List.of(
                new Card("2@", 20),
                new Card("2#", 21),
                new Card("2^", 22),
                new Card("2*", 24),
                new Card("3@", 30),
                new Card("3#", 31),
                new Card("3^", 32),
                new Card("3*", 33),
                new Card("4@", 34),
                new Card("4#", 40),
                new Card("4^", 41),
                new Card("4*", 42),
                new Card("5@", 50),
                new Card("5#", 51),
                new Card("5^", 52),
                new Card("5*", 53),
                new Card("6@", 60),
                new Card("6#", 61),
                new Card("6^", 62),
                new Card("6*", 63),
                new Card("7@", 70),
                new Card("7#", 71),
                new Card("7^", 72),
                new Card("7*", 73),
                new Card("8@", 80),
                new Card("8#", 81),
                new Card("8^", 82),
                new Card("8*", 83),
                new Card("9@", 90),
                new Card("9#", 91),
                new Card("9^", 92),
                new Card("9*", 93),
                new Card("10@", 100),
                new Card("10#", 101),
                new Card("10^", 102),
                new Card("10*", 103),
                new Card("J@", 110),
                new Card("J#", 111),
                new Card("J^", 112),
                new Card("J*", 113),
                new Card("Q@", 120),
                new Card("Q#", 121),
                new Card("Q^", 122),
                new Card("Q*", 123),
                new Card("K@", 130),
                new Card("K#", 131),
                new Card("K^", 132),
                new Card("K*", 133),
                new Card("A@", 140),
                new Card("A#", 141),
                new Card("A^", 142),
                new Card("A*", 143)
        );

        return cards;
    }

    public int getCardWeightage(String cardName) {
        List<Card> cardList = this.getCardList();
        Card card = cardList.stream().filter(c -> c.getName().equalsIgnoreCase(cardName)).findFirst().get();
        return card.getWeightage();
    }

    public List<Card> shuffleCards(List<PlayerCard> playerCards) {
        return null;
    };

    public List<PlayerCard> distributeCardsToPlayers(List<Player> players, List<Card> cards) {
        return null;
    }

    public Winner checkWinnerRule1(List<PlayerCard> playerCards) {
        // Get distinct cards alphanumeric of players
        List<PlayerCardAlphanumericCount> playerCardAlphanumericCounts = null;
        for (PlayerCard playerCard : playerCards) {
            Optional<PlayerCardAlphanumericCount> playerCardAlphanumeric = playerCardAlphanumericCounts.stream()
                    .filter(p -> p.getPlayerName().equalsIgnoreCase(playerCard.getCardName())
                            && p.getCardAlphanumeric() == playerCard.getCardName().charAt(0))
                    .findFirst();
            if(playerCardAlphanumeric.isPresent()) {
                playerCardAlphanumericCounts.stream()
                        .filter(p -> p.getPlayerName().equalsIgnoreCase(playerCard.getCardName())
                                && p.getCardAlphanumeric() == playerCard.getCardName().charAt(0))
                        .findFirst()
                        .ifPresent(p -> p.setCount(p.getCount() + 1));
            } else {
                playerCardAlphanumericCounts.add(new PlayerCardAlphanumericCount(playerCard.getPlayerName(), playerCard.getCardName().charAt(0), 1));
            }
        }

        // Get player, alphanumeric with highest count
        int maxCount = playerCardAlphanumericCounts.stream()
                .mapToInt(PlayerCardAlphanumericCount::getCount)
                .max()
                .orElse(0);
        List<PlayerCardAlphanumericCount> playerAlphanumericHighestCount = playerCardAlphanumericCounts.stream()
                .filter(p -> p.getCount() == maxCount)
                .toList();

        // Winner
        if(playerAlphanumericHighestCount.size() == 1) {
            return new Winner(playerAlphanumericHighestCount.get(0).getPlayerName(), true);
        }

        return new Winner("", false);
    }

    public Winner getWinner(List<PlayerCard> playerCardList) {
        // Rule 1 : Players with highest number of cards with same alphanumeric
        Winner winner = null;
        winner = checkWinnerRule1(playerCardList);

        if(winner.isWinner()) {
            return winner;
        } else {
            // Rule 2 : If more than 1 player has the same number of winning cards, the alphanumeric part with higher value won.
            // Get distinct player names, add into score list and initialize the score as 0
            String[] players = playerCardList.stream().map(PlayerCard::getPlayerName).distinct().toArray(String[]::new);
            List<Score> scores = new ArrayList<>();
            for (String name : players) {
                scores.add(new Score(name, 0));
            }


            // Calculate players total score
            for (String name : players) {
                for (PlayerCard playerCard : playerCardList) {
                    if(playerCard.getPlayerName().equalsIgnoreCase(name)) {
                        scores.stream()
                                .filter(score -> score.getPlayerName().equalsIgnoreCase(name))
                                .findFirst()
                                .ifPresent(score -> score.setScore(score.getScore() + getCardWeightage(playerCard.getCardName())));

                    }
                }
            }
        }

        return scores;
    }
}
