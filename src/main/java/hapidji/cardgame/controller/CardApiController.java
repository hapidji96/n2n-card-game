package hapidji.cardgame.controller;

import hapidji.cardgame.model.Card;
import hapidji.cardgame.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardApiController {
    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> getCards() {
        return cardService.getCardList();
    }
}
