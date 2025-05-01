package com.example.week6_app1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCities;
    private ImageView imageWeatherIcon;
    private TextView textWeatherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        spinnerCities = findViewById(R.id.spinnerCities);
        imageWeatherIcon = findViewById(R.id.imageWeatherIcon);
        textWeatherInfo = findViewById(R.id.textWeatherInfo);



        String[] cities = {"Şehir seçiniz", "Sakarya" ,"İstanbul", "Ankara", "İzmir", "Antalya", "Bursa", "Eskisaehir"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cities);
        spinnerCities.setAdapter(adapter);

        // Şehir seçildiğinde hava durumu bilgilerini güncelle
        spinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // "Şehir seçiniz" ise veri çekme
                    textWeatherInfo.setText("");
                    imageWeatherIcon.setImageDrawable(null);
                    return;
                }

                String city = cities[position];
                updateWeatherInfo(city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Gerek yok
            }
        });

    }

    private void updateWeatherInfo(String city) {
        WeatherService weatherService = new WeatherService(this);
        weatherService.getWeatherByCity(city, new WeatherService.WeatherCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    // Weatherstack JSON yapısı
                    JSONObject current = response.getJSONObject("current");
                    JSONObject location = response.getJSONObject("location");

                    String description = current.getJSONArray("weather_descriptions").getString(0);
                    double temperature = current.getDouble("temperature");
                    String localTime = location.getString("localtime");
                    String country = location.getString("country");
                    String city = location.getString("name") + ", " + country;
                    int humidity = current.getInt("humidity");
                    String iconUrl = current.getJSONArray("weather_icons").getString(0);




                    textWeatherInfo.setText(city + "\n\n" + temperature + "°C\n\n" +
                                    description + "\n\n" + localTime + "\n\n" + humidity + "%\n\n"
                    );

                    // Glide ile ikon yükle
                    Glide.with(MainActivity.this)
                            .load(iconUrl)
                            .into(imageWeatherIcon);


                } catch (Exception e) {
                    e.printStackTrace();
                    textWeatherInfo.setText("Veri çözümlenemedi");
                }
            }

            @Override
            public void onError(VolleyError error) {
                error.printStackTrace();
                textWeatherInfo.setText("Hava durumu alınamadı");
            }
        });
    }


}
