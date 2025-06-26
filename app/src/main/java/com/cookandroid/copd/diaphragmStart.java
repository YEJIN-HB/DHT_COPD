package com.cookandroid.copd;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class diaphragmStart extends Activity {

    Button btnStartTimer5, btnStartTimer10, btnEnd, btnHome;
    Chronometer chronometer;
    TextView textCount;
    WebView webView;
    int count = 0;

    String beforeCondition = "";
    String afterCondition = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaphragm_start);

        btnStartTimer5 = findViewById(R.id.btnStartTimer5);
        btnStartTimer10 = findViewById(R.id.btnStartTimer10);
        btnEnd = findViewById(R.id.btnEnd);
        btnHome = findViewById(R.id.btnHome);
        chronometer = findViewById(R.id.D_timer);
        textCount = findViewById(R.id.textCount);
        webView = findViewById(R.id.D_Vedio);

        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);

// 유튜브 영상 ID만 추출해서 iframe에 삽입
        String videoId = "1QjvUrS5r4k";
        String html = "<html><body style='margin:0;padding:0;'>" +
                "<iframe width='100%' height='100%' src='https://www.youtube.com/embed/" +
                videoId + "' frameborder='0' allowfullscreen></iframe>" +
                "</body></html>";

        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

        btnStartTimer5.setOnClickListener(v -> showBeforeDialog(5));
        btnStartTimer10.setOnClickListener(v -> showBeforeDialog(10));

        btnEnd.setOnClickListener(v -> {
            chronometer.stop();
            count++;
            textCount.setText("훈련 횟수: " + count + "회");
            showAfterDialog();
        });

        btnHome.setOnClickListener(v -> finish());
    }

    private void showBeforeDialog(int minutes) {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_status, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("훈련 전 상태 입력");
        builder.setView(dialogView);

        RadioGroup radioBreath = dialogView.findViewById(R.id.radioBreath);
        RadioGroup radioMood = dialogView.findViewById(R.id.radioMood);

        builder.setPositiveButton("확인", (dialog, which) -> {
            int breathId = radioBreath.getCheckedRadioButtonId();
            int moodId = radioMood.getCheckedRadioButtonId();

            if (breathId != -1 && moodId != -1) {
                RadioButton breathSelected = dialogView.findViewById(breathId);
                RadioButton moodSelected = dialogView.findViewById(moodId);

                beforeCondition = breathSelected.getText().toString() + " / " + moodSelected.getText().toString();
                Toast.makeText(getApplicationContext(), "훈련을 시작합니다.", Toast.LENGTH_SHORT).show();
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            } else {
                Toast.makeText(getApplicationContext(), "모든 항목을 선택해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("취소", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void showAfterDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_status, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("훈련 후 상태 입력");
        builder.setView(dialogView);

        RadioGroup radioBreath = dialogView.findViewById(R.id.radioBreath);
        RadioGroup radioMood = dialogView.findViewById(R.id.radioMood);

        builder.setPositiveButton("확인", (dialog, which) -> {
            int breathId = radioBreath.getCheckedRadioButtonId();
            int moodId = radioMood.getCheckedRadioButtonId();

            if (breathId != -1 && moodId != -1) {
                RadioButton breathSelected = dialogView.findViewById(breathId);
                RadioButton moodSelected = dialogView.findViewById(moodId);

                afterCondition = breathSelected.getText().toString() + " / " + moodSelected.getText().toString();

                String summary = "훈련 전: " + beforeCondition + "\n"
                        + "훈련 후: " + afterCondition;

                new AlertDialog.Builder(this)
                        .setTitle("훈련 결과 요약")
                        .setMessage(summary)
                        .setPositiveButton("확인", null)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(), "모든 항목을 선택해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("취소", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}
