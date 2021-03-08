package com.example.gamestate_group5;

public class Card {
    private int numOnCard;
    private int colorOnCard; // 1 is Red, 2 is Green, 3 is Blue, 4 is Yellow
    private String typeOfCard;

    public Card(int num, int color, String type) {
        numOnCard = num;
        colorOnCard = color;
        typeOfCard = type;
    }
    public int getNum() {
        return numOnCard;
    }
    public int getColor() {
        return colorOnCard;
    }
    public void setColor(int newColor) {
        colorOnCard = newColor;
    }
    public String getType() {
        return typeOfCard;
    }
}
