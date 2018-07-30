package com.example.marque.interactivestory.Model;

public class Choice {
    //class member variables
    private int textId;
    private int nextPage;

    //constructor
    public Choice(int textId, int nextPage) {
        this.textId = textId;
        this.nextPage = nextPage;
    }

    //getters and setters for class variables
    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
