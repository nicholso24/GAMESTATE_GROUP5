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
                // clears the multi-line EditText
                EditText multilineText = (EditText) findViewById(R.id.editTextTextMultiLine);
                multilineText.setText("");

                // new instance of game state class
                GameState firstInstance = new GameState();

                // deep copy of firstInstance
                GameState secondInstance = new GameState(firstInstance);

                // calls game state methods
                firstInstance.playTest();

                // NEED DESCRIPTIONS OF ACTIONS PRINTED TO MULTI-LINE EDITTEXT

                /*firstInstance.playCard();
                multilineText.append("player plays card on discard pile\n");
                firstInstance.drawCard();
                multilineText.append("player draws card from drawing pile\n");
                firstInstance.sayUno();
                multilineText.append("player presses uno button, indicates 1 card in hand left\n");
                firstInstance.useWild();
                multilineText.append("player has used a wild card to change the color\n");*/

                // creates another new instance of game state class
                GameState thirdInstance = new GameState();

                // deep copy of thirdInstance
                GameState fourthInstance = new GameState(thirdInstance);

                // calls toString() method on secondInstance and fourthInstance; prints to screen
                secondInstance.toString();
                multilineText.append("second instance: "+secondInstance.toString());
                fourthInstance.toString();
                multilineText.append("fourth instance: "+fourthInstance.toString());

            }
        });
    }
}