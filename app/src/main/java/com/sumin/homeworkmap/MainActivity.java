package com.sumin.homeworkmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private EditText editTextCountry;
    private EditText editTextCapital;

    private TextView textViewCapital;
    private TextView textViewCountries;

    private Map<String, String> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCapital = findViewById(R.id.editTextCapital);
        editTextSearch = findViewById(R.id.editTextSearch);
        editTextCountry = findViewById(R.id.editTextCountry);
        textViewCapital = findViewById(R.id.textViewCapital);
        textViewCountries = findViewById(R.id.textViewCountries);

        countries = new TreeMap<>();

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String capital = countries.get(s.toString());
                if (capital != null) {
                    textViewCapital.setText(capital);
                } else  {
                    textViewCapital.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onClickAddCountry(View view) {
        if (fieldsAreFilled()) {
            countries.put(editTextCountry.getText().toString().trim(), editTextCapital.getText().toString().trim());
            editTextCountry.setText("");
            editTextCapital.setText("");
            showAllCountries();
        }
    }

    private boolean fieldsAreFilled() {
        return editTextCapital.getText() != null && !editTextCapital.getText().toString().trim().isEmpty() && editTextCountry.getText() != null && !editTextCountry.getText().toString().trim().isEmpty();
    }

    private void showAllCountries() {
        textViewCountries.setText("");
        StringBuilder builder = new StringBuilder();
        for (String country : countries.keySet()) {
            builder.append(country).append(" - ").append(countries.get(country)).append("\n");
        }
        textViewCountries.setText(builder.toString());
    }
}
