package com.example.week2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    TextView bmi;
    TextView range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setTitle("BMI Calculator");

        bmi = (TextView)findViewById(R.id.bmi);
        range = (TextView)findViewById(R.id.range);

        int h = getIntent().getIntExtra("height", -1);
        int w = getIntent().getIntExtra("weight", -1);

        if(h == -1 || w == -1) {
            bmi.setText("Something went wrong.");
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);

            double result = (double)w / Math.pow((double)h/100, 2);
            bmi.setText(String.valueOf(df.format(result)));

            if(result < 18.5) {
                range.setTextColor(Color.parseColor("#70bfc4"));
                range.setText("underweight!");
            } else if(result >= 18.5 && result < 25) {
                range.setTextColor(Color.parseColor("#2b8f4a"));
                range.setText("healthy!");
            } else if(result >= 25 && result < 30) {
                range.setTextColor(Color.parseColor("#e68a20"));
                range.setText("overweight!");
            } else {
                range.setTextColor(Color.parseColor("#e63420"));
                range.setText("obese!");
            }
        }
    }

    public void finishBtn(View view) {
        finish();
    }
}