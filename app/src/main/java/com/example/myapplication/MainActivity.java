package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        populateGrid();

        Spinner ruleSpinner = findViewById(R.id.ruleSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.rules_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ruleSpinner.setAdapter(adapter);

        ruleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRule = parent.getItemAtPosition(position).toString();
                highlightNumbers(selectedRule);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void populateGrid() {
        for (int i = 1; i <= 100; i++) {
            TextView textView = new TextView(this);
            textView.setText(String.valueOf(i));
            textView.setLayoutParams(new GridLayout.LayoutParams());
            textView.setPadding(30, 40, 30, 30);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            gridLayout.addView(textView);
        }
    }
    private boolean isEven(int number) {
        return number % 2 == 0;
    }

    private boolean isOdd(int number) {
        return number % 2 != 0;
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    private boolean isFibonacci(int number) {
        int a = 0, b = 1, c = 0;
        while (c < number) {
            c = a + b;
            a = b;
            b = c;
        }
        return c == number;
    }
    private void highlightNumbers(String rule) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            TextView textView = (TextView) gridLayout.getChildAt(i);
            int number = Integer.parseInt(textView.getText().toString());
            textView.setBackgroundColor(Color.TRANSPARENT); // Reset color

            switch (rule) {
                case "Odd":
                    if (isOdd(number)) textView.setBackgroundColor(Color.YELLOW);
                    break;
                case "Even":
                    if (isEven(number)) textView.setBackgroundColor(Color.GREEN);
                    break;
                case "Prime":
                    if (isPrime(number)) textView.setBackgroundColor(Color.RED);
                    break;
                case "Fibonacci":
                    if (isFibonacci(number)) textView.setBackgroundColor(Color.BLUE);
                    break;
            }
        }
    }

}
