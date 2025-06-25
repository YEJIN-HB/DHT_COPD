package com.cookandroid.copd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class trainingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training);

        Button btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
            }
        });
//횡격막 호흡법
        Button btnDiaphragm = (Button) findViewById(R.id.btnDiaphragm);

        btnDiaphragm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        diaphragmActivity.class);
                startActivity(intent);
            }
        });
//폐분절 호흡법
        Button btnLung = (Button) findViewById(R.id.btnLung);

        btnLung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        lungActivity.class);
                startActivity(intent);
            }
        });

        //오므린 입술 호흡법
        Button btnMouse = (Button) findViewById(R.id.btnMouse);

        btnMouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        mouseActivity.class);
                startActivity(intent);
            }
        });

        //횡격막 강화 운동
        Button btnDTraining = (Button) findViewById(R.id.btnDTraining);

        btnDTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        dtActivity.class);
                startActivity(intent);
            }
        });
    }


}


