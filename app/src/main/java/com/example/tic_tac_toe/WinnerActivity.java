package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        TextView tvWinner = findViewById(R.id.tv_winner);
        Button btnNewGame = findViewById(R.id.btn_new_game);

        // Get winner from Intent
        Intent intent = getIntent();
        String winner = intent.getStringExtra("WINNER");
        tvWinner.setText(winner.equals("Draw") ? "It's a Draw!" : winner + " Wins!");

        // New Game Button
        btnNewGame.setOnClickListener(v -> {
            Intent newGameIntent = new Intent(WinnerActivity.this, MainActivity.class);
            newGameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(newGameIntent);
            finish();
        });
    }
}
