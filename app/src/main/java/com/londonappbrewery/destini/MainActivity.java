package com.londonappbrewery.destini;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    private TextView mTextView;
    private Button mTopButton;
    private Button mBottomButton;
    private int mIndex = 0;
    private AlertDialog mGameOverAlert;
    private int mEndMessage;
    private StoryNode[] storyLine = new StoryNode[]

            {
                    new StoryNode(R.string.T1_Story,
                            new Answer(R.string.T1_Ans1, R.string.T3_Story,false),
                            new Answer(R.string.T1_Ans2, R.string.T2_Story,false)),

                    new StoryNode(R.string.T2_Story,
                            new Answer(R.string.T2_Ans1, R.string.T3_Story,false),
                            new Answer(R.string.T2_Ans2,true, R.string.T4_End)),

                    new StoryNode(R.string.T3_Story,
                            new Answer(R.string.T3_Ans1,true, R.string.T6_End),
                            new Answer(R.string.T3_Ans2,true, R.string.T5_End)),

            };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:

            mTextView = findViewById(R.id.storyTextView);
            mTopButton = findViewById(R.id.buttonTop);
            mBottomButton = findViewById(R.id.buttonBottom);

        if (savedInstanceState != null)
        {

            mIndex = savedInstanceState.getInt("IndexKey");
            mEndMessage = savedInstanceState.getInt("GameOverKey");
            if (savedInstanceState.getBoolean("AlertKey")) {showEndDialog(mEndMessage);}
        }
        else
        {

            mIndex = 0;

        }

           updateStory(mIndex);

        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        

            mTopButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEndMessage = storyLine[mIndex].getTopAnswer().getEndMessageId();

                    if( storyLine[mIndex].getTopAnswer().isOver() ) {
                        showEndDialog(mEndMessage);
                    }
                    else
                        {
                            updateStoryTopButton();
                        }
                }
            });


        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
            mBottomButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mEndMessage = storyLine[mIndex].getBottomAnswer().getEndMessageId();

                    if(  storyLine[mIndex].getBottomAnswer().isOver() ) {
                        showEndDialog(mEndMessage);
                    }
                    else
                    {
                        updateStoryBottomButton();
                    }

                }
            });


    }

    public void updateStory(int index)
    {
        mTextView.setText(storyLine[index].getStoryTextIndex());
        mTopButton.setText(storyLine[index].getTopAnswer().getTextID());
        mBottomButton.setText(storyLine[index].getBottomAnswer().getTextID());
    }
    public void  updateStoryTopButton()
    {
        int nextStoryID = storyLine[mIndex].getTopAnswer().getNextStoryId();
        for (int i = 0;i < storyLine.length;i++) {
            if (storyLine[i].getStoryTextIndex() == nextStoryID)
            {
                mIndex = i;
            }
        }
        updateStory(mIndex);


    }

    public void  updateStoryBottomButton()
    {
        int nextStoryID = storyLine[mIndex].getBottomAnswer().getNextStoryId();
        for (int i = 0;i < storyLine.length;i++) {
            if (storyLine[i].getStoryTextIndex() == nextStoryID)
            {

                mIndex = i;

            }
            updateStory(mIndex);

        }

    }

    private void showEndDialog(int message)
    {
        mGameOverAlert =
                new AlertDialog.Builder(this)
                        .setTitle("Game over")
                        .setMessage(message)
                        .setCancelable(false)
                        .setPositiveButton((R.string.restart), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mIndex = 0;
                                updateStory(mIndex);
                            }
                        })
                        .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
    }
    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("IndexKey",mIndex);

        if (mGameOverAlert != null && mGameOverAlert.isShowing())
        {
            outState.putBoolean("AlertKey",true);
            outState.putInt("GameOverKey",mEndMessage);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        if (mGameOverAlert != null && mGameOverAlert.isShowing())
        {
            mGameOverAlert.dismiss();
        }
    }

}
