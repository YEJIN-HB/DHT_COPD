package com.cookandroid.copd;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class catActivity extends Activity {

    private RadioGroup[] questionGroups = new RadioGroup[8];
    private TextView resultText;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat);

        questionGroups[0] = findViewById(R.id.q1Group);
        questionGroups[1] = findViewById(R.id.q2Group);
        questionGroups[2] = findViewById(R.id.q3Group);
        questionGroups[3] = findViewById(R.id.q4Group);
        questionGroups[4] = findViewById(R.id.q5Group);
        questionGroups[5] = findViewById(R.id.q6Group);
        questionGroups[6] = findViewById(R.id.q7Group);
        questionGroups[7] = findViewById(R.id.q8Group);

        resultText = findViewById(R.id.resultText);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            int totalScore = 0;

            for (int i = 0; i < 8; i++) {
                int checkedId = questionGroups[i].getCheckedRadioButtonId();
                if (checkedId == -1) {
                    Toast.makeText(getApplicationContext(), (i + 1) + "번 문항에 응답해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selected = findViewById(checkedId);
                int score = Integer.parseInt(selected.getText().toString());
                totalScore += score;
            }

            String message = "총 CAT 점수는 " + totalScore + "점입니다.\n";

            if (totalScore <= 10) {
                message += "→ 증상이 경미한 편입니다.";
            } else if (totalScore <= 20) {
                message += "→ 증상이 중간 정도입니다.";
            } else {
                message += "→ 증상이 심한 편입니다. 의사와 상담이 필요합니다.";
            }

            resultText.setText(message);
        });
    }
}
