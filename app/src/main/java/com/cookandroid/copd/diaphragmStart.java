package com.cookandroid.copd;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.app.Activity;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.os.Handler;
import android.widget.TextView;

public class diaphragmStart extends Activity {
    int completeCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaphragm_start);

        WebView webView = findViewById(R.id.D_Vedio);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String videoId = "wD-D7VKglJg";
        String html = "<html><body style='margin:0;padding:0;'>" +
                "<iframe width='100%' height='200' src='https://www.youtube.com/embed/" +
                videoId + "' frameborder='0' allowfullscreen></iframe>" +
                "</body></html>";

        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

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
    }

}
