package com.example.week2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText weight;
    EditText height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setTitle("BMI Calculator");

        height = (EditText)findViewById(R.id.editTextHeight);
        weight = (EditText)findViewById(R.id.editTextWeight);
    }

    public void resetBtn(View view) {
        height.setText("");
        weight.setText("");
    }

    public void calcBtn(View view) {
        int h;
        int w;
        try {
            w = Integer.parseInt(weight.getText().toString());
            h = Integer.parseInt(height.getText().toString());
        } catch(Exception e) {
            Toast.makeText(this, (CharSequence) "Error parsing your input!",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(h < 80 || h > 300) {
            Toast.makeText(this, (CharSequence) "Only heights between 80 cm and 300 cm are accepted.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if(w < 20 || w > 200) {
            Toast.makeText(this, (CharSequence) "Only weights between 20 kg and 200 kg are accepted.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Intent resultActivity = new Intent(view.getContext(), ResultActivity.class);
        resultActivity.putExtra("height", h);
        resultActivity.putExtra("weight", w);
        startActivity(resultActivity);

        resetBtn(view);
    }
}