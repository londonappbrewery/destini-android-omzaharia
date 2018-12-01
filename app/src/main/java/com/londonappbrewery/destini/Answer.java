package com.londonappbrewery.destini;

public class Answer {
    private int TextID;
    private int nextStoryId;
    private boolean over;
    private int endMessageId;

    public Answer(int textID, int nextStoryId, boolean over) {
        TextID = textID;
        this.nextStoryId = nextStoryId;
        this.over = over;
    }

    public Answer(int textID, int nextStoryId, boolean over, int endMessageId) {
        TextID = textID;
        this.nextStoryId = nextStoryId;
        this.over = over;
        this.endMessageId = endMessageId;
    }

    public Answer(int textID, boolean over, int endMessageId) {
        TextID = textID;
        this.over = over;
        this.endMessageId = endMessageId;
    }

    public int getTextID() {
        return TextID;
    }

    public int getNextStoryId() {
        return nextStoryId;
    }

    public boolean isOver() {
        return over;
    }

    public int getEndMessageId() {
        return endMessageId;
    }
}
