package com.example.baith1_b2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

public class SpinnerActivity2 extends AppCompatActivity {

    Spinner countrySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner2);

        countrySpinner = findViewById(R.id.spinner);

        String[] countryNames = {"USA", "Canada", "Japan", "Germany", "Brazil"};
        int[] countryFlags = {R.drawable.usa, R.drawable.canada, R.drawable.japan,
                R.drawable.germany, R.drawable.brazil};

        MyAdapter adapter = new MyAdapter(this, countryNames, countryFlags);
        countrySpinner.setAdapter(adapter);
    }
}