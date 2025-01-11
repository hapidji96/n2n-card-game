package hapidji.cardgame.service;

import ch.qos.logback.core.util.StringUtil;
import hapidji.cardgame.model.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CardService {

    public List<Card> shuffleCards(List<PlayerCard> playerCards) {
        return null;
    };

    public List<PlayerCard> distributeCardsToPlayers(List<Player> players, List<Card> cards) {
        return null;
    }

    public Winner checkWinnerRule1(List<PlayerCard> playerCards) {

        return new Winner("", false);
    }

    public Winner checkWinnerRule2(List<PlayerCard> playerCards) {
        return null;
    }

    public Winner getWinner(List<PlayerCard> playerCards) {
        //region Rule 1 : Players with highest number of cards with same alphanumeric
        Winner winner = null;
        // Get distinct cards prefix of players
        List<PlayerCardCharacterCount> playerCardPrefixCounts = null;
        for (PlayerCard playerCard : playerCards) {
            Optional<PlayerCardCharacterCount> playerCardPrefix = playerCardPrefixCounts.stream()
                    .filter(p -> p.getPlayerName().equalsIgnoreCase(playerCard.getCardName())
                            && p.getCharacter().equalsIgnoreCase(String.valueOf(playerCard.getCardName().charAt(0))))
                    .findFirst();
            if(playerCardPrefix.isPresent()) {
                playerCardPrefixCounts.stream()
                        .filter(p -> p.getPlayerName().equalsIgnoreCase(playerCard.getCardName())
                                && p.getCharacter().equalsIgnoreCase(String.valueOf(playerCard.getCardName().charAt(0))))
                        .findFirst()
                        .ifPresent(p -> p.setCount(p.getCount() + 1));
            } else {
                playerCardPrefixCounts.add(new PlayerCardCharacterCount(playerCard.getPlayerName(), String.valueOf(playerCard.getCardName().charAt(0)), 1));
            }
        }

        // Get player, prefix with highest count
        int maxCount = playerCardPrefixCounts.stream()
                .mapToInt(PlayerCardCharacterCount::getCount)
                .max()
                .orElse(0);
        List<PlayerCardCharacterCount> playerPrefixHighestCount = playerCardPrefixCounts.stream()
                .filter(p -> p.getCount() == maxCount)
                .toList();

        if(playerPrefixHighestCount.size() == 1) {
            // Winner found
            return new Winner(playerPrefixHighestCount.get(0).getPlayerName(), true);
        }
        //endregion

        // Rule 2 : If more than 1 player has the same number of winning cards, the alphanumeric part with higher value won.
        if(playerPrefixHighestCount.size() > 1) {
            List<PlayerCardCharacterWeightage> playerCardCharacterWeightages = new ArrayList<>();
            for (PlayerCardCharacterCount item : playerCardPrefixCounts) {
                playerCardCharacterWeightages.add(new PlayerCardCharacterWeightage(item.getPlayerName(), item.getCharacter(), new CharacterWeightage().getCharWeightage(item.getCharacter(), "prefix")));
            }
        }


        // Rule 3 : If tie, the symbol part with high value won.
        // Get distinct player names, add into score list and initialize the score as 0
//        String[] players = playerCards.stream().map(PlayerCard::getPlayerName).distinct().toArray(String[]::new);
//        List<Score> scores = new ArrayList<>();
//        for (String name : players) {
//            scores.add(new Score(name, 0));
//        }
//
//        // Calculate players total score
//        for (String name : players) {
//            for (PlayerCard playerCard : playerCards) {
//                if(playerCard.getPlayerName().equalsIgnoreCase(name)) {
//                    scores.stream()
//                            .filter(score -> score.getPlayerName().equalsIgnoreCase(name))
//                            .findFirst()
//                            .ifPresent(score -> score.setScore(score.getScore() + getCardWeightage(playerCard.getCardName())));
//
//                }
//            }
//        }

        return winner;
    }
}
