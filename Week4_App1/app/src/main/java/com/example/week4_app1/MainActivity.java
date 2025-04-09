package com.example.week4_app1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerProvince;
    ListView listViewDistricts;

    // Bölgeler ve iller verisi
    HashMap<String, List<String>> regionData;
    List<String> regions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        spinnerProvince = findViewById(R.id.spinnerProvince);
        listViewDistricts = findViewById(R.id.listViewDistricts);

        setupRegionData();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                regions
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(spinnerAdapter);

        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRegion = regions.get(position);

                if (selectedRegion.equals("Choose a region")) {
                    List<String> defaultList = new ArrayList<>();
                    defaultList.add("No items selected");
                    ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            defaultList
                    );
                    listViewDistricts.setAdapter(listAdapter);
                } else {
                    List<String> cities = regionData.get(selectedRegion);
                    ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            cities
                    );
                    listViewDistricts.setAdapter(listAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupRegionData() {
        regionData = new HashMap<>();
        regions = new ArrayList<>();

        // Başta boş seçim olacak
        regions.add("Choose a region");

        regions.add("Marmara");
        regions.add("Ege");
        regions.add("Akdeniz");
        regions.add("İç Anadolu");
        regions.add("Karadeniz");
        regions.add("Doğu Anadolu");
        regions.add("Güneydoğu Anadolu");

        regionData.put("Marmara", List.of("İstanbul", "Bursa", "Kocaeli", "Edirne", "Tekirdağ"));
        regionData.put("Ege", List.of("İzmir", "Manisa", "Aydın", "Denizli", "Muğla"));
        regionData.put("Akdeniz", List.of("Antalya", "Adana", "Mersin", "Hatay", "Isparta"));
        regionData.put("İç Anadolu", List.of("Ankara", "Konya", "Kayseri", "Eskişehir", "Sivas"));
        regionData.put("Karadeniz", List.of("Trabzon", "Samsun", "Ordu", "Rize", "Amasya"));
        regionData.put("Doğu Anadolu", List.of("Erzurum", "Malatya", "Elazığ", "Van", "Kars"));
        regionData.put("Güneydoğu Anadolu", List.of("Diyarbakır", "Gaziantep", "Şanlıurfa", "Mardin", "Batman"));
    }
}