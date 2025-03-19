package com.example.spinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCharacters;
    private ImageView imageCharacter;
    private HashMap<String, Integer> characterImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        spinnerCharacters = findViewById(R.id.spinner);
        imageCharacter = findViewById(R.id.imageView);

        String[] characters = {"Select a character", "Luke Skywalker", "Yoda", "Darth Vader", "Obi Wan Kenobi"};
        characterImages = new HashMap<>();
        characterImages.put("Luke Skywalker", R.drawable.luke_skywalker);
        characterImages.put("Yoda", R.drawable.yoda);
        characterImages.put("Darth Vader", R.drawable.darth_vader);
        characterImages.put("Obi Wan Kenobi", R.drawable.obi_wan);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, characters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCharacters.setAdapter(adapter);

        spinnerCharacters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCharacter = (String) parent.getItemAtPosition(position);
                if (position != 0) {
                    imageCharacter.setImageResource(characterImages.get(selectedCharacter));
                } else {
                    imageCharacter.setImageResource(R.drawable.star_wars); // Varsayılan resim
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                imageCharacter.setImageResource(R.drawable.star_wars); // Varsayılan resim
            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}