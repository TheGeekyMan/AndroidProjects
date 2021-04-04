package com.example.sleepindicator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.lang.Math;
import java.text.NumberFormat;

import com.example.sleepindicator.R;

public class MainActivity extends AppCompatActivity {
    int weekend = 2;
    int weekday = 5;
    int num = 0;
    int num1 = 1;
    int optimalHours = 7 * 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit(View view){
        int actualHours = weekday * num + weekend * num1;
        int solution = optimalHours - actualHours;
//        displayAnswer(solution+" ");
        if (solution > 0) {
            String result = "You need " + solution + " hours more sleep to enjoy healthy life" + "\nThank You!";
            displayMessage(result);
        }

        else
        {
            String result = "You need " + Math.abs(solution) + " hours less sleep to enjoy healthy life" + "\nThank You!";
            displayMessage(result);
        }

    }

    public void increment(View view){
        num = num + 1;
        display(num);
    }
    public void decrement(View view){
        num = num - 1;
        display(num);
    }
    public void incrementSecond(View view){
        num1 = num1 + 1;
        displaySecond(num1);
    }
    public void decrementSecond(View view){
        num1 = num1 - 1;
        displaySecond(num1);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.hours_weekday_text_view);
        quantityTextView.setText("" + number);
    } private void displaySecond(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.hours_weekend_text_view);
        quantityTextView.setText("" + number);
    }
    public void displayAnswer (int i) {
        TextView t = (TextView) findViewById(R.id.display_text_view);
        t.setText(""+i);
    }
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.display_text_view);
        priceTextView.setText(message);
    }
}