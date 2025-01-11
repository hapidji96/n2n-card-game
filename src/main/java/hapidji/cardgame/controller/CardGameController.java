package hapidji.cardgame.controller;

import hapidji.cardgame.model.Card;
import hapidji.cardgame.model.PlayerCard;
import hapidji.cardgame.model.Winner;
import hapidji.cardgame.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CardGameController {
    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initialPage() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String resultPage(Model model) throws Exception {
        // Shuffle cards
        List<Card> shuffledCards = cardService.shuffleCards();

        // Build partition list of shuffled cards into 12 rows
        List<List<Card>> partitionedCards = cardService.partitionList(shuffledCards, 12);

        // Distribute cards to 4 players
        List<PlayerCard> playerCards = cardService.distributeCardsToPlayers(shuffledCards);

        // Process the winner
        Winner winner = cardService.calculatingWinner(playerCards);

        // Pass to modelAttributes : shuffleCards, playerCards, winner
        model.addAttribute("shuffledCards", partitionedCards);
        model.addAttribute("playerCards", playerCards);
        model.addAttribute("winner", winner);
        return "result";
    }
}
