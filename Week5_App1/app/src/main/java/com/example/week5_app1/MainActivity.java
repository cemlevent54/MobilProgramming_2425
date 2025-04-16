package com.example.week5_app1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week5_app1.R;
import com.example.week5_app1.adapter.PersonAdapter;
import com.example.week5_app1.data.Person;
import com.example.week5_app1.database.Database;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PersonAdapter adapter;
    private List<Person> personList;
    private Button btnEkle;
    private Toolbar toolbar;

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar); // 🔥 önemli
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kullanıcılar");

        db = new Database(this);


        btnEkle = findViewById(R.id.btnEkle);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personList = db.getPersonList();

        adapter = new PersonAdapter(personList, this);
        recyclerView.setAdapter(adapter);

        btnEkle.setOnClickListener(v -> showAddPersonDialog());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, systemBars.top, 0, 0); // sadece üst padding veriyoruz
            return insets;
        });
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
                        int imageResId = R.drawable.ic_launcher_background;

                        // ✅ Veritabanına ekle ve ID'yi al
                        long newId = db.addPerson(name, age, imageResId, isMale ? 1 : 0);

                        // ✅ ID'ye göre kişi çek
                        Person newPerson = db.getPersonDetail((int) newId);

                        // ✅ null kontrolü
                        if (newPerson != null) {
                            personList.add(newPerson);
                            adapter.notifyItemInserted(personList.size() - 1);
                        }
                    }
                })
                .setNegativeButton("İptal", null)
                .show();
    }
}