package com.cookandroid.copd;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class timerActivity extends Activity {

    TimePicker timePicker;
    Button btnSetAlarm;
    TextView txtAlarmInfo;
    Handler handler = new Handler();
    Runnable alarmRunnable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        timePicker = findViewById(R.id.timePicker);
        btnSetAlarm = findViewById(R.id.btnSetAlarm);
        txtAlarmInfo = findViewById(R.id.txtAlarmInfo);

        if (Build.VERSION.SDK_INT >= 23) {
            timePicker.setIs24HourView(true);
        }

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();
                Calendar target = Calendar.getInstance();

                int hour, minute;
                if (Build.VERSION.SDK_INT >= 23) {
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                } else {
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                }

                target.set(Calendar.HOUR_OF_DAY, hour);
                target.set(Calendar.MINUTE, minute);
                target.set(Calendar.SECOND, 0);

                long delayMillis = target.getTimeInMillis() - now.getTimeInMillis();
                if (delayMillis < 0) {
                    delayMillis += 24 * 60 * 60 * 1000; // 다음 날
                }

                String timeText = String.format("%02d시 %02d분", hour, minute);
                txtAlarmInfo.setText(getString(R.string.alarm_set_prefix) + " " + timeText);

                alarmRunnable = new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "호흡 훈련 시간입니다!", Toast.LENGTH_LONG).show();
                    }
                };

                handler.postDelayed(alarmRunnable, delayMillis);
                Toast.makeText(getApplicationContext(), "알림이 설정되었습니다.", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (alarmRunnable != null) {
            handler.removeCallbacks(alarmRunnable); // 메모리 누수 방지
        }
    }
}
