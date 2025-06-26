package com.cookandroid.copd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class mouseStart extends Activity {
    int completeCount = 0;
    String beforeCondition = "";
    String afterCondition = "";
    Chronometer chrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mouse_start);

        WebView webView = findViewById(R.id.D_Vedio);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String videoId = "-ltUSERYbfk";
        String html = "<html><body style='margin:0;padding:0;'>" +
                "<iframe width='100%' height='200' src='https://www.youtube.com/embed/" +
                videoId + "' frameborder='0' allowfullscreen></iframe>" +
                "</body></html>";

        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

        chrono = findViewById(R.id.D_timer);
        TextView textCount = findViewById(R.id.textCount);
        Button btnStart5 = findViewById(R.id.btnStartTimer5);
        Button btnStart10 = findViewById(R.id.btnStartTimer10);
        Button btnCount = findViewById(R.id.btnEnd);
        Button btnHome = findViewById(R.id.btnHome);

        btnStart5.setOnClickListener(v -> showBeforeDialog(5));
        btnStart10.setOnClickListener(v -> showBeforeDialog(10));

        btnCount.setOnClickListener(v -> {
            chrono.stop();
            completeCount++;
            textCount.setText("완료 횟수: " + completeCount + "회");
            showAfterDialog();
        });

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

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
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();

                new Handler().postDelayed(() -> chrono.stop(), minutes * 60 * 1000);
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
