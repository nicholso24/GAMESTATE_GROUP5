package com.example.gamestate_group5;

import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
@author Saylene Hernanedez, John Nicholson, Chiara Profenna, Megan Ly
@team 5
@date 03.11.2021

 */

public class GameState {
    private ArrayList<Card> cardsInHandP1;
    private ArrayList<Card> cardsInHandP2;
    private ArrayList<Card> deckOfCards; // change to arraylist
    private Card cardInPlay;
    private ArrayList <Card> discardPile; //where all the cards go after they're played
    private ArrayList<Card> drawPile;
    //private int discardPile; //where all the cards go after they're played
    //private ArrayList<Card> discardPile; //where all the cards go after they're played

    //private ArrayList<Card> drawPile;


    // add arraylist of discards

    //private ArrayList<Card> drawPile; // may not need
    private int playerTurn;
    private int stageOfGame; // maybe not needed
    private String typeOfCard; // maybe not needed
    private boolean hasCalledUno; // needs to exist for both players

    // add players 3&4

    public GameState() {

        deckOfCards = new ArrayList<Card>(108);
        // Set each card in deck
        // Loop for each normal color card from 1-9
        for(int i = 0; i < 72; i++) {
            for(int j = 1; j <= 4; j++) {
                for(int k = 1; k <= 9; k++) {
                    Card temp = new Card(k, j, "normal");
                    deckOfCards.add(temp);
                }

            }


        }

        // Sets zero cards
        for(int i = 72; i < 76; i++) {
            Card temp = new Card(0, i-72, "normal");
            deckOfCards.add(temp);
        }

        // Sets the Skip Cards
        for(int i = 76; i < 84; i++) {
            for(int j = 1; j <= 4; j++) {
                Card temp = new Card(-1, j, "skip");
                deckOfCards.add(temp);
            }
        }
        // Sets the reverse Cards
        for(int i = 84; i < 92; i ++) {
            for(int j = 1; j <= 4; j++) {
                Card temp = new Card(-2, j, "reverse");
                deckOfCards.add(temp);
            }
        }
        // Sets the draw 2 cards
        for(int i = 92; i < 100; i ++) {
            for(int j = 1; j <= 4; j++) {
                Card temp = new Card(-3, j, "draw2");
                deckOfCards.add(temp);
            }
        }
        // Sets the wild cards
        for(int i = 100; i < 104; i++) {
            Card temp = new Card(-4, -1, "wild");
            deckOfCards.add(temp);
        }
        // Sets the wildDraw4 cards
        for(int i = 104; i < 108; i++) {
            Card temp = new Card(-5, -1, "wildDraw4");
            deckOfCards.add(temp);
        }


        


        // Shuffle
        Random rand = new Random();
        for(int i = 0; i < deckOfCards.size(); i++) {
            int randomIdx = rand.nextInt(deckOfCards.size());
            Card temp = deckOfCards.get(randomIdx);
            deckOfCards.set(randomIdx, deckOfCards.get(i));
            deckOfCards.set(i, temp);
        }





        cardInPlay = deckOfCards.get(0);


        cardsInHandP1 = new ArrayList<Card>(7);
        for(int i = 0; i < cardsInHandP1.size(); i++) {
            cardsInHandP1.set(i, deckOfCards.get(i+1));
        }
        // Cards in hand equal [1-8]

        cardsInHandP2 = new ArrayList<Card>(7);
        for(int i = 0; i < cardsInHandP2.size(); i++) {
            cardsInHandP2.set(i, deckOfCards.get(i+1));
        }
        // Cards in cpHand = [9-16]

        drawPile = new ArrayList<Card>(93);
        for(int i = 0; i < drawPile.size(); i++) {
            drawPile.set(i, deckOfCards.get(i+1));
        }



        playerTurn = 1;

        stageOfGame = 2; // 1 is Setup, 2 is in-play, 3 is GameOver


    }

    //When the drawPile pile is empty it reshuffles the deck
    public void reShuffle(ArrayList<Card> drawPile){
        Random rand = new Random();
        if(drawPile.size() == 0) {
            for (int i = 0; i < discardPile.size(); i++) {
                int randomIdx = rand.nextInt(discardPile.size());
                Card temp = discardPile.get(randomIdx);
                drawPile.set(randomIdx, discardPile.get(i));
                //deckOfCards[i] = temp;
            }
        }
    }


    public GameState(GameState other) {
        this.playerTurn = other.playerTurn;
        this.cardInPlay = other.cardInPlay;
        this.cardsInHandP1 = new ArrayList<Card>();
        for (int i = 0; i < other.cardsInHandP1.size(); i++)
        {
            this.cardsInHandP1.add(new Card (other.cardsInHandP1.get(i)));
        }

        this.cardsInHandP2 = new ArrayList<Card>();
        for (int i = 0; i < other.cardsInHandP2.size(); i++){
            this.cardsInHandP2.add(new Card (other.cardsInHandP2.get(i)));
        }



    }

    public void playTest() {
        cardsInHandP1.clear();
        cardsInHandP2.clear();
        drawPile.clear();
        Card red1 = new Card(1, 1, "normal");
        Card redSkip = new Card(-1, 1, "skip");
        Card redDraw2 = new Card(-2, 1, "draw2");
        Card redReverse = new Card(-3, 1, "reverse");
        Card wild = new Card(-4, 0, "wild");
        Card wildDraw4 = new Card(-5, 0, "wildDraw4");
        Card yellow1 = new Card(1, 4, "normal");
        Card yellow2 = new Card(2, 4, "normal");
        Card yellow3 = new Card(3, 4, "normal");
        Card yellow4 = new Card(4, 4, "normal");
        Card yellow5 = new Card(5, 4, "normal");

        drawPile.add(yellow2);
        drawPile.add(yellow3);
        drawPile.add(yellow4);
        drawPile.add(yellow5);

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
            discardPile.add(cardInPlay);
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
