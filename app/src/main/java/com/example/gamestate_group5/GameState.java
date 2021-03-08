package com.example.gamestate_group5;

import android.util.Log;

import java.util.ArrayList;

public class GameState {
    private ArrayList<Card> cardsInHandP1;
    private ArrayList<Card> cardsInHandP2;
    private Card[] deckOfCards;
    private Card cardInPlay;

    private ArrayList<Card> drawPile;
    private int playerTurn;
    private int stageOfGame;
    private String typeOfCard;
    private boolean hasCalledUno;

    public GameState() {

        deckOfCards = new Card[108];
        // Set each card in deck
        // Shuffle

        cardInPlay = deckOfCards[0];

        cardsInHandP1 = new ArrayList<Card>(7);
        // Cards in hand equal [1-8]

        cardsInHandP2 = new ArrayList<Card>(7);
        // Cards in cpHand = [9-16]

        drawPile = new ArrayList<Card>(93);


        playerTurn = 1;

        stageOfGame = 2; // 1 is Setup, 2 is in-play, 3 is GameOver


    }

    public void playTest() {
        cardsInHandP1.clear();
        cardsInHandP2.clear();
        Card green1 = new Card(1, 2, "normal");
        Card greenSkip = new Card(0, 2, "skip");
        Card redDraw2 = new Card(0, 1, "draw2");
        Card blueReverse = new Card(0, 3, "reverse");
        Card wild = new Card(0, 0, "wild");
        Card wildDraw4 = new Card(0, 0, "wildDraw4");
        Card yellow1 = new Card(1, 4, "normal");

        Card blue1 = new Card(1, 3, "normal");
        cardInPlay = greenSkip;

        cardsInHandP1.add(green1);

        cardsInHandP2.add(blueReverse);

        playCard(cardsInHandP1.get(0));

    }
    public boolean playCard(Card cardPlayed) {
        if(cardPlayed.getColor() == cardInPlay.getColor() || cardPlayed.getNum() == cardInPlay.getNum()) {
            cardInPlay = cardPlayed;
            Log.d("Played", "Card has been played");
            return true;
        }
        return false;
    }
    public boolean drawCard() {
        for(int i = 0; i < cardsInHandP1.size(); i++) {
            if(cardsInHandP1.get(i).getColor() == cardInPlay.getColor() || cardsInHandP1.get(i).getNum() == cardInPlay.getNum()) {
                return false;
            }
        }
        // Add to Array cardsInHand the card that is drawn
        return true;
    }
    public boolean sayUno() {
        if(cardsInHandP1.size() == 1 || cardsInHandP2.size() == 1) {
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
