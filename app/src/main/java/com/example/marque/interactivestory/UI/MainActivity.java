package com.example.marque.interactivestory.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.marque.interactivestory.R;

public class MainActivity extends AppCompatActivity {

    private EditText nameField;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = (EditText) findViewById(R.id.nameEditText); //textfield for user text created

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameField.getText().toString(); //converts string to text string
                startStory(name);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        nameField.setText(""); //when app resumes name field reset
    }

    private void startStory(String name) {  //starts story using name variable and StortyActivity class
        Intent intent = new Intent(this,StoryActivity.class);
        String key = getResources().getString(R.string.key_name);
        intent.putExtra(key,name);
        startActivity(intent);
    }
}
