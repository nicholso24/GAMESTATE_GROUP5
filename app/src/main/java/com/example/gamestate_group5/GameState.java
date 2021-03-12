package com.example.gamestate_group5;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameState {
    private ArrayList<Card> cardsInHandP1;
    private ArrayList<Card> cardsInHandP2;
    private Card[] deckOfCards; //for the start of the game
    private Card cardInPlay;

    private ArrayList<Card> drawPile;
    private int playerTurn;
    private int stageOfGame;
    private String typeOfCard;
    private boolean hasCalledUno;

    public GameState() {

        deckOfCards = new Card[108];
        // Set each card in deck
        // Loop for each normal color card from 1-9
        for(int i = 0; i < 72; i++) {
            for(int j = 1; j <= 4; j++) {
                for(int k = 1; k <= 9; k++) {
                    Card temp = new Card(k, j, "normal");
                    deckOfCards[i] = temp;
                }

            }


        }

        // Sets zero cards
        for(int i = 72; i < 76; i++) {
            Card temp = new Card(0, i-72, "normal");
            deckOfCards[i] = temp;
        }

        // Sets the Skip Cards
        for(int i = 76; i < 84; i++) {
            for(int j = 1; j <= 4; j++) {
                Card temp = new Card(-1, j, "skip");
                deckOfCards[i] = temp;
            }
        }
        // Sets the reverse Cards
        for(int i = 84; i < 92; i ++) {
            for(int j = 1; j <= 4; j++) {
                Card temp = new Card(-2, j, "reverse");
                deckOfCards[i] = temp;
            }
        }
        // Sets the draw 2 cards
        for(int i = 92; i < 100; i ++) {
            for(int j = 1; j <= 4; j++) {
                Card temp = new Card(-3, j, "draw2");
                deckOfCards[i] = temp;
            }
        }
        // Sets the wild cards
        for(int i = 100; i < 104; i++) {
            Card temp = new Card(-4, -1, "wild");
            deckOfCards[i] = temp;
        }
        // Sets the wildDraw4 cards
        for(int i = 104; i < 108; i++) {
            Card temp = new Card(-5, -1, "wildDraw4");
            deckOfCards[i] = temp;
        }


        


        // Shuffle
        Random rand = new Random();
        for(int i = 0; i < deckOfCards.length; i++) {
            int randomIdx = rand.nextInt(deckOfCards.length);
            Card temp = deckOfCards[randomIdx];
            deckOfCards[randomIdx] = deckOfCards[i];
            deckOfCards[i] = temp;
        }




        cardInPlay = deckOfCards[0];

        cardsInHandP1 = new ArrayList<Card>(7);
        for(int i = 0; i < cardsInHandP1.size(); i++) {
            cardsInHandP1.set(i, deckOfCards[i+1]);
        }
        // Cards in hand equal [1-8]

        cardsInHandP2 = new ArrayList<Card>(7);
        for(int i = 0; i < cardsInHandP2.size(); i++) {
            cardsInHandP2.set(i, deckOfCards[i+9]);
        }
        // Cards in cpHand = [9-16]

        drawPile = new ArrayList<Card>(93);
        for(int i = 0; i < drawPile.size(); i++) {
            drawPile.set(i, deckOfCards[i+17]);
        }



        playerTurn = 1;

        stageOfGame = 2; // 1 is Setup, 2 is in-play, 3 is GameOver


    }

    public GameState(GameState g) {
        playerTurn = g.playerTurn;
        cardInPlay = g.cardInPlay;
        ArrayList<Card> cardsInHandP1List = new ArrayList<Card>();
        for (int i = 0; i < cardsInHandP1.size(); i++)
        {
            cardsInHandP1List.add(cardsInHandP1.get(i));
        }

        ArrayList<Card> cardsInHandP2List = new ArrayList<Card>();
        for (int i = 0; i < cardsInHandP2.size(); i++){
            cardsInHandP2List.add(cardsInHandP2.get(i));
        }



    }

    public void playTest() {
        cardsInHandP1.clear();
        cardsInHandP2.clear();
        Card red1 = new Card(1, 1, "normal");
        Card redSkip = new Card(-1, 1, "skip");
        Card redDraw2 = new Card(-2, 1, "draw2");
        Card redReverse = new Card(-3, 1, "reverse");
        Card wild = new Card(-4, 0, "wild");
        Card wildDraw4 = new Card(-5, 0, "wildDraw4");
        Card yellow1 = new Card(1, 4, "normal");

        Card blue1 = new Card(1, 3, "normal");
        cardInPlay = yellow1;

        cardsInHandP1.add(red1);
        cardsInHandP1.add(redDraw2);
        cardsInHandP1.add(wildDraw4);
        cardsInHandP1.add(blue1);

        cardsInHandP2.add(redSkip);
        cardsInHandP2.add(redReverse);
        cardsInHandP2.add(wild);


        playCard(cardsInHandP1,cardsInHandP1.get(0));
        useSkip(cardsInHandP2, cardsInHandP2.get(0));
        useReverse(cardsInHandP2,cardsInHandP2.get(0));
        useDraw2(cardsInHandP1,cardsInHandP1.get(0), cardsInHandP2);
        useWild(cardsInHandP2, cardsInHandP2.get(0), 1);
        useWildDraw4(cardsInHandP1, cardsInHandP1.get(0), cardsInHandP2);
        sayUno(cardsInHandP2);


    }

    // Actions taken by player
    // Using each hand as a player until player class is formed
    public boolean playCard(ArrayList<Card> currentHand,Card cardPlayed) {
        if(cardPlayed.getColor() == cardInPlay.getColor() || cardPlayed.getNum() == cardInPlay.getNum() || cardPlayed.getNum() < -3) {
            cardInPlay = cardPlayed;
            currentHand.remove(cardPlayed);
            currentHand.trimToSize();
            playerTurn++;
            Log.d("Played", "Card has been played");
            return true;
        }
        return false;
    }
    public boolean drawCard(ArrayList<Card> currentHand) {
        for(int i = 0; i < currentHand.size(); i++) {
            if(currentHand.get(i).getColor() == cardInPlay.getColor() || currentHand.get(i).getNum() == cardInPlay.getNum()) {
                return false;
            }
        }
        // Add to Array cardsInHand the card that is drawn
        currentHand.add(drawPile.get(0));
        drawPile.remove(0);
        drawPile.trimToSize();
        playerTurn++;

        return true;
    }
    public boolean sayUno(ArrayList<Card> currentHand) {
        if(cardsInHandP1.size() == 1 || cardsInHandP2.size() == 1) {
            return true;
        }

        return false;
    }
    public boolean useWild(ArrayList<Card> currentHand,Card cardPlayed, int color) {
        if(cardPlayed.getType() == "wild") {
            cardInPlay.setColor(color);
            playCard(currentHand, cardPlayed);

            return true;
        }
        return false;
    }
     public boolean useDraw2(ArrayList<Card> currentHand, Card cardPlayed, ArrayList<Card> otherHand) {
        if(cardPlayed.getType() != "draw2") {
            return false;
        }
        playCard(currentHand, cardPlayed);

        drawCard(otherHand);
        drawCard(otherHand);
        return true;
     }
     public boolean useSkip(ArrayList<Card> currentHand, Card cardPlayed) {
        if(cardPlayed.getType() != "skip") {
            return false;
        }
        playCard(currentHand, cardPlayed);
        playerTurn++;
        return true;
     }

     public boolean useReverse(ArrayList<Card> currentHand, Card cardPlayed) {
        if(cardPlayed.getType() != "reverse") {
            return false;
        }
        playCard(currentHand, cardPlayed);
        return true;

     }
     public boolean useWildDraw4(ArrayList<Card> currentHand, Card cardPlayed, ArrayList<Card> otherHand) {
        if(cardPlayed.getType() != "wildDraw4") {
            return false;
        }
        playCard(currentHand, cardPlayed);
        for(int i = 0; i < 4; i++) {
            drawCard(otherHand);
        }
        return true;
     }


    //toString method
    //@Override
    //public String toString() {
       // <string name =

       // return ;
   // }


}
