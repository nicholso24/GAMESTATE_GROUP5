package com.example.gamestate_group5;

import java.util.ArrayList;

public class GameState {
    private ArrayList<Card> cardsInHand;
    private ArrayList<Card> cpHand;
    private Card[] deckOfCards;
    private Card cardInPlay;
    private String[] discardPile;
    private String[] drawPile;
    private boolean isVisible;
    private int playerTurn;
    private int stageOfGame;
    private String typeOfCard;

    public GameState() {

        deckOfCards = new Card[108];
        // Set each card in deck
        // Shuffle

        cardInPlay = deckOfCards[0];

        cardsInHand = new ArrayList<Card>(7);
        // Cards in hand equal [1-8]

        cpHand = new ArrayList<Card>(7);
        // Cards in cpHand = [9-16]

        drawPile = new String [93];

        discardPile = new String[1];

        isVisible = false;

        playerTurn = 1;

        stageOfGame = 2; // 1 is Setup, 2 is in-play, 3 is GameOver


    }
    public boolean playCard(Card cardPlayed) {
        if(cardPlayed.getColor() == cardInPlay.getColor() || cardPlayed.getNum() == cardInPlay.getNum()) {
            cardInPlay = cardPlayed;
            return true;
        }
        return false;
    }
    public boolean drawCard() {
        for(int i = 0; i < cardsInHand.size(); i++) {
            if(cardsInHand.get(i).getColor() == cardInPlay.getColor() || cardsInHand.get(i).getNum() == cardInPlay.getNum()) {
                return false;
            }
        }
        // Add to Array cardsInHand the card that is drawn
        return true;
    }
    public boolean sayUno() {
        if(cardsInHand.size() == 1 || cpHand.size() == 1) {
            return true;
        }

        return false;
    }
    public boolean useWild(Card cardPlayed) {
        if(cardPlayed.getType() == "wild") {
            return true;
        }
        return false;
    }


}
