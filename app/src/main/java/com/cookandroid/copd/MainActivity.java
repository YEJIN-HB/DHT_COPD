package com.cookandroid.copd;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button trainingBtn = findViewById(R.id.Training);

        trainingBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, trainingActivity.class);
            startActivity(intent);
        });

        Button catBtn = findViewById(R.id.CAT);

        catBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, catActivity.class);
            startActivity(intent);
        });

        }

    }


