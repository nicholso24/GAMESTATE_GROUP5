package com.example.gamestate_group5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup button
        Button testButton = (Button) findViewById(R.id.runTestButton);
        testButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 1) Any text currently displayed in in the multi-line EditText (probably from a
                // previous test run) should be cleared.

                //(R.id.editTextTextMultiLine).getEditableText().clear();
                // why doesn't this work^^

                // 2) A new instance of the game state class is created using the default constructor and
                // assigned to a variable named firstInstance.
                GameState firstInstance = new GameState();

                // 3) Use your other constructor to create a deep copy of firstInstance from the perspective
                // of player one. Assign this copy to a variable named secondInstance.
                // need deep opy to be completed first
                // GameState secondInstance = new GameState();

                // 4) Using firstInstance, call each method in the game state class at least once. In
                // each case it should be making a legal move in the game.
                // For each method call, a brief description of the action taken should be
                // printed to the multi-line EditText.
                // New messages should be appended to previous ones, not overwrite them.

                // 5) Create a new instance of the game state class using the default constructor.
                // Assign this value to a variable named thirdInstance.
                GameState thirdInstance = new GameState();

                // 6) Use your deep copy constructor to make a deep copyof thirdInstance from
                // the perspective of player one. Assign this copy to a variable named fourthInstance.
                // need deep opy to be completed first
                // GameState fourthInstance =

                // 7) Call the toString() method on secondInstance and fourthInstance.
                // The two strings should be identical. Your code should verify this.
                // Also, print both strings to the multi-line EditText for visual inspection.
                // Again, append these rather than overwrite previous messages.
                // need toString to be completed
            }
        });
    }
}