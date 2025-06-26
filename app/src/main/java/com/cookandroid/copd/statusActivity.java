package com.cookandroid.copd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class statusActivity extends Activity {

    private RadioGroup[] questionGroups = new RadioGroup[8];
    private RadioGroup mMRCGroup;
    private TextView resultText;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);  // XML 파일명이 cat.xml일 경우

        // CAT 문항 연결
        questionGroups[0] = findViewById(R.id.q1Group);
        questionGroups[1] = findViewById(R.id.q2Group);
        questionGroups[2] = findViewById(R.id.q3Group);
        questionGroups[3] = findViewById(R.id.q4Group);
        questionGroups[4] = findViewById(R.id.q5Group);
        questionGroups[5] = findViewById(R.id.q6Group);
        questionGroups[6] = findViewById(R.id.q7Group);
        questionGroups[7] = findViewById(R.id.q8Group);

        // mMRC 연결
        mMRCGroup = findViewById(R.id.mMRCGroup);

        resultText = findViewById(R.id.resultText);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            int totalScore = 0;

            // CAT 점수 계산
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

            // mMRC 점수 계산
            int mMRCCheckedId = mMRCGroup.getCheckedRadioButtonId();
            if (mMRCCheckedId == -1) {
                Toast.makeText(getApplicationContext(), "mMRC 항목에 응답해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton mMRCSelected = findViewById(mMRCCheckedId);
            int mMRCScore = Integer.parseInt(mMRCSelected.getText().toString().substring(0, 1)); // "0: ~" 형태에서 숫자만 추출

            // 결과 메시지 구성
            String message = "총 CAT 점수는 " + totalScore + "점입니다.\n";

            if (totalScore <= 10) {
                message += "→ 증상이 경미한 편입니다.\n";
            } else if (totalScore <= 20) {
                message += "→ 증상이 중간 정도입니다.\n";
            } else {
                message += "→ 증상이 심한 편입니다. 의사와 상담이 필요합니다.\n";
            }

            message += "mMRC 점수는 " + mMRCScore + "점입니다.\n";

            resultText.setText(message);
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
