package com.example.week3_app2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewSeason;
    private RadioGroup radioGroupSeasons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageViewSeason = findViewById(R.id.imageViewSeason);
        radioGroupSeasons = findViewById(R.id.radioGroupSeasons);


        String[] seasons = {"Kış", "İlkbahar", "Yaz", "Sonbahar"};


        Map<String, Integer> seasonImages = new HashMap<>();
        seasonImages.put("Kış", R.drawable.winter);
        seasonImages.put("İlkbahar", R.drawable.spring);
        seasonImages.put("Yaz", R.drawable.summer);
        seasonImages.put("Sonbahar", R.drawable.autumn);


        for (String season : seasons) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(season);
            radioButton.setId(season.hashCode());
            radioGroupSeasons.addView(radioButton);
        }


        radioGroupSeasons.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedButton = findViewById(checkedId);
            if (selectedButton != null) {
                String selectedSeason = selectedButton.getText().toString();
                imageViewSeason.setImageResource(seasonImages.get(selectedSeason));
            }
        });
    }
}
