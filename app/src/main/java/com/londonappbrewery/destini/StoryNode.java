package com.londonappbrewery.destini;

public class StoryNode {
    private int storyTextIndex;
    private Answer topAnswer;
    private Answer bottomAnswer;


    public StoryNode(int storyTextIndex, Answer topAnswerID, Answer bottomAnswerID) {
        this.storyTextIndex = storyTextIndex;
        this.topAnswer = topAnswerID;
        this.bottomAnswer = bottomAnswerID;
    }

    public int getStoryTextIndex() {
        return storyTextIndex;
    }

    public Answer getTopAnswer() {
        return topAnswer;
    }

    public Answer getBottomAnswer() {
        return bottomAnswer;
    }
}
