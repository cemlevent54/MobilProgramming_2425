package com.example.week4_app2;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week4_app2.adapter.PersonAdapter;
import com.example.week4_app2.data.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PersonAdapter adapter;
    private List<Person> personList;
    private Button btnEkle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEkle = findViewById(R.id.btnEkle);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personList = new ArrayList<>();
        personList.add(new Person("Cem Levent", 21, R.drawable.ic_launcher_background, true));
        personList.add(new Person("Cansu", 24, R.drawable.ic_launcher_background, false));

        adapter = new PersonAdapter(personList, this);
        recyclerView.setAdapter(adapter);

        btnEkle.setOnClickListener(v -> showAddPersonDialog());
    }

    private void showAddPersonDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_update, null);

        EditText editName = dialogView.findViewById(R.id.editName);
        EditText editAge = dialogView.findViewById(R.id.editAge);
        RadioGroup radioGender = dialogView.findViewById(R.id.radioGender);
        RadioButton radioWoman = dialogView.findViewById(R.id.radioWoman);
        RadioButton radioMan = dialogView.findViewById(R.id.radioMan);

        new AlertDialog.Builder(this)
                .setTitle("Yeni Kişi Ekle")
                .setView(dialogView)
                .setPositiveButton("Ekle", (dialog, which) -> {
                    String name = editName.getText().toString().trim();
                    String ageStr = editAge.getText().toString().trim();
                    boolean isMale = radioMan.isChecked();

                    if (!name.isEmpty() && !ageStr.isEmpty()) {
                        int age = Integer.parseInt(ageStr);
                        personList.add(new Person(name, age, R.drawable.ic_launcher_background, isMale));
                        adapter.notifyItemInserted(personList.size() - 1);
                    }
                })
                .setNegativeButton("İptal", null)
                .show();
    }
}