package com.cookandroid.copd;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.app.Activity;

public class lungStart extends Activity {
    int completeCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lung_start);

        Chronometer chrono;
        chrono = findViewById(R.id.D_timer);
        Button btnStart5 = (Button) findViewById(R.id.btnStartTimer5);
        btnStart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();

                new Handler().postDelayed(() -> chrono.stop(), 5*60*1000);
            }
        });

        Button btnStart10 = (Button) findViewById(R.id.btnStartTimer10);
        btnStart10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();

                new Handler().postDelayed(() -> chrono.stop(), 10*60*1000);
            }
        });


        TextView textCount = findViewById(R.id.textCount);
        Button btnCount = (Button) findViewById(R.id.btnEnd);
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeCount++;
                textCount.setText("완료 횟수: " + completeCount + "회");
            }
        });

        Button btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
            }
        });
    }

}

