package com.cookandroid.copd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class diaphragmActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaphragm);

        Button btnStart = (Button) findViewById(R.id.btnStart_Diaphragm);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),
                        diaphragmStart.class);
                startActivity(intent);
            }
        });
    }


}