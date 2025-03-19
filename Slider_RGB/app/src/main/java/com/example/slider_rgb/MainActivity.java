package com.example.slider_rgb;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekRed, seekGreen, seekBlue;
    private LinearLayout mainLayout;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        seekRed = findViewById(R.id.seekRed);
        seekGreen = findViewById(R.id.seekGreen);
        seekBlue = findViewById(R.id.seekBlue);
        mainLayout = findViewById(R.id.mainLayout);

        sharedPreferences = getSharedPreferences("ColorPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        int red = sharedPreferences.getInt("red", 255);
        int green = sharedPreferences.getInt("green", 255);
        int blue = sharedPreferences.getInt("blue", 255);

        seekRed.setProgress(red);
        seekGreen.setProgress(green);
        seekBlue.setProgress(blue);

        updateBackgroundColor(red, green, blue);

        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int r = seekRed.getProgress();
                int g = seekGreen.getProgress();
                int b = seekBlue.getProgress();

                updateBackgroundColor(r, g, b);

                editor.putInt("red", r);
                editor.putInt("green", g);
                editor.putInt("blue", b);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };

        seekRed.setOnSeekBarChangeListener(listener);
        seekGreen.setOnSeekBarChangeListener(listener);
        seekBlue.setOnSeekBarChangeListener(listener);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void updateBackgroundColor(int red, int green, int blue) {
        mainLayout.setBackgroundColor(Color.rgb(red, green, blue));
    }
}