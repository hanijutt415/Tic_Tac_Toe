package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button[] buttons = new Button[9];
    private boolean isPlayerXTurn = true;
    private int turnCount = 0;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tv_status);

        for (int i = 0; i < 9; i++) {
            String buttonID = "btn_" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(this::onButtonClick);
        }

        Button resetButton = findViewById(R.id.btn_reset);
        resetButton.setOnClickListener(v -> resetGame());
    }

    private void onButtonClick(View view) {
        Button button = (Button) view;

        if (!button.getText().toString().isEmpty()) {
            return;
        }

        button.setText(isPlayerXTurn ? "X" : "O");

        if (checkWinner()) {
            String winner = isPlayerXTurn ? "Player X" : "Player O";
            Intent intent = new Intent(MainActivity.this, WinnerActivity.class);
            intent.putExtra("WINNER", winner);
            startActivity(intent);
            return;
        }

        turnCount++;

        if (turnCount == 9) {
            Intent intent = new Intent(MainActivity.this, WinnerActivity.class);
            intent.putExtra("WINNER", "Draw");
            startActivity(intent);
            return;
        }

        isPlayerXTurn = !isPlayerXTurn;
        tvStatus.setText(isPlayerXTurn ? "Player X's Turn" : "Player O's Turn");
    }

    private boolean checkWinner() {
        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combination : winCombinations) {
            String a = buttons[combination[0]].getText().toString();
            String b = buttons[combination[1]].getText().toString();
            String c = buttons[combination[2]].getText().toString();

            if (!a.isEmpty() && a.equals(b) && b.equals(c)) {
                return true;
            }
        }
        return false;
    }

    private void resetGame() {
        for (Button button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        isPlayerXTurn = true;
        turnCount = 0;
        tvStatus.setText("Player X's Turn");
    }
}
