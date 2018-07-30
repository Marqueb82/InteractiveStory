package com.example.marque.interactivestory.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marque.interactivestory.Model.Page;
import com.example.marque.interactivestory.Model.Story;
import com.example.marque.interactivestory.R;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private String name;
    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private Stack<Integer> pageStack = new Stack<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView = (ImageView) findViewById(R.id.storyImageView); //image displayed
        storyTextView = (TextView) findViewById(R.id.storyTextView); //text displayed
        choice1Button = (Button)findViewById(R.id.choice1button) ; //button displayed
        choice2Button = (Button)findViewById(R.id.choice2button); //button displayed

        Intent intent = getIntent();
        name = intent.getStringExtra(getString(R.string.key_name)); //using variable in intent
        if(name==null || name.isEmpty()){
            name = "Friend";
        }
        Log.d(TAG,name);

        story = new Story(); //creates new story object
        loadPage(0); //loads beginning of pages
    }

    private void loadPage(int pageNumber) {
        pageStack.push(pageNumber); //pushes page number onto stack--builds up pages as we navigate

        final Page page = story.getPage(pageNumber); //allows to get page

        Drawable image = ContextCompat.getDrawable(this,page.getImageId()); //sets image for page
        storyImageView.setImageDrawable(image);

        String pageText = getString(page.getTextId()); //sets text to be displayed
        // add name if placeholder included doesnt if not
        pageText = String.format(pageText,name);
        storyTextView.setText(pageText);

        if(page.isFinalPage()){ //final page check
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText(R.string.play_again);
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadPage(0);//goes back to beginning of activity
                }
            });
        }
        else{
            loadButtons(page);
        }
    }

    private void loadButtons(final Page page) { //loads buttons
        choice1Button.setVisibility(View.VISIBLE); //buttons visible
        choice1Button.setText(page.getChoice1().getTextId()); //sets button text
        choice1Button.setOnClickListener(new View.OnClickListener() { //on click method
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice1().getNextPage(); //sets next page
                loadPage(nextPage); //goes to next page
            }
        });

        choice2Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage); //goes to next page
            }
        });
    }

    @Override
    public void onBackPressed() { //if user navigates back
        pageStack.pop();
        if(pageStack.isEmpty()){
            super.onBackPressed();
        }
        else{
            loadPage(pageStack.pop());
        }
    }
}
