package com.example.gamestate_group5;

public class Card {
    private int numOnCard;
    private int colorOnCard;
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
