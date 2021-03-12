package com.example.gamestate_group5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                EditText multilineText = (EditText) findViewById(R.id.editTextTextMultiLine);
                multilineText.setText("");

                // 2) A new instance of the game state class is created using the default constructor and
                // assigned to a variable named firstInstance.
                GameState firstInstance = new GameState();

                // 3) Use your other constructor to create a deep copy of firstInstance from the perspective
                // of player one. Assign this copy to a variable named secondInstance.
                GameState secondInstance = new GameState(firstInstance);

                // 4) Using firstInstance, call each method in the game state class at least once. In
                // each case it should be making a legal move in the game.
                // For each method call, a brief description of the action taken should be
                // printed to the multi-line EditText.
                // New messages should be appended to previous ones, not overwrite them.
                firstInstance.playCard();
                multilineText.append("player plays card on discard pile\n");
                firstInstance.drawCard();
                multilineText.append("player draws card from drawing pile\n");
                firstInstance.sayUno();
                multilineText.append("player presses uno button, indicates 1 card in hand left\n");
                firstInstance.useWild();
                multilineText.append("player has used a wild card to change the color\n");

                // 5) Create a new instance of the game state class using the default constructor.
                // Assign this value to a variable named thirdInstance.
                GameState thirdInstance = new GameState();

                // 6) Use your deep copy constructor to make a deep copyof thirdInstance from
                // the perspective of player one. Assign this copy to a variable named fourthInstance.
                // need deep opy to be completed first
                GameState fourthInstance = new GameState(thirdInstance);

                // 7) Call the toString() method on secondInstance and fourthInstance.
                // The two strings should be identical. Your code should verify this.
                // Also, print both strings to the multi-line EditText for visual inspection.
                // Again, append these rather than overwrite previous messages.
                secondInstance.toString();
                fourthInstance.toString();

            }
        });
    }
}