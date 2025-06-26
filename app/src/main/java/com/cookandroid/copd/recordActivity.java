package com.cookandroid.copd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.HashMap;

public class recordActivity extends Activity {

    CalendarView calendarView;
    EditText editCount, editTime, editStatus;
    Button btnSave;
    TextView textResult;

    String selectedDate = "";
    HashMap<String, String> fakeDB = new HashMap<>(); // 실제 저장은 안 되고 "저장된 척"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);

        calendarView = findViewById(R.id.calendarView);
        editCount = findViewById(R.id.editCount);
        editTime = findViewById(R.id.editTime);
        editStatus = findViewById(R.id.editStatus);
        btnSave = findViewById(R.id.btnSave);
        textResult = findViewById(R.id.textResult);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;

                if (fakeDB.containsKey(selectedDate)) {
                    textResult.setText("[" + selectedDate + "] 기록:\n" + fakeDB.get(selectedDate));
                } else {
                    textResult.setText("[" + selectedDate + "] 기록 없음.");
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (selectedDate.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "날짜를 선택하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String count = editCount.getText().toString();
                String time = editTime.getText().toString();
                String status = editStatus.getText().toString();

                if (count.isEmpty() || time.isEmpty() || status.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "모든 항목을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String result = "횟수: " + count + "\n시간: " + time + "분\n상태: " + status;
                fakeDB.put(selectedDate, result);
                textResult.setText("[" + selectedDate + "] 기록:\n" + result);
                Toast.makeText(getApplicationContext(), "저장된 척 완료!", Toast.LENGTH_SHORT).show();
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
